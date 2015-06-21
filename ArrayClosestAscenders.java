import java.util.Stack;

public class ArrayClosestAscenders {
	public int[] solution(int[] A) {
		int[] leftAscenderIndices = new int[A.length];
		Stack<Integer> ascenderIndices = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			while (!ascenderIndices.isEmpty()
					&& A[ascenderIndices.peek()] <= A[i]) {
				ascenderIndices.pop();
			}
			if (ascenderIndices.isEmpty()) {
				leftAscenderIndices[i] = -1;
			} else {
				leftAscenderIndices[i] = ascenderIndices.peek();
			}
			ascenderIndices.push(i);
		}

		int[] rightAscenderIndices = new int[A.length];
		ascenderIndices.clear();
		for (int i = A.length - 1; i >= 0; i--) {
			while (!ascenderIndices.isEmpty()
					&& A[ascenderIndices.peek()] <= A[i]) {
				ascenderIndices.pop();
			}
			if (ascenderIndices.isEmpty()) {
				rightAscenderIndices[i] = -1;
			} else {
				rightAscenderIndices[i] = ascenderIndices.peek();
			}
			ascenderIndices.push(i);
		}

		int[] result = new int[A.length];
		for (int i = 0; i < result.length; i++) {
			int leftDistance = leftAscenderIndices[i] == -1 ? Integer.MAX_VALUE
					: (i - leftAscenderIndices[i]);
			int rightDistance = rightAscenderIndices[i] == -1 ? Integer.MAX_VALUE
					: (rightAscenderIndices[i] - i);
			int minDistance = Math.min(leftDistance, rightDistance);
			result[i] = (minDistance == Integer.MAX_VALUE ? 0 : minDistance);
		}
		return result;
	}
}
