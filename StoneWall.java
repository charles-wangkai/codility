import java.util.Arrays;
import java.util.Stack;

public class StoneWall {
	public int solution(int[] H) {
		int[] heights = Arrays.copyOf(H, H.length + 1);
		Stack<Integer> increasingHeights = new Stack<Integer>();
		int blockNum = 0;
		for (int height : heights) {
			while (!increasingHeights.empty()
					&& increasingHeights.peek() >= height) {
				if (increasingHeights.peek() > height) {
					blockNum++;
				}
				increasingHeights.pop();
			}
			increasingHeights.push(height);
		}
		return blockNum;
	}
}
