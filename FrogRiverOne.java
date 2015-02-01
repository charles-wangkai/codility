public class FrogRiverOne {
	public int solution(int X, int[] A) {
		boolean[] appears = new boolean[X + 1];
		int remain = X;
		for (int i = 0; i < A.length; i++) {
			if (!appears[A[i]]) {
				appears[A[i]] = true;
				remain--;
				if (remain == 0) {
					return i;
				}
			}
		}
		return -1;
	}
}
