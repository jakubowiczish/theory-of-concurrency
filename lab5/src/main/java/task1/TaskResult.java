package task1;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class TaskResult {

    private BufferedImage image;
    private final int x;
    private final int y;

    public TaskResult(final int x, final int y, int width, int height) {
        this.x = x;
        this.y = y;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void setImageRGB(int x, int y, int val) {
        this.image.setRGB(x, y, val);
    }
}
