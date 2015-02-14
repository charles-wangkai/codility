public class ChocolatesByNumbers {
	public int solution(int N, int M) {
		return N / gcd(N, M);
	}

	int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
