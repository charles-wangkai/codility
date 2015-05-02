import java.util.Arrays;

public class DoubleMedian {
	public int solution(int[] A, int[] B, int[] P, int[] Q, int[] R, int[] S) {
		int[] medians = new int[P.length];
		for (int i = 0; i < medians.length; i++) {
			medians[i] = findMedian(A, P[i], Q[i], B, R[i], S[i]);
		}
		Arrays.sort(medians);
		return medians[medians.length / 2];
	}

	int findMedian(int[] A, int beginA, int endA, int[] B, int beginB, int endB) {
		int lengthA = endA - beginA + 1;
		int lengthB = endB - beginB + 1;
		int totalLength = lengthA + lengthB;
		return findKthElement(A, beginA, endA, B, beginB, endB,
				(totalLength + 1) / 2);
	}

	int findKthElement(int[] A, int beginIndexA, int endIndexA, int[] B,
			int beginIndexB, int endIndexB, int k) {
		if (beginIndexA > endIndexA) {
			return B[beginIndexB + (k - 1)];
		}
		if (beginIndexB > endIndexB) {
			return A[beginIndexA + (k - 1)];
		}

		int middleIndexA = (beginIndexA + endIndexA) / 2;
		int foundIndexB = Arrays.binarySearch(B, beginIndexB, endIndexB + 1,
				A[middleIndexA]);
		if (foundIndexB < 0) {
			foundIndexB = -1 - foundIndexB - 1;
		}

		if (middleIndexA == endIndexA && foundIndexB == endIndexB) {
			if (k == 1 + (endIndexB - beginIndexB + 1)) {
				return A[middleIndexA];
			} else {
				return B[beginIndexB + (k - 1)];
			}
		}

		if (k <= (middleIndexA - beginIndexA + 1)
				+ (foundIndexB - beginIndexB + 1)) {
			return findKthElement(A, beginIndexA, middleIndexA, B, beginIndexB,
					foundIndexB, k);
		} else {
			return findKthElement(A, middleIndexA + 1, endIndexA, B,
					foundIndexB + 1, endIndexB, k
							- (middleIndexA - beginIndexA + 1)
							- (foundIndexB - beginIndexB + 1));
		}
	}
}
