public class Flags {
	public int solution(int[] A) {
		boolean[] peaks = buildPeaks(A);

		int[] nextPeaks = buildNextPeaks(peaks);

		int maxFlagNum = 0;
		for (int flagNum = 1; (flagNum - 1) * flagNum < A.length; flagNum++) {
			if (canSetFlags(nextPeaks, flagNum)) {
				maxFlagNum = Math.max(maxFlagNum, flagNum);
			}
		}
		return maxFlagNum;
	}

	boolean[] buildPeaks(int[] A) {
		boolean[] peaks = new boolean[A.length];
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks[i] = true;
			}
		}
		return peaks;
	}

	int[] buildNextPeaks(boolean[] peaks) {
		int[] nextPeaks = new int[peaks.length];
		int nextPeak = -1;
		for (int i = nextPeaks.length - 1; i >= 0; i--) {
			if (peaks[i]) {
				nextPeak = i;
			}
			nextPeaks[i] = nextPeak;
		}
		return nextPeaks;
	}

	boolean canSetFlags(int[] nextPeaks, int flagNum) {
		int index = 0;
		for (int i = 0; i < flagNum; i++) {
			if (index >= nextPeaks.length || nextPeaks[index] < 0) {
				return false;
			}
			index = nextPeaks[index] + flagNum;
		}
		return true;
	}
}
