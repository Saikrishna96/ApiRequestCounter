import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new HitCounterList());
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error");
        }

        System.out.println("HC1 Count : " + HitCounterList.countRequestsInLastOneMinute());

        System.out.println("\n\n\n");
        for (int i = 0; i < 100; i++) {
            executorService.execute(new HitCounterQueue());
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error");
        }

        System.out.println("HC2 Count : " + HitCounterQueue.countRequestsInLastOneMinute());
        executorService.shutdown();

    }
}
