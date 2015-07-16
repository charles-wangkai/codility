import java.util.Arrays;

public class Cannonballs {
	public int[] solution(int[] A, int[] B) {
		int maxCannon = -1;
		for (int cannon : B) {
			maxCannon = Math.max(maxCannon, cannon);
		}

		int[] hitIndices = new int[maxCannon + 1];
		Arrays.fill(hitIndices, Integer.MAX_VALUE);
		int prevHeight = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > prevHeight) {
				for (int j = prevHeight + 1; j <= A[i] && j < hitIndices.length; j++) {
					hitIndices[j] = i;
				}
				prevHeight = A[i];
			}
		}

		int[] A1 = Arrays.copyOf(A, A.length);
		for (int cannon : B) {
			int hitIndex = hitIndices[cannon];

			if (hitIndex == 0 || hitIndex >= A.length) {
				continue;
			}

			A1[hitIndex - 1]++;
			hitIndices[A1[hitIndex - 1]] = Math.min(
					hitIndices[A1[hitIndex - 1]], hitIndex - 1);
		}
		return A1;
	}
}
