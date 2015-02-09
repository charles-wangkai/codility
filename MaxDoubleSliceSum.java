public class MaxDoubleSliceSum {
	public int solution(int[] A) {
		int[] maxLeftSums = new int[A.length];
		int sum = 0;
		for (int i = 1; i < A.length; i++) {
			sum = Math.max(0, sum + A[i]);
			maxLeftSums[i] = sum;
		}

		int[] maxRightSums = new int[A.length];
		sum = 0;
		for (int i = A.length - 2; i >= 0; i--) {
			sum = Math.max(0, sum + A[i]);
			maxRightSums[i] = sum;
		}

		int maxSum = 0;
		for (int i = 1; i <= A.length - 2; i++) {
			maxSum = Math.max(maxSum, maxLeftSums[i - 1] + maxRightSums[i + 1]);
		}
		return maxSum;
	}
}
