public class HitTheNumber {
	public int[] solution(int A) {
		for (int length = 1;; length++) {
			int[] sequence = new int[length];
			sequence[0] = 1;
			if (search(sequence, 1, A)) {
				return sequence;
			}
		}
	}

	boolean search(int[] sequence, int index, int A) {
		if (index == sequence.length) {
			return sequence[sequence.length - 1] == A;
		}

		if (!isPossible(sequence, index, A)) {
			return false;
		}

		for (int i = index - 1; i >= 0; i--) {
			if (sequence[i] * 2 <= sequence[index - 1]) {
				break;
			}
			for (int j = i; j >= 0; j--) {
				int next = sequence[j] + sequence[i];
				if (next <= sequence[index - 1]) {
					break;
				}
				sequence[index] = next;
				if (search(sequence, index + 1, A)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isPossible(int[] sequence, int index, int A) {
		int last = sequence[index - 1];
		for (int i = index; i < sequence.length; i++) {
			last *= 2;
		}
		return last >= A;
	}
}
