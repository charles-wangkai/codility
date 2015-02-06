public class Nesting {
	public int solution(String S) {
		int leftBracketNum = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '(') {
				leftBracketNum++;
			} else {
				if (leftBracketNum == 0) {
					return 0;
				}
				leftBracketNum--;
			}
		}
		return leftBracketNum == 0 ? 1 : 0;
	}
}
