public class AbsDistinct {
	public int solution(int[] A) {
		int absDistinctNum = 0;
		long previous = Long.MAX_VALUE;
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			long leftAbs = Math.abs((long) A[left]);
			long rightAbs = Math.abs((long) A[right]);
			long current;
			if (leftAbs >= rightAbs) {
				current = leftAbs;
				left++;
			} else {
				current = rightAbs;
				right--;
			}
			if (current != previous) {
				absDistinctNum++;
			}
			previous = current;
		}
		return absDistinctNum;
	}
}
