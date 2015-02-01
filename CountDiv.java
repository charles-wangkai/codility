public class CountDiv {
	public int solution(int A, int B, int K) {
		int lower = A / K * K;
		if (lower < A) {
			lower += K;
		}
		int upper = B / K * K;
		return (upper - lower) / K + 1;
	}
}
