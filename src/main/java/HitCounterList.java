import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HitCounterList implements Runnable {
    public static List<LocalDateTime> timestamps = new ArrayList<>();

    //synchronized to overcome the thread race condition
    public static synchronized void inc() {
        System.out.println("Request Received by HC1");
        timestamps.add(LocalDateTime.now());
    }

    public static int countRequestsInLastOneMinute() {
        LocalDateTime lastOneMinute = LocalDateTime.now().minusMinutes(1);
        List<LocalDateTime> filterTimestamps = timestamps
                .stream().filter(curTimeStamp -> curTimeStamp.isAfter(lastOneMinute))
                .collect(Collectors.toList());
        return filterTimestamps.size();
    }

    @Override
    public void run() {
        inc();
    }
}
