package task1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
@AllArgsConstructor
public class ChunkResult {

    private BufferedImage image;

    private int x;
    private int y;
}
