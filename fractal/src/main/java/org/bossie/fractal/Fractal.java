package org.bossie.fractal;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Fractal {
	public static void main(String[] args) throws Exception {
		RenderedImage image = mandelbrot(600, 800);
		ImageIO.write(image, "png", new File("/tmp/mandelbrot.png"));
	}

	private static RenderedImage mandelbrot(int rows, int cols) {
		final double xmin = -2.0;
		final double xmax = 1.0;
		final double xstep = (xmax - xmin) / cols;

		final double ymin = -1;
		final double ymax = 1;
		final double ystep = (ymax - ymin) / rows;

		final int maxIt = 0xFF;

		BufferedImage image = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);

		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				int it = iterations(col * xstep + xmin, (rows - row) * ystep + ymin, maxIt);
				image.setRGB(col, row, it << 16 | it << 8 | it);
			}
		}

		return image;
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
		} while (tx * tx + ty * ty <= 4.0 && ++i < max);

		return i;
	}
}
