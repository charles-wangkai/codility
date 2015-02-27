public class OddOccurrencesInArray {
	public int solution(int[] A) {
		int unpaired = 0;
		for (int number : A) {
			unpaired ^= number;
		}
		return unpaired;
	}
}
