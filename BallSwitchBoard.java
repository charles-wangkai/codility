public class BallSwitchBoard {
	public int solution(int[][] A, int K) {
		int row = A.length;
		int col = A[0].length;
		Box[] boxes = null;
		for (int i = 0; i < row; i++) {
			Box[] nextBoxes = new Box[col];
			for (int j = 0; j < col; j++) {
				int fromLeft = 0;
				int fromUp = 0;
				if (j != 0) {
					fromLeft = nextBoxes[j - 1].right;
				}
				if (boxes != null) {
					fromUp = boxes[j].down;
				} else if (j == 0) {
					fromUp = K;
				}
				int right;
				int down;
				if (A[i][j] == 0) {
					right = fromLeft;
					down = fromUp;
				} else {
					int total = fromLeft + fromUp;
					if (A[i][j] == 1) {
						down = total / 2;
						right = total - down;
					} else {
						right = total / 2;
						down = total - right;
					}
				}
				nextBoxes[j] = new Box(right, down);
			}
			boxes = nextBoxes;
		}
		return boxes[col - 1].down;
	}
}

class Box {
	int right;
	int down;

	Box(int right, int down) {
		this.right = right;
		this.down = down;
	}
}