package task1;

import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@AllArgsConstructor
public class Task implements Callable<TaskResult> {

    private final int MAX_ITER = 570;

    private int startX;
    private int startY;
    private int width;
    private int height;

    private double zoom;
    private ExecutorService executorService;

    @Override
    public TaskResult call() {
        double zx, zy, cX, cY, tmp;

        TaskResult taskResult = new TaskResult(startX, startY, width, height);

        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                zx = zy = 0;
                cX = (x - 400) / zoom;
                cY = (y - 300) / zoom;

                int iter = MAX_ITER;

                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }

                taskResult.setImageRGB(x - startX, y - startY, iter | (iter << 8));
            }
        }

        return taskResult;
    }

    public Future<TaskResult> getTaskResult() {
        return executorService.submit(this);
    }
}
