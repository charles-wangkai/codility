class Tree {
	public int x;
	public Tree l;
	public Tree r;
}

public class TreeHeight {
	public int solution(Tree T) {
		if (T == null) {
			return -1;
		}
		return 1 + Math.max(solution(T.l), solution(T.r));
	}
}
