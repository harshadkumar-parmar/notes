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

## CompletableFuture

`CompletableFuture` is a feature introduced in Java 8 as part of the `java.util.concurrent` package. It provides a powerful way to handle asynchronous programming and non-blocking operations. Here's a detailed look at `CompletableFuture`:

### Key Features:
1. **Asynchronous Computation**: Allows you to run tasks asynchronously without blocking the main thread.
2. **Chaining**: Supports chaining multiple asynchronous tasks together using methods like `thenApply`, `thenAccept`, and `thenCompose`.
3. **Combining**: Enables combining multiple `CompletableFuture` instances using methods like `allOf`, `anyOf`, and `thenCombine`.
4. **Exception Handling**: Provides methods for handling exceptions in asynchronous tasks, such as `exceptionally` and `handle`.
5. **Completion**: Can be explicitly completed using methods like `complete` and `completeExceptionally`.

### Example Usage:
Here's an example of how to use `CompletableFuture` to run an asynchronous task and handle its result:

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public static void main(String[] args) {
        // Create a CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, World!";
        });

        // Chain another task to run after the first task completes
        future.thenApply(result -> {
            System.out.println("Result: " + result);
            return result.length();
        }).thenAccept(length -> {
            System.out.println("Length: " + length);
        });

        // Wait for the CompletableFuture to complete
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
```

### Explanation:
- **supplyAsync**: Runs a task asynchronously and returns a `CompletableFuture`.
- **thenApply**: Chains another task to run after the first task completes, transforming the result.
- **thenAccept**: Consumes the result of the previous task without returning a new value.
- **get**: Waits for the `CompletableFuture` to complete and retrieves the result.

### Benefits:
- **Non-blocking**: Allows you to write non-blocking code, improving the responsiveness of your application.
- **Composability**: Makes it easy to compose multiple asynchronous tasks together.
- **Error Handling**: Provides robust error handling for asynchronous operations.

---
###  `Future` and `CompletableFuture`:

| **Feature**              | **Future**                                      | **CompletableFuture**                                      |
|--------------------------|-------------------------------------------------|------------------------------------------------------------|
| **Definition**           | Represents the result of an asynchronous computation | An extension of `Future` that provides additional methods for asynchronous programming |
| **Completion**           | Can be completed manually using `get` or `cancel` methods | Can be completed manually or automatically using various methods like `complete`, `completeExceptionally`, and `supplyAsync` |
| **Chaining**             | Does not support chaining of tasks              | Supports chaining of tasks using methods like `thenApply`, `thenAccept`, and `thenCompose` |
| **Combining**            | Does not support combining multiple futures     | Supports combining multiple futures using methods like `allOf`, `anyOf`, and `thenCombine` |
| **Exception Handling**   | Limited exception handling                      | Provides robust exception handling using methods like `exceptionally` and `handle` |
| **Blocking**             | Requires blocking calls to retrieve the result  | Supports non-blocking calls and provides methods to handle results asynchronously |
| **Ease of Use**          | Requires more boilerplate code for complex scenarios | Provides a more fluent and expressive API for asynchronous programming |
| **Introduced In**        | Java 5                                          | Java 8                                                     |

### Example Usage:
#### Future:
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
            return "Hello, World!";
        };

        Future<String> future = executor.submit(task);

        try {
            String result = future.get(); // Blocking call
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```

#### CompletableFuture:
```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, World!";
        });

        future.thenApply(result -> {
            System.out.println("Result: " + result);
            return result.length();
        }).thenAccept(length -> {
            System.out.println("Length: " + length);
        });

        try {
            future.get(); // Blocking call to wait for completion
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
```

### Summary:
- **Future**: Provides basic asynchronous computation capabilities but requires blocking calls to retrieve results and lacks support for chaining and combining tasks.
- **CompletableFuture**: Extends `Future` with additional methods for chaining, combining, and handling exceptions, making it more suitable for complex asynchronous programming.


Sure! Here's a comparison of `CyclicBarrier`, `Phaser`, `CountDownLatch`, `Exchanger`, `Semaphore`, and `SynchronousQueue`, which are all synchronization aids in Java:

