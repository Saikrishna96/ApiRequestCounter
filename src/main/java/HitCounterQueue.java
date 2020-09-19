import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class HitCounterQueue implements Runnable {
    public static Queue<LocalDateTime> timestamps = new ArrayDeque<>();

    //synchronized to overcome the thread race condition
    public static synchronized void inc() {
        System.out.println("Request Received by HC2");
        timestamps.add(LocalDateTime.now());
    }

    public static int countRequestsInLastOneMinute() {
        LocalDateTime lastOneMinute = LocalDateTime.now().minusMinutes(1);
        List<LocalDateTime> filteredTimestamps = timestamps
                .stream().filter(curTimeStamp -> curTimeStamp.isAfter(lastOneMinute))
                .collect(Collectors.toList());
        timestamps = new ArrayDeque<>(filteredTimestamps);//updated the queue of timestamps after removing the unwanted
        return filteredTimestamps.size();
    }

    @Override
    public void run() {
        inc();
    }
}
