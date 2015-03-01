import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Minfuds {
	static final int INFINITE = 2001;
	static final double ERROR = 1E-9;

	public double solution(int[] A, int[] B) {
		Line[] lines = new Line[A.length];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new Line(A[i], B[i]);
		}

		List<Point> maxPoints = computeExtremePoints(lines,
				(line1, line2) -> (int) Math.signum(line1.a - line2.a),
				yDiffLeftEnd -> yDiffLeftEnd < 0);
		List<Point> minPoints = computeExtremePoints(lines,
				(line1, line2) -> (int) Math.signum(line2.a - line1.a),
				yDiffLeftEnd -> yDiffLeftEnd > 0);
		return computeMinDiff(maxPoints, minPoints);
	}

	double computeMinDiff(List<Point> maxPoints, List<Point> minPoints) {
		double minDiff = Double.MAX_VALUE;
		int maxPointsIndex = 0;
		int minPointsIndex = 0;
		while (maxPointsIndex < maxPoints.size()
				|| minPointsIndex < minPoints.size()) {
			double diff;
			if (minPointsIndex == minPoints.size()
					|| (maxPointsIndex < maxPoints.size() && maxPoints
							.get(maxPointsIndex).x <= minPoints
							.get(minPointsIndex).x)) {
				int leftEndIndex = findLeftEndIndex(minPointsIndex,
						minPoints.size());
				Line minLine = new Line(minPoints.get(leftEndIndex),
						minPoints.get(leftEndIndex + 1));
				diff = maxPoints.get(maxPointsIndex).y
						- minLine.getY(maxPoints.get(maxPointsIndex).x);
				maxPointsIndex++;
			} else {
				int leftEndIndex = findLeftEndIndex(maxPointsIndex,
						maxPoints.size());
				Line maxLine = new Line(maxPoints.get(leftEndIndex),
						maxPoints.get(leftEndIndex + 1));
				diff = maxLine.getY(minPoints.get(minPointsIndex).x)
						- minPoints.get(minPointsIndex).y;
				minPointsIndex++;
			}
			minDiff = Math.min(minDiff, diff);
		}
		return minDiff;
	}

	int findLeftEndIndex(int pointsIndex, int pointsSize) {
		return Math.min(Math.max(pointsIndex - 1, 0), pointsSize - 2);
	}

	List<Point> computeExtremePoints(Line[] lines, Comparator<Line> comparator,
			ExtremeChecker extremeChecker) {
		Arrays.sort(lines, comparator);

		List<Point> extremePoints = new ArrayList<Point>();
		for (Line line : lines) {
			while (true) {
				if (extremePoints.isEmpty()) {
					extremePoints
							.add(new Point(-INFINITE, line.getY(-INFINITE)));
					extremePoints.add(new Point(INFINITE, line.getY(INFINITE)));
					break;
				}

				Point lastSecondPoint = extremePoints
						.get(extremePoints.size() - 2);
				Point lastPoint = extremePoints.get(extremePoints.size() - 1);
				double yDiffLeftEnd = line.getY(lastSecondPoint.x)
						- lastSecondPoint.y;
				if (extremeChecker.check(yDiffLeftEnd)) {
					if (!isParallel(lastSecondPoint, lastPoint, line)) {
						extremePoints.remove(extremePoints.size() - 1);
						extremePoints.add(intersect(line, new Line(
								lastSecondPoint, lastPoint)));
						extremePoints.add(new Point(INFINITE, line
								.getY(INFINITE)));
					}
					break;
				}
				extremePoints.remove(extremePoints.size() - 1);

				if (extremePoints.size() == 1) {
					extremePoints.remove(0);
				}
			}
		}
		return extremePoints;
	}

	boolean isParallel(Point lastSecondPoint, Point lastPoint, Line line) {
		return Math.abs(new Line(lastSecondPoint, lastPoint).a - line.a) < ERROR;
	}

	Point intersect(Line line1, Line line2) {
		double x = (line2.b - line1.b) / (line1.a - line2.a);
		double y = line1.a * x + line1.b;
		return new Point(x, y);
	}
}

@FunctionalInterface
interface ExtremeChecker {
	boolean check(double yDiffLeftEnd);
}

class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

// y = a * x + b
class Line implements Comparable<Line> {
	double a;
	double b;

	Line(double a, double b) {
		this.a = a;
		this.b = b;
	}

	Line(Point p1, Point p2) {
		a = (p1.y - p2.y) / (p1.x - p2.x);
		b = p1.y - a * p1.x;
	}

	@Override
	public int compareTo(Line other) {
		return (int) Math.signum(a - other.a);
	}

	double getY(double x) {
		return a * x + b;
	}
}