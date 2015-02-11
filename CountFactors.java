public class CountFactors {
	public int solution(int N) {
		int factorNum = 0;
		int limit = (int) Math.round(Math.sqrt(N));
		if (limit * limit == N) {
			factorNum++;
		}
		if (limit * limit >= N) {
			limit--;
		}
		for (int i = 1; i <= limit; i++) {
			if (N % i == 0) {
				factorNum += 2;
			}
		}
		return factorNum;
	}
}
