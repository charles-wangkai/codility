import java.util.Arrays;
import java.util.Comparator;

public class NumberOfDiscIntersections {
	static final int LIMIT = 10000000;

	public int solution(int[] A) {
		Point[] points = new Point[A.length * 2];
		for (int i = 0; i < A.length; i++) {
			points[i * 2] = new Point((long) i - A[i], Type.LOWER);
			points[i * 2 + 1] = new Point((long) i + A[i], Type.UPPER);
		}

		Arrays.sort(points, new PointComparator());

		int intersectNum = 0;
		int openedNum = 0;
		for (Point point : points) {
			if (point.type.equals(Type.LOWER)) {
				intersectNum += openedNum;
				if (intersectNum > LIMIT) {
					return -1;
				}
				openedNum++;
			} else {
				openedNum--;
			}
		}
		return intersectNum;
	}
}

class PointComparator implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		if (p1.y != p2.y) {
			return (int) Math.signum(p1.y - p2.y);
		}
		return p1.type.equals(Type.LOWER) ? -1 : 1;
	}
}

class Point {
	long y;
	Type type;

	Point(long y, Type type) {
		this.y = y;
		this.type = type;
	}
}

enum Type {
	LOWER, UPPER
}