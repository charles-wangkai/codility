public class NailingPlanks {
	public int solution(int[] A, int[] B, int[] C) {
		int minNailNum = -1;
		int lower = 0;
		int upper = C.length;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (coverAll(A, B, C, middle)) {
				minNailNum = middle;
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return minNailNum;
	}

	boolean coverAll(int[] A, int[] B, int[] C, int CIndexLimit) {
		boolean[] nailed = buildNailed(C, CIndexLimit);
		int[] cumulativeNails = buildCumulativeNails(nailed);
		for (int i = 0; i < A.length; i++) {
			if (!hasAnyNailBetween(cumulativeNails, A[i], B[i])) {
				return false;
			}
		}
		return true;
	}

	boolean[] buildNailed(int[] C, int CIndexLimit) {
		boolean[] nailed = new boolean[2 * C.length + 1];
		for (int i = 0; i < CIndexLimit; i++) {
			nailed[C[i]] = true;
		}
		return nailed;
	}

	int[] buildCumulativeNails(boolean[] nailed) {
		int[] cumulativeNails = new int[nailed.length];
		int nails = 0;
		for (int i = 0; i < cumulativeNails.length; i++) {
			if (nailed[i]) {
				nails++;
			}
			cumulativeNails[i] = nails;
		}
		return cumulativeNails;
	}

	boolean hasAnyNailBetween(int[] cumulativeNails, int begin, int end) {
		return (cumulativeNails[end] - (begin == 0 ? 0
				: cumulativeNails[begin - 1])) > 0;
	}
}
