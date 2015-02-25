public class MaxNonoverlappingSegments {
	public int solution(int[] A, int[] B) {
		int result = 0;
		int previous = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > previous) {
				result++;
				previous = B[i];
			}
		}
		return result;
	}
}