| **Feature**              | **CyclicBarrier**                                      | **Phaser**                                      | **CountDownLatch**                                      | **Exchanger**                                      | **Semaphore**                                      | **SynchronousQueue**                                      |
|--------------------------|--------------------------------------------------------|-------------------------------------------------|--------------------------------------------------------|---------------------------------------------------|---------------------------------------------------|------------------------------------------------------------|
| **Purpose**              | Synchronizes a group of threads at a common barrier point | Synchronizes threads in phases                   | Delays execution until the count reaches zero            | Allows two threads to exchange data                | Controls access to a resource by multiple threads | Facilitates handoff of data between threads                 |
| **Reuse**                | Cyclic; can be reset and reused after all threads arrive | Cyclic; can be reused for multiple phases        | One-time use; the count cannot be reset once it reaches zero | One-time use per exchange                           | Can be reused                                      | Can be reused                                               |
| **Count Changes**        | Dynamic; the number of parties can be changed at runtime | Dynamic; supports dynamic registration and deregistration of parties | Fixed; the count is set during initialization and cannot be changed | Fixed; one exchange per call                       | Fixed; the number of permits is set during initialization | N/A                                                        |
| **Parties**              | Threads wait for each other, all must reach the barrier | Threads wait for each other in phases            | Threads wait for a fixed number of tasks to complete     | Two threads exchange data                           | Threads acquire and release permits               | Threads exchange data in a handoff manner                   |
| **Example Use Case**     | A group of threads waiting to start a race together     | A group of threads performing tasks in phases    | A main thread waiting for multiple worker threads to complete their tasks | Two threads exchanging buffers                     | Limiting the number of concurrent database connections | Producer-consumer scenario with direct handoff               |

### Explanation:
- **CyclicBarrier**: Allows a group of threads to wait for each other to reach a common barrier point. Once all threads have reached the barrier, they can proceed together. The barrier is cyclic because it can be reused after all threads have reached it.
- **Phaser**: Similar to `CyclicBarrier`, but more flexible. It allows threads to wait for each other in phases and supports dynamic registration and deregistration of parties.
- **CountDownLatch**: Allows one or more threads to wait until a set of operations being performed in other threads completes. The count cannot be reset, making it suitable for scenarios where a specific number of tasks need to be completed before a process continues.
- **Exchanger**: Allows two threads to exchange data. Each thread presents some data to the exchange point and receives the data presented by the other thread.
- **Semaphore**: Controls access to a resource by multiple threads. It maintains a set of permits, and threads can acquire and release permits to access the resource.
- **SynchronousQueue**: A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa. It is useful for handoff scenarios where one thread produces data and another thread consumes it immediately.

### Example Usage:
#### CyclicBarrier:
```java
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        final int numberOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads have reached the barrier. Let's proceed!");
            }
        });

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Task(barrier)).start();
        }
    }

    static class Task implements Runnable {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### Phaser:
```java
import java.util.concurrent.Phaser;

public class PhaserExample {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); // Register the main thread

        for (int i = 0; i < 3; i++) {
            phaser.register();
            new Thread(new Task(phaser)).start();
        }

        phaser.arriveAndDeregister(); // Deregister the main thread
    }

    static class Task implements Runnable {
        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is performing phase 1.");
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " is performing phase 2.");
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " is performing phase 3.");
            phaser.arriveAndDeregister();
        }
    }
}
```

#### CountDownLatch:
```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        final int numberOfTasks = 3;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            new Thread(new Task(latch)).start();
        }

        try {
            latch.await(); // Wait until the count reaches zero
            System.out.println("All tasks have been completed. Proceeding...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {
        private CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is performing a task.");
                Thread.sleep(1000); // Simulate task
                latch.countDown(); // Decrement the count
                System.out.println(Thread.currentThread().getName() + " has completed the task.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### Exchanger:
```java
import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Producer(exchanger)).start();
        new Thread(new Consumer(exchanger)).start();
    }

    static class Producer implements Runnable {
        private Exchanger<String> exchanger;

        public Producer(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String data = "Data from Producer";
                System.out.println("Producer is exchanging data: " + data);
                String response = exchanger.exchange(data);
                System.out.println("Producer received response: " + response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private Exchanger<String> exchanger;

        public Consumer(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String data = "Data from Consumer";
                System.out.println("Consumer is exchanging data: " + data);
                String response = exchanger.exchange(data);
                System.out.println("Consumer received response: " + response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### Semaphore:
```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // Allow 2 threads to access the resource

        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore)).start();
        }
    }

    static class Task implements Runnable {
        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
                Thread.sleep(1000); // Simulate resource access
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " has released the resource.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```
