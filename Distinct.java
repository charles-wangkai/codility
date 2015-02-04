import java.util.HashSet;
import java.util.Set;

public class Distinct {
	public int solution(int[] A) {
		Set<Integer> numberSet = new HashSet<Integer>();
		for (int number : A) {
			numberSet.add(number);
		}
		return numberSet.size();
	}
}
