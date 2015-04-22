public class SpaceCrews {
	static final int MODULO = 1410000017;
	static final int LIMIT = 1000000;

	static int[] factorials;

	public int solution(int[] T, int[] D) {
		buildFactorials();

		int result = 1;
		for (int i = 0; i < T.length; i++) {
			result = multiplyMod(result, C(T[i], D[i]));
		}
		return result;
	}

	void buildFactorials() {
		factorials = new int[LIMIT + 1];
		int factorial = 1;
		for (int i = 0; i < factorials.length; i++) {
			if (i != 0) {
				factorial = multiplyMod(factorial, i);
			}
			factorials[i] = factorial;
		}
	}

	int multiplyMod(int a, int b) {
		return (int) ((long) a * b % MODULO);
	}

	int C(int n, int m) {
		return multiplyMod(
				multiplyMod(factorials[n],
						computeMultiplicativeInverse(factorials[m])),
				computeMultiplicativeInverse(factorials[n - m]));
	}

	int computeMultiplicativeInverse(int number) {
		return (int) (((long) extendedEuclid(number, MODULO).x + MODULO) % MODULO);
	}

	ExtendedEuclidResult extendedEuclid(int a, int b) {
		if (b == 0) {
			return new ExtendedEuclidResult(a, 1, 0);
		}
		ExtendedEuclidResult eer = extendedEuclid(b, a % b);
		return new ExtendedEuclidResult(eer.r, eer.y, eer.x - a / b * eer.y);
	}
}

class ExtendedEuclidResult {
	int r;
	int x;
	int y;

	public ExtendedEuclidResult(int r, int x, int y) {
		this.r = r;
		this.x = x;
		this.y = y;
	}
}
