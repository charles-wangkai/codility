import java.util.Arrays;

public class MinRouterPeripherality {
	public int solution(int[] T) {
		int N = T.length;

		int[] degrees = new int[N];
		for (int current = 0; current < N; current++) {
			int parent = T[current];
			if (parent != current) {
				degrees[parent]++;
			}
		}

		int[] orders = new int[N];
		int orderIndex = 0;
		for (int i = 0; i < N; i++) {
			if (degrees[i] == 0) {
				orders[orderIndex] = i;
				orderIndex++;
			}
		}
		for (int i = 0; i < N - 1; i++) {
			int current = orders[i];
			int parent = T[current];
			degrees[parent]--;
			if (degrees[parent] == 0) {
				orders[orderIndex] = parent;
				orderIndex++;
			}
		}

		long[] pathLens = new long[N];
		int[] sizes = new int[N];
		Arrays.fill(sizes, 1);
		for (int i = 0; i < N - 1; i++) {
			int current = orders[i];
			int parent = T[current];
			sizes[parent] += sizes[current];
			pathLens[parent] += pathLens[current] + sizes[current];
		}

		for (int i = N - 2; i >= 0; i--) {
			int current = orders[i];
			int parent = T[current];
			pathLens[current] = pathLens[parent] - sizes[current]
					+ (N - sizes[current]);
		}

		int result = 0;
		for (int i = 1; i < N; i++) {
			if (pathLens[i] < pathLens[result]) {
				result = i;
			}
		}
		return result;
	}
}
