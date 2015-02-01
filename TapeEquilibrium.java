public class TapeEquilibrium {
	public int solution(int[] A) {
		int minDiff = Integer.MAX_VALUE;
		int rightSum = 0;
		for (int number : A) {
			rightSum += number;
		}
		int leftSum = 0;
		for (int i = 0; i < A.length - 1; i++) {
			leftSum += A[i];
			rightSum -= A[i];
			minDiff = Math.min(minDiff, Math.abs(leftSum - rightSum));
		}
		return minDiff;
	}
}
