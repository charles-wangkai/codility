public class TorusLot {
	public int solution(int[][] C) {
		int row = C.length;
		int col = C[0].length;
		int maxProfit = 0;
		for (int i = 0; i < row; i++) {
			int[] columnSums = new int[col];
			for (int j = 0; j < row; j++) {
				for (int k = 0; k < col; k++) {
					columnSums[k] += C[(i + j) % row][k];
				}
				maxProfit = Math.max(maxProfit,
						findRotatedMaxSubsetSum(columnSums));
			}
		}
		return maxProfit;
	}

	int findRotatedMaxSubsetSum(int[] a) {
		return Math.max(findMaxSubsetSum(a), findSidedSubsetSum(a));
	}

	int findMaxSubsetSum(int[] a) {
		int maxSum = 0;
		int sum = 0;
		for (int elem : a) {
			sum = Math.max(0, sum + elem);
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}

	int findSidedSubsetSum(int[] a) {
		int[] leftSums = new int[a.length + 1];
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			leftSums[i + 1] = Math.max(leftSums[i], sum);
		}

		int[] rightSums = new int[a.length + 1];
		sum = 0;
		for (int i = a.length - 1; i >= 0; i--) {
			sum += a[i];
			rightSums[a.length - i] = Math
					.max(rightSums[a.length - i - 1], sum);
		}

		int maxSum = 0;
		for (int leftLength = 0; leftLength <= a.length; leftLength++) {
			maxSum = Math.max(maxSum, leftSums[leftLength]
					+ rightSums[a.length - leftLength]);
		}
		return maxSum;
	}
}
