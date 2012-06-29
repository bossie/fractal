package org.bossie.fractal;

public class Fractal {
	public static void main(String[] args) {
		System.out.println(mandelbrot(50, 160));
	}

	private static String mandelbrot(int rows, int cols) {
		StringBuilder sb = new StringBuilder();

		final double xmin = -2.0;
		final double xmax = 1.0;
		final double xstep = (xmax - xmin) / cols;

		final double ymin = -1;
		final double ymax = 1;
		final double ystep = (ymax - ymin) / rows;

		final int maxIt = 255;

		for (double y = ymax; y >= ymin; y -= ystep) {
			for (double x = xmin; x <= xmax; x += xstep) {
				sb.append(iterations(x, y, maxIt) >= maxIt ? '#' : ' ');
			}

			sb.append('\n');
		}

		return sb.toString();
	}

	private static int iterations(double x0, double y0, int max) {
		double x = 0;
		double y = 0;

		int i = 0;
		double tx, ty;

		do {
			tx = x * x - y * y + x0;
			ty = 2 * x * y + y0;

			x = tx;
			y = ty;
		} while (tx * tx + ty * ty <= 4.0 && i++ < max);

		return i;
	}
}
