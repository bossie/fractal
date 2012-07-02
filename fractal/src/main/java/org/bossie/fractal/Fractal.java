package org.bossie.fractal;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Fractal {
	public static void main(String[] args) throws Exception {
		RenderedImage image = mandelbrot(800, 700);

		File out = new File("/tmp/mandelbrot.png");
		ImageIO.write(image, "png", out);

		System.out.println("Done writing " + out);
	}

	private static RenderedImage mandelbrot(int width, int height) {
		final double xmin = -2.05;
		final double xmax = 0.5;
		final double xstep = (xmax - xmin) / width;

		final double ymin = -1.1;
		final double ymax = 1.1;
		final double ystep = (ymax - ymin) / height;

		final int maxIt = 0xFF;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				int it = iterations(i * xstep + xmin, (height - j) * ystep + ymin, maxIt);
				image.setRGB(i, j, color(it));
			}
		}

		return image;
	}

	private static int color(int it) {
		return it << 16 | it << 8 | it;
	}

	private static int iterations(double x0, double y0, int max) {
		double x = 0;
		double y = 0;

		int it = 0;

		do {
			double tx = x * x - y * y + x0;
			double ty = 2 * x * y + y0;

			x = tx;
			y = ty;
		} while (++it < max && x * x + y * y <= 4.0);

		return it;
	}
}
