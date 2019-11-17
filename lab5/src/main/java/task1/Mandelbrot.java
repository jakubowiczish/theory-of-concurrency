package task1;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mandelbrot extends JFrame {

    private int taskWidth;
    private int taskHeight;

    private List<Future<TaskResult>> taskResultList;
    private List<TaskResult> resultsToBeDrawn;

    private ExecutorService executorService;

    private int numberOfThreads, numberOfTasks;

    public Mandelbrot(boolean isPixelModeEnabled, int numberOfThreads, int numberOfTasks) {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.numberOfThreads = numberOfThreads;
        this.numberOfTasks = numberOfTasks;

        this.taskWidth = isPixelModeEnabled ? 1 : getWidth();
        this.taskHeight = isPixelModeEnabled ? 1 : getHeight() / numberOfTasks;
        this.taskResultList = new LinkedList<>();
        this.resultsToBeDrawn = new LinkedList<>();
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("Threads: " + numberOfThreads + ", tasks: " + numberOfTasks);

        for (int i = 0; i < 11; i++) { // 11 - ignore first one
            clearLists();
            long startTime = System.currentTimeMillis();

            addValuesToTaskResultList();
            addValuesToResultsToBeDrawnList();

            long resultTime = System.currentTimeMillis() - startTime;

            System.out.println(resultTime);
            drawImage(g);
        }
    }

    private void clearLists() {
        taskResultList.clear();
        resultsToBeDrawn.clear();
    }

    private void addValuesToTaskResultList() {
        final double ZOOM = 150;

        for (int y = 0; y < getHeight(); y += taskHeight) {
            for (int x = 0; x < getWidth(); x += taskWidth) {
                taskResultList.add(new Task(x, y, taskWidth, taskHeight, ZOOM, executorService)
                        .getTaskResult());
            }
        }
    }

    private void addValuesToResultsToBeDrawnList() {
        for (Future<TaskResult> taskResult : taskResultList) {
            try {
                resultsToBeDrawn.add(taskResult.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawImage(Graphics g) {
        for (TaskResult taskResult : resultsToBeDrawn) {
            g.drawImage(taskResult.getImage(), taskResult.getX(), taskResult.getY(), this);
        }
    }
}
