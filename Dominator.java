public class Dominator {
	public int solution(int[] A) {
		int index = -1;
		int candidate = 0;
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (count == 0) {
				index = i;
				candidate = A[i];
				count++;
			} else if (A[i] == candidate) {
				count++;
			} else {
				count--;
			}
		}
		int countCandidate = 0;
		for (int number : A) {
			if (number == candidate) {
				countCandidate++;
			}
		}
		return (countCandidate * 2 > A.length) ? index : -1;
	}
}
