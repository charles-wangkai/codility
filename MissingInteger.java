import java.util.HashSet;
import java.util.Set;

public class MissingInteger {
	public int solution(int[] A) {
		Set<Integer> numbers = new HashSet<Integer>();
		for (int number : A) {
			numbers.add(number);
		}
		for (int i = 1;; i++) {
			if (!numbers.contains(i)) {
				return i;
			}
		}
	}
}
