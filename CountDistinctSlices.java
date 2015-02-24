public class CountDistinctSlices {
	static final int LIMIT = 1000000000;

	public int solution(int M, int[] A) {
		boolean[] used = new boolean[M + 1];
		int distinctSliceNum = 0;
		int back = 0;
		for (int front = 0; front < A.length; front++) {
			while (used[A[front]]) {
				used[A[back]] = false;
				back++;
			}
			used[A[front]] = true;
			distinctSliceNum += front - back + 1;
			if (distinctSliceNum > LIMIT) {
				return LIMIT;
			}
		}
		return distinctSliceNum;
	}
}
