public class Peaks {
	public int solution(int[] A) {
		int[] peakNums = buildPeakNums(A);
		if (computePeaks(peakNums, 0, A.length - 1) == 0) {
			return 0;
		}
		for (int size = 1;; size++) {
			if (A.length % size == 0 && allContainPeak(peakNums, size)) {
				return A.length / size;
			}
		}
	}

	int[] buildPeakNums(int[] A) {
		int[] peakNums = new int[A.length];
		int peakNum = 0;
		for (int i = 0; i < peakNums.length; i++) {
			if (i > 0 && i < A.length - 1 && A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peakNum++;
			}
			peakNums[i] = peakNum;
		}
		return peakNums;
	}

	int computePeaks(int[] peakNums, int begin, int end) {
		return peakNums[end] - (begin == 0 ? 0 : peakNums[begin - 1]);
	}

	boolean allContainPeak(int[] peakNums, int size) {
		for (int i = 0; i < peakNums.length; i += size) {
			if (computePeaks(peakNums, i, i + size - 1) == 0) {
				return false;
			}
		}
		return true;
	}
}
