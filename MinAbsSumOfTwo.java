import java.util.Arrays;

public class MinAbsSumOfTwo {
	public int solution(int[] A) {
		int result = Integer.MAX_VALUE;
		Arrays.sort(A);
		for (int number : A) {
			int index = Arrays.binarySearch(A, -number);
			if (index < 0) {
				index = -1 - index;
			}
			if (index < A.length) {
				result = Math.min(result, Math.abs(number + A[index]));
			}
			if (index >= 1) {
				result = Math.min(result, Math.abs(number + A[index - 1]));
			}
		}
		return result;
	}
}
