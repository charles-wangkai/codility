public class StrSymmetryPoint {
	public int solution(String S) {
		int length = S.length();
		if (length % 2 != 0 && isPalindrome(S)) {
			return length / 2;
		} else {
			return -1;
		}
	}

	boolean isPalindrome(String S) {
		for (int i = 0, j = S.length() - 1; i < j; i++, j--) {
			if (S.charAt(i) != S.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
