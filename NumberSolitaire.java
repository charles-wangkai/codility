public class NumberSolitaire {
	public int solution(int[] A) {
		int[] maxSums = new int[A.length];
		maxSums[0] = A[0];
		for (int i = 1; i < maxSums.length; i++) {
			maxSums[i] = Integer.MIN_VALUE;
			for (int dice = 1; dice <= 6 && i - dice >= 0; dice++) {
				maxSums[i] = Math.max(maxSums[i], maxSums[i - dice] + A[i]);
			}
		}
		return maxSums[maxSums.length - 1];
	}
}
