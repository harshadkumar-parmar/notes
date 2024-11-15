## Executors

### Overview
The Executor framework in Java provides a high-level API for managing threads and handling asynchronous tasks. It decouples the task submission from the mechanics of how each task will be run, including thread use, scheduling, etc.

### Key Components
1. **Executor Interface**: Defines a single method, `execute(Runnable)`, that executes a given command at some time in the future.
2. **ExecutorService Interface**: Extends Executor and provides methods to manage the lifecycle, such as `shutdown()`, and for submitting tasks that return a value.
3. **ThreadPoolExecutor**: A concrete class that implements ExecutorService. Manages a pool of worker threads.
4. **ScheduledExecutorService**: Extends ExecutorService to support scheduling tasks to run after a delay or periodically.

### Example
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println("Task executed by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
```

---

## Blocking Queues

### Overview
Blocking Queues are thread-safe queues that support operations to wait for the queue to become non-empty when retrieving an element and to wait for space to become available in the queue when storing an element.

### Key Implementations
1. **ArrayBlockingQueue**: Bounded blocking queue backed by an array.
2. **LinkedBlockingQueue**: Optionally-bounded blocking queue backed by linked nodes.
3. **PriorityBlockingQueue**: Unbounded blocking queue that supports priority ordering.
4. **DelayQueue**: A time-based blocking queue where elements can only be taken when their delay has expired.

### Example
```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        // Producer
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer
        new Thread(() -> {
            try {
                while (true) {
                    Integer item = queue.take();
                    System.out.println("Consumed: " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
```

---

## Future Object

### Overview
A Future object represents the result of an asynchronous computation. It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result.

### Key Methods
- **get()**: Waits for the computation to complete and then retrieves its result.
- **cancel()**: Attempts to cancel the computation.
- **isDone()**: Checks if the computation is complete.
- **isCancelled()**: Checks if the computation was cancelled.

### Example
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Task completed";
        };

        Future<String> future = executor.submit(task);
        try {
            System.out.println("Doing other work...");
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```
