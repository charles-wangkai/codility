public class MaxCounters {
	public int[] solution(int N, int[] A) {
		int[] counters = new int[N];
		int max = 0;
		int prevMax = 0;

		for (int elem : A) {
			if (elem == N + 1) {
				prevMax = max;
			} else {
				refresh(counters, elem - 1, prevMax);
				counters[elem - 1]++;
				max = Math.max(max, counters[elem - 1]);
			}
		}

		for (int i = 0; i < counters.length; i++) {
			refresh(counters, i, prevMax);
		}

		return counters;
	}

	void refresh(int[] counters, int index, int prevMax) {
		counters[index] = Math.max(counters[index], prevMax);
	}
}
