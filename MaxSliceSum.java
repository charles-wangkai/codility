public class MaxSliceSum {
	public int solution(int[] A) {
		int maxSum = Integer.MIN_VALUE;
		int currentSum = 0;
		for (int number : A) {
			currentSum += number;
			maxSum = Math.max(maxSum, currentSum);
			currentSum = Math.max(0, currentSum);
		}
		return maxSum;
	}
}
