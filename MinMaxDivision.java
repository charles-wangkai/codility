public class MinMaxDivision {
	public int solution(int K, int M, int[] A) {
		int result = -1;
		int lower = max(A);
		int upper = sum(A);
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (countBlocks(A, middle) <= K) {
				result = middle;
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return result;
	}

	int max(int[] A) {
		int result = Integer.MIN_VALUE;
		for (int number : A) {
			result = Math.max(result, number);
		}
		return result;
	}

	int sum(int[] A) {
		int result = 0;
		for (int number : A) {
			result += number;
		}
		return result;
	}

	int countBlocks(int[] A, int largeSum) {
		int blockNum = 1;
		int remain = largeSum;
		for (int number : A) {
			if (remain < number) {
				remain = largeSum;
				blockNum++;
			}
			remain -= number;
		}
		return blockNum;
	}
}
