public class FrogJmp {
	public int solution(int X, int Y, int D) {
		return divideToCeil(Y - X, D);
	}

	int divideToCeil(int a, int b) {
		return a / b + ((a % b == 0) ? 0 : 1);
	}
}
