import java.util.Arrays;

public class KSparseBinaryCount {
	static final int MODULO = 1000000007;
	int[] F;

	public int solution(String S, String T, int K) {
		sparseBinaryCount(T.length() + 1, K);
		return subtractMod(below(increase(T), K), below(S, K));
	}

	void sparseBinaryCount(int length, int K) {
		F = new int[length];
		F[0] = 1;
		for (int i = 1; i < length; i++) {
			if (i > K) {
				F[i] = addMod(F[i - 1], F[i - K - 1]);
			} else {
				F[i] = addMod(F[i - 1], 1);
			}
		}
	}

	int addMod(int a, int b) {
		return (a + b) % MODULO;
	}

	int subtractMod(int a, int b) {
		return addMod(a, -b + MODULO);
	}

	String increase(String str) {
		str = '0' + str;
		int lastZeroIndex = str.lastIndexOf('0');
		return str.substring(0, lastZeroIndex) + '1'
				+ repeatChars('0', str.length() - lastZeroIndex - 1);
	}

	String repeatChars(char ch, int times) {
		char[] letters = new char[times];
		Arrays.fill(letters, ch);
		return new String(letters);
	}

	int below(String N, int K) {
		N = '0' + N;
		boolean kSparse = true;
		int countZero = K + 1;
		int j = 0;
		for (int i = 0; i < N.length(); i++) {
			if (N.charAt(i) == '1') {
				if (countZero < K) {
					kSparse = false;
					break;
				} else if (countZero > K) {
					j = i;
				}
				countZero = 0;
			} else {
				countZero++;
			}
		}
		if (!kSparse) {
			N = N.substring(0, j - 1) + '1' + repeatChars('0', N.length() - j);
		}
		return belowKSparse(N, K);
	}

	int belowKSparse(String N, int K) {
		int result = 0;
		for (int i = 0; i < N.length(); i++) {
			if (N.charAt(i) == '1') {
				result = addMod(result, F[N.length() - i - 1]);
			}
		}
		return result;
	}
}
