import java.util.Stack;

public class Fish {
	public int solution(int[] A, int[] B) {
		Stack<Integer> livingIndices = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (B[i] == 0) {
				while (!livingIndices.empty() && B[livingIndices.peek()] == 1
						&& A[i] > A[livingIndices.peek()]) {
					livingIndices.pop();
				}
				if (!(!livingIndices.empty() && B[livingIndices.peek()] == 1)) {
					livingIndices.push(i);
				}
			} else {
				livingIndices.push(i);
			}
		}
		return livingIndices.size();
	}
}
