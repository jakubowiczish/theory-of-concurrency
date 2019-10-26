package task2;

import java.util.ArrayList;
import java.util.List;

public class SecondTaskExecutioner {

    public static void executePrintersMonitor() throws InterruptedException {
        PrintersMonitor printersMonitor = new PrintersMonitor(3);
        createTaskToPrint(printersMonitor, 5);
    }

    private static void createTaskToPrint(PrintersMonitor printersMonitor, int numberOfPrinters) throws InterruptedException {
        List<Thread> printers = new ArrayList<>();
        for (int i = 0; i < numberOfPrinters; i++) {
            printers.add(new Thread(new Customer(i, printersMonitor)));
        }

        for (int i = 0; i < numberOfPrinters; i++) {
            printers.get(i).start();
        }

        for (int i = 0; i < numberOfPrinters; i++) {
            printers.get(i).join();
        }
    }
}
