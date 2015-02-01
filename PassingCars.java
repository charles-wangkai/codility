public class PassingCars {
	static final int LIMIT = 1000000000;

	public int solution(int[] A) {
		int passingNum = 0;
		int zeroCount = 0;
		for (int elem : A) {
			if (elem == 0) {
				zeroCount++;
			} else {
				passingNum += zeroCount;
				if (passingNum > LIMIT) {
					return -1;
				}
			}
		}
		return passingNum;
	}
}
