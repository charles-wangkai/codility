import java.util.HashMap;
import java.util.Map;

public class GenomicRangeQuery {
	public int[] solution(String S, int[] P, int[] Q) {
		Map<Character, int[]> letter2counts = new HashMap<Character, int[]>();
		char[] letters = { 'A', 'C', 'G', 'T' };
		for (char letter : letters) {
			letter2counts.put(letter, buildCounts(S, letter));
		}

		int[] minImpacts = new int[P.length];
		for (int i = 0; i < P.length; i++) {
			for (int j = 0; j < letters.length; j++) {
				if (countIn(letter2counts.get(letters[j]), P[i], Q[i]) > 0) {
					minImpacts[i] = j + 1;
					break;
				}
			}
		}
		return minImpacts;
	}

	int[] buildCounts(String S, char letter) {
		int[] counts = new int[S.length()];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = (i == 0 ? 0 : counts[i - 1])
					+ (S.charAt(i) == letter ? 1 : 0);
		}
		return counts;
	}

	int countIn(int[] counts, int beginIndex, int endIndex) {
		return counts[endIndex]
				- (beginIndex == 0 ? 0 : counts[beginIndex - 1]);
	}
}
