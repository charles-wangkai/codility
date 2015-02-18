public class Ladder {
	static final int LIMIT = 30000;
	static final int MODULO = 1 << 30;

	public int[] solution(int[] A, int[] B) {
		int[] fibonacci = new int[LIMIT + 1];
		fibonacci[0] = 1;
		fibonacci[1] = 1;
		for (int i = 2; i < fibonacci.length; i++) {
			fibonacci[i] = (fibonacci[i - 2] + fibonacci[i - 1]) % MODULO;
		}

		int[] ways = new int[A.length];
		for (int i = 0; i < ways.length; i++) {
			ways[i] = fibonacci[A[i]] % (1 << B[i]);
		}
		return ways;
	}
}
