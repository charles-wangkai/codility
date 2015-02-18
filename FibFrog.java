import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibFrog {
	public int solution(int[] A) {
		List<Integer> steps = new ArrayList<Integer>();
		steps.add(1);
		steps.add(1);
		while (true) {
			int nextStep = steps.get(steps.size() - 2)
					+ steps.get(steps.size() - 1);
			if (nextStep > A.length + 1) {
				break;
			}
			steps.add(nextStep);
		}

		int[] minJumpNums = new int[A.length + 2];
		Arrays.fill(minJumpNums, Integer.MAX_VALUE);
		minJumpNums[0] = 0;
		for (int i = 0; i < minJumpNums.length; i++) {
			if (i == 0 || i == minJumpNums.length - 1 || A[i - 1] == 1) {
				for (int step : steps) {
					if (step > i) {
						break;
					}
					minJumpNums[i] = (int) Math.min(minJumpNums[i],
							(long) minJumpNums[i - step] + 1);
				}
			}
		}
		return minJumpNums[minJumpNums.length - 1] == Integer.MAX_VALUE ? -1
				: minJumpNums[minJumpNums.length - 1];
	}
}
