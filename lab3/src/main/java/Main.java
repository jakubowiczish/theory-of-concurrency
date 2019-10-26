import task1.FirstTaskExecutioner;
import task2.SecondTaskExecutioner;
import task3.ThirdTaskExecutioner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        FirstTaskExecutioner.executeBoundedBufferOnProducersAndConsumers();
//        SecondTaskExecutioner.executePrintersMonitor();
        ThirdTaskExecutioner.executeWaitersMonitor();
    }
}
