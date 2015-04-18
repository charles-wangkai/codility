import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestAdjSeq {
	static final int[] OFFSETS = { -1, 1 };

	public int solution(int[] A) {
		Map<Integer, List<Integer>> number2indices = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < A.length; i++) {
			if (!number2indices.containsKey(A[i])) {
				number2indices.put(A[i], new ArrayList<Integer>());
			}
			number2indices.get(A[i]).add(i);
		}

		Set<Integer> visited = new HashSet<Integer>();
		Set<Integer> numbers = new HashSet<Integer>();
		numbers.add(A[0]);
		for (int length = 1;; length++) {
			Set<Integer> nextNumbers = new HashSet<Integer>();
			for (int number : numbers) {
				if (number == A[A.length - 1]) {
					return length;
				}
				visited.add(number);
				for (int index : number2indices.get(number)) {
					for (int offset : OFFSETS) {
						int nextIndex = index + offset;
						if (nextIndex >= 0 && nextIndex < A.length
								&& !visited.contains(A[nextIndex])) {
							nextNumbers.add(A[nextIndex]);
						}
					}
				}
			}
			numbers = nextNumbers;
		}
	}
}
