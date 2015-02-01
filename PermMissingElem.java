public class PermMissingElem {
	public int solution(int[] A) {
		int N = A.length;
		long missing = (N + 1L) * (N + 2) / 2;
		for (int element : A) {
			missing -= element;
		}
		return (int) missing;
	}
}
