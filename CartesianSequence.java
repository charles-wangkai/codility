import java.util.Arrays;
import java.util.Stack;

public class CartesianSequence {
	public int solution(int[] A) {
		int[] values = Arrays.copyOf(A, A.length + 1);
		values[values.length - 1] = Integer.MAX_VALUE;
		int maxHeight = 0;
		Stack<ValueNHeight> stack = new Stack<ValueNHeight>();
		for (int value : values) {
			ValueNHeight vh = new ValueNHeight(value, 1);
			while (!stack.isEmpty() && stack.peek().value < value) {
				vh.height = Math.max(vh.height, stack.pop().height) + 1;
			}
			stack.push(vh);
			maxHeight = Math.max(maxHeight, vh.height);
		}
		return maxHeight - 1;
	}
}

class ValueNHeight {
	int value;
	int height;

	ValueNHeight(int value, int height) {
		this.value = value;
		this.height = height;
	}
}