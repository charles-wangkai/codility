public class MinAbsSum {
	public int solution(int[] A) {
		int max = 0;
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			A[i] = Math.abs(A[i]);
			max = Math.max(max, A[i]);
			sum += A[i];
		}

		int[] counts = new int[max + 1];
		for (int number : A) {
			counts[number]++;
		}

		boolean[] reaches = new boolean[sum / 2 + 1];
		reaches[0] = true;
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] == 0) {
				continue;
			}
			int[] remains = new int[reaches.length];
			for (int j = 0; j < remains.length; j++) {
				remains[j] = reaches[j] ? counts[i] : -1;
			}
			for (int j = 0; j + i < remains.length; j++) {
				if (remains[j] > 0) {
					remains[j + i] = Math.max(remains[j + i], remains[j] - 1);
				}
			}
			for (int j = 0; j < remains.length; j++) {
				if (remains[j] >= 0) {
					reaches[j] = true;
				}
			}
		}

		for (int i = reaches.length - 1;; i--) {
			if (reaches[i]) {
				return sum - i - i;
			}
		}
	}
}
