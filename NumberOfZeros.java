public class NumberOfZeros {
	final int LIMIT_L = 10000;
	final int MODULO = 1410000017;

	int[] tenPowers;
	int[] leftNumbers;
	int[] rightNumbers;

	public int solution(String S) {
		buildTenPowers();
		buildLeftNumbers(S);
		buildRightNumbers(S);

		int zeroNum = 1;
		for (int i = 1; i < S.length(); i++) {
			int wholeRight = getTenPower(S.length() - i - 1);
			int addition = multiplyMod(addMod(getLeftNumber(i - 1), -1),
					wholeRight);
			int digit = S.charAt(i) - '0';
			if (digit == 0) {
				addition = addMod(addition, addMod(getRightNumber(i + 1), 1));
			} else if (digit > 0) {
				addition = addMod(addition, wholeRight);
			}
			zeroNum = addMod(zeroNum, addition);
		}
		return zeroNum;
	}

	int addMod(int x, int y) {
		return (int) (((long) x + y + MODULO) % MODULO);
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULO);
	}

	void buildTenPowers() {
		tenPowers = new int[LIMIT_L];
		tenPowers[0] = 1;
		for (int i = 1; i < tenPowers.length; i++) {
			tenPowers[i] = multiplyMod(tenPowers[i - 1], 10);
		}
	}

	void buildLeftNumbers(String S) {
		leftNumbers = new int[S.length()];
		int leftNumber = 0;
		for (int i = 0; i < leftNumbers.length; i++) {
			leftNumber = addMod(multiplyMod(leftNumber, 10), S.charAt(i) - '0');
			leftNumbers[i] = leftNumber;
		}
	}

	void buildRightNumbers(String S) {
		int rightNumber = 0;
		rightNumbers = new int[S.length()];
		for (int i = S.length() - 1; i >= 0; i--) {
			rightNumber = addMod(
					rightNumber,
					multiplyMod(S.charAt(i) - '0', getTenPower(S.length() - i
							- 1)));
			rightNumbers[i] = rightNumber;
		}
	}

	int getTenPower(int power) {
		return tenPowers[power];
	}

	int getLeftNumber(int index) {
		return (index >= 0) ? leftNumbers[index] : 0;
	}

	int getRightNumber(int index) {
		return (index < rightNumbers.length) ? rightNumbers[index] : 0;
	}
}
