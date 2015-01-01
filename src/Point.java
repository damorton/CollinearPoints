import java.util.Comparator;

/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

		@Override
		public int compare(Point point0, Point point1) {

			double slope0 = slopeTo(point0);
			double slope1 = slopeTo(point1);
			return Double.compare(slope0, slope1);
		}

	};

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {

		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {

		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {

		double y0 = this.y;
		double y1 = that.y;
		double x0 = this.x;
		double x1 = that.x;

		if (this.equals(that))
			return Double.NEGATIVE_INFINITY; // points are the same

		if (y1 - y0 == 0.0)
			return 0.0; // horizontal line segment

		if (x1 - x0 == 0.0)
			return Double.POSITIVE_INFINITY; // vertical line segments

		return (y1 - y0) / (x1 - x0);

	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {

		if (this.y < that.y || (this.y == that.y && this.x < that.x))
			return -1; // this point is less than that point

		if (this.x == that.x && this.y == that.y)
			return 0; // this point is equal to that point

		return 1; // this point is greater than that point
	}

	// return string representation of this point
	public String toString() {

		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {

		Point p;
		Point q;

		p = new Point(10000, 1000);
		System.out.printf("degenerate\t%f\n", p.slopeTo(p));

		p = new Point(10000, 1000);
		q = new Point(5000, 1000);
		System.out.printf("horizontal\t%f\n", p.slopeTo(q));

		p = new Point(1000, 10000);
		q = new Point(1000, 5000);
		System.out.printf("vertical\t%f\n", p.slopeTo(q));

	}
}
