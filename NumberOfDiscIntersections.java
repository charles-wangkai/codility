import java.util.Arrays;

public class NumberOfDiscIntersections {
	static final int LIMIT = 10000000;

	public int solution(int[] A) {
		long[] points = new long[A.length * 2];
		for (int i = 0; i < A.length; i++) {
			points[i * 2] = generatePoint((long) i - A[i], true);
			points[i * 2 + 1] = generatePoint((long) i + A[i], false);
		}

		Arrays.sort(points);

		int intersectNum = 0;
		int openedNum = 0;
		for (long point : points) {
			if (isLeft(point)) {
				intersectNum += openedNum;
				if (intersectNum > LIMIT) {
					return -1;
				}
				openedNum++;
			} else {
				openedNum--;
			}
		}
		return intersectNum;
	}

	long generatePoint(long x, boolean leftOrRight) {
		return x * 2 - (leftOrRight ? 1 : 0);
	}

	boolean isLeft(long point) {
		return point % 2 != 0;
	}
}
