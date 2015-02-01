public class MinAvgTwoSlice {
	public int solution(int[] A) {
		if (A.length == 2) {
			return 0;
		}

		int[] prefixSums = computePrefixSums(A);
		int startIndex2 = findStartIndex(prefixSums, 2);
		int startIndex3 = findStartIndex(prefixSums, 3);
		int diff = sum(prefixSums, startIndex2, 2) * 3
				- sum(prefixSums, startIndex3, 3) * 2;
		int startIndex;
		if (diff < 0) {
			startIndex = startIndex2;
		} else if (diff > 0) {
			startIndex = startIndex3;
		} else {
			startIndex = Math.min(startIndex2, startIndex3);
		}
		return startIndex;
	}

	int[] computePrefixSums(int[] A) {
		int[] prefixSums = new int[A.length];
		for (int i = 0; i < prefixSums.length; i++) {
			prefixSums[i] = (i == 0 ? 0 : prefixSums[i - 1]) + A[i];
		}
		return prefixSums;
	}

	int findStartIndex(int[] prefixSums, int length) {
		int startIndex = 0;
		for (int i = 0; i < prefixSums.length - length + 1; i++) {
			if (sum(prefixSums, i, length) < sum(prefixSums, startIndex, length)) {
				startIndex = i;
			}
		}
		return startIndex;
	}

	int sum(int[] prefixSums, int startIndex, int length) {
		return prefixSums[startIndex + length - 1]
				- (startIndex == 0 ? 0 : prefixSums[startIndex - 1]);
	}
}
