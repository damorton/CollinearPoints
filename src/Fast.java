import java.util.Arrays;

public class Fast {
	
	private static int delay = 20; // StdDraw delay
	private int numberOfPoints = 4;
		
	/**
	 * Canvas setup. Sets the scale of the canvas, the pen thickness and the
	 * displays the canvas to the screen.
	 */
	public static void canvasSetup() {

		// re scale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenRadius(.002);
	}
	
	/**
	 * Reads the points coordinates from a file and stores them in an array.
	 * 
	 * @param args
	 * @return points is an array of points.
	 */
	public static Point[] readFile(String[] args) {

		// read in the input
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();

		Point[] points = new Point[N]; // array to store points

		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
			points[i].draw();
			StdDraw.show(delay);
		}

		return points; // return the array of points

	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		canvasSetup(); // set up the canvas
		Point[] points = readFile(args); // read points from a file and create an array

		// sort the array
		Arrays.sort(points);
		
		double slopeIJ;
		double slopeJK;
		double slopeKL;

		for (int i = 0; i < points.length; i++) {

			for (int j = 0; j < points.length; j++) {

				// if this point equals the last point go to the next point
				if (j == i)
					continue;

				// get the slope of points i and j
				slopeIJ = points[i].slopeTo(points[j]);

				// if j is less than i go to the next point
				if (points[i].compareTo(points[j]) > -1)
					continue;

				for (int k = 0; k < points.length; k++) {

					// if this point equals the last point go to the next point
					if (k == j)
						continue;

					// if k is less than j go to next point
					if (points[j].compareTo(points[k]) > -1)
						continue;

					// get the slope of points j and k
					slopeJK = points[j].slopeTo(points[k]);

					// if the slopes are not equal go to the next point
					if (slopeJK != slopeIJ)
						continue;

					for (int l = 0; l < points.length; l++) {

						// if this point is equal to the last point go to the
						// next point
						if (l == k)
							continue;

						// if l is less than k go to the next point
						if (points[k].compareTo(points[l]) > -1)
							continue;

						// get the slope of points k and l
						slopeKL = points[k].slopeTo(points[l]);

						// if the slopes are not equal go to the next point
						if (slopeKL != slopeJK)
							continue;

						// System.out.printf("collinear line found!\n");

						points[i].drawTo(points[j]);
						StdDraw.show(delay);
						points[j].drawTo(points[k]);
						StdDraw.show(delay);
						points[k].drawTo(points[l]);
						StdDraw.show(delay);

						System.out.printf("%s -> %s -> %s -> %s\n", points[i],
								points[j], points[k], points[l]);

					}
				}
			}
		}
	}

}
