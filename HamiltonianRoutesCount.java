import java.util.HashMap;
import java.util.Map;

// http://blog.sina.com.cn/s/blog_5123df350101b54k.html

public class HamiltonianRoutesCount {
	public int solution(int[] A) {
		if (isAllRoadsConnectsDistinctTowns(A)
				&& isAllTownsVisitedOnceOrThrice(A)
				&& isAllRoadsVisitedTwice(A)) {
			return 3;
		} else {
			return -2;
		}
	}

	boolean isAllRoadsConnectsDistinctTowns(int[] A) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == A[(i + 1) % A.length]) {
				return false;
			}
		}
		return true;
	}

	boolean isAllTownsVisitedOnceOrThrice(int[] A) {
		int townNum = A.length / 2 + 1;
		int[] townCounts = new int[townNum];
		for (int town : A) {
			townCounts[town]++;
		}
		for (int townCount : townCounts) {
			if (townCount != 1 && townCount != 3) {
				return false;
			}
		}
		return true;
	}

	boolean isAllRoadsVisitedTwice(int[] A) {
		Map<Road, Integer> road2count = new HashMap<Road, Integer>();
		for (int i = 0; i < A.length; i++) {
			Road road = new Road(A[i], A[(i + 1) % A.length]);
			if (!road2count.containsKey(road)) {
				road2count.put(road, 0);
			}
			road2count.put(road, road2count.get(road) + 1);
		}
		return road2count.values().stream().allMatch(count -> count == 2);
	}
}

class Road {
	int town1;
	int town2;

	Road(int t1, int t2) {
		this.town1 = Math.min(t1, t2);
		this.town2 = Math.max(t1, t2);
	}

	@Override
	public int hashCode() {
		return town1 * town2;
	}

	@Override
	public boolean equals(Object obj) {
		Road other = (Road) obj;
		return town1 == other.town1 && town2 == other.town2;
	}
}