import java.util.Arrays;

public class CountTriangles {
	public int solution(int[] A) {
		int triangleNum = 0;
		Arrays.sort(A);
		for (int p = 0; p < A.length; p++) {
			int r = p + 2;
			for (int q = p + 1; q < A.length; q++) {
				while (r < A.length && A[p] + A[q] > A[r]) {
					r++;
				}
				triangleNum += r - q - 1;
			}
		}
		return triangleNum;
	}
}
