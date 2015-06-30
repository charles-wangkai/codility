public class TilingsCount {
	static final int MODULO = 10000007;

	public int solution(int N, int M) {
		int[][] transition = buildTransition(M);
		int[] tilingNums = initTilingNums(M);
		tilingNums = multiply(tilingNums, pow(transition, N));
		return tilingNums[0];
	}

	int[][] buildTransition(int M) {
		int stateNum = 1 << M;
		int[][] transition = new int[stateNum][stateNum];
		for (int code = 0; code < stateNum; code++) {
			search(transition, code, decode(code, M), 0);
		}
		return transition;
	}

	void search(int[][] transition, int from, int[] state, int index) {
		if (index == state.length) {
			transition[from][encode(state)]++;
			return;
		}

		int original = state[index];
		state[index] = 0;
		search(transition, from, state, index + 1);
		state[index] = original;

		if (index < state.length - 1 && state[index] == 0
				&& state[index + 1] == 0) {
			state[index] = 1;
			state[index + 1] = 1;
			search(transition, from, state, index + 2);
			state[index + 1] = 0;
			state[index] = 0;
		}
	}

	int encode(int[] state) {
		int code = 0;
		for (int square : state) {
			code = code * 2 + square;
		}
		return code;
	}

	int[] decode(int code, int size) {
		int[] state = new int[size];
		for (int i = state.length - 1; i >= 0; i--) {
			state[i] = code % 2;
			code /= 2;
		}
		return state;
	}

	int[] initTilingNums(int M) {
		int[] tilingNums = new int[1 << M];
		tilingNums[0] = 1;
		return tilingNums;
	}

	int[][] pow(int[][] matrix, int exponent) {
		int size = matrix.length;
		int[][] result = new int[size][size];
		for (int i = 0; i < size; i++) {
			result[i][i] = 1;
		}
		while (exponent != 0) {
			if (exponent % 2 != 0) {
				result = multiply(result, matrix);
			}
			matrix = multiply(matrix, matrix);
			exponent /= 2;
		}
		return result;
	}

	int[][] multiply(int[][] a, int[][] b) {
		int rowA = a.length;
		int colA = a[0].length;
		int colB = b[0].length;
		int[][] result = new int[rowA][colB];
		for (int i = 0; i < rowA; i++) {
			for (int j = 0; j < colB; j++) {
				for (int k = 0; k < colA; k++) {
					result[i][j] = addMod(result[i][j],
							multiplyMod(a[i][k], b[k][j]));
				}
			}
		}
		return result;
	}

	int[] multiply(int[] vectorA, int[][] matrixB) {
		return multiply(new int[][] { vectorA }, matrixB)[0];
	}

	int addMod(int a, int b) {
		return (a + b) % MODULO;
	}

	int multiplyMod(int a, int b) {
		return (int) ((long) a * b % MODULO);
	}
}
