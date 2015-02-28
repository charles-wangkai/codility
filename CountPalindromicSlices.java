public class CountPalindromicSlices {
	static final char SEPARATOR = '#';
	static final int LIMIT = 100000000;

	public int solution(String S) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEPARATOR);
		for (int i = 0; i < S.length(); i++) {
			sb.append(S.charAt(i));
			sb.append(SEPARATOR);
		}

		int[] p = new int[sb.length()];
		int maxRight = -1;
		int centreForMaxRight = -1;
		for (int i = 0; i < p.length; i++) {
			if (maxRight > i) {
				p[i] = Math.min(p[centreForMaxRight * 2 - i], maxRight - i);
			} else {
				p[i] = 1;
			}
			while (i - p[i] >= 0 && i + p[i] < sb.length()
					&& sb.charAt(i - p[i]) == sb.charAt(i + p[i])) {
				p[i]++;
			}
			if (i + p[i] > maxRight) {
				maxRight = i + p[i];
				centreForMaxRight = i;
			}
		}

		int result = 0;
		for (int i = 0; i < p.length; i++) {
			result += (p[i] - 1) / 2;
			if (result > LIMIT) {
				return -1;
			}
		}
		return result;
	}
}
