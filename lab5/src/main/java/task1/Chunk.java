package task1;

import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

@AllArgsConstructor
public class Chunk implements Callable<ChunkResult> {

    private final int MAX_ITER = 570;
    private final double ZOOM = 150;

    private int givenX;
    private int givenY;
    private int width;
    private int height;

    @Override
    public ChunkResult call() throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = givenY; y < height + givenY; y++) {
            for (int x = givenX; x < width + givenX; x++) {
                double zx = 0, zy = 0;
                double cX = (x - 400) / ZOOM;
                double cY = (y - 300) / ZOOM;

                int iter = MAX_ITER;

                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }

                image.setRGB(x - givenX, y - givenY, iter | (iter << 8));
            }
        }

        return new ChunkResult(image, givenX, givenY);
    }
}
