public class PrefixSet {
	public int solution(int[] A) {
		boolean[] appears = new boolean[A.length];
		int firstCoveringPrefix = -1;
		for (int i = 0; i < A.length; i++) {
			if (!appears[A[i]]) {
				appears[A[i]] = true;
				firstCoveringPrefix = i;
			}
		}
		return firstCoveringPrefix;
	}
}
