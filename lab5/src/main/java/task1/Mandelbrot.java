package task1;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 150;

    private final int imageHeight = 600;
    private final int imageWidth = 800;

    private final int numberOfChunksX;
    private final int numberOfChunksY;

    private List<ChunkResult> chunkResultList;

    public Mandelbrot(int numberOfChunksX, int numberOfChunksY) {
        super("Mandelbrot Set");

        this.numberOfChunksX = numberOfChunksX;
        this.numberOfChunksY = numberOfChunksY;

        chunkResultList = new LinkedList<>();

        setBounds(100, 100, imageWidth, imageHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletionService<ChunkResult> completionService = new ExecutorCompletionService<>(executor);

        long startTime = System.currentTimeMillis();

        submitChunks(completionService);
        addResultsToChunkResultList(completionService);

        long resultTime = System.currentTimeMillis() - startTime;

        logCalculationTime(resultTime);
        drawImage(g);
    }

    private void submitChunks(CompletionService<ChunkResult> completionService) {
        int chunkWidth = imageWidth / numberOfChunksX;
        int chunkHeight = imageHeight / numberOfChunksY;

        for (int y = 0; y < numberOfChunksY; y++) {
            for (int x = 0; x < numberOfChunksX; x++) {
                int iX = x * chunkWidth;
                int iY = y * chunkHeight;

                Chunk chunk = new Chunk(iX, iY, chunkWidth, chunkHeight);
                completionService.submit(chunk);
            }
        }
    }

    private void addResultsToChunkResultList(CompletionService<ChunkResult> completionService) {
        for (int i = 0; i < numberOfChunksY * numberOfChunksX; ++i) {
            try {
                Future<ChunkResult> result = completionService.take();
                ChunkResult chunkResult = result.get();
                chunkResultList.add(chunkResult);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawImage(Graphics g) {
        for (ChunkResult chunkResult : chunkResultList) {
            g.drawImage(chunkResult.getImage(), chunkResult.getX(), chunkResult.getY(), this);
        }
    }

    private void logCalculationTime(long calculationTime) {
        System.out.println("Time of calculation: " + calculationTime);
    }
}
