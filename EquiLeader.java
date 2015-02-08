public class EquiLeader {
	public int solution(int[] A) {
		int candidate = 0;
		int count = 0;
		for (int number : A) {
			if (count == 0) {
				candidate = number;
				count++;
			} else if (number == candidate) {
				count++;
			} else {
				count--;
			}
		}
		if (count == 0) {
			return 0;
		}
		int countCandidate = 0;
		for (int number : A) {
			if (number == candidate) {
				countCandidate++;
			}
		}
		if (countCandidate * 2 <= A.length) {
			return 0;
		}

		int result = 0;
		int countLeft = 0;
		for (int S = 0; S < A.length - 1; S++) {
			if (A[S] == candidate) {
				countLeft++;
			}
			if (countLeft * 2 > S + 1
					&& (countCandidate - countLeft) * 2 > A.length - 1 - S) {
				result++;
			}
		}
		return result;
	}
}
