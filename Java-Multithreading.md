
# Java Multi-Threading Notes

### Introduction to Multi-Threading
- **Definition**: Multi-threading is a process of executing multiple threads simultaneously within a single process.
- **Threads**: Threads are lightweight sub-processes, the smallest unit of processing. They share the same memory space and resources of the parent process.

### Advantages of Multi-Threading
- **Efficient Resource Utilization**: Threads use a shared memory area, saving memory.
- **Improved Performance**: Threads can run concurrently, improving the performance of applications.
- **Responsiveness**: Threads allow applications to remain responsive even while performing complex tasks.

### Thread Life Cycle
1. **New**: The thread is in the process of being created but has not yet started.
2. **Runnable**: The thread is ready to run and is waiting for CPU time.
3. **Running**: The thread is executing.
4. **Blocked**: The thread is waiting for a resource (e.g., I/O).
5. **Terminated**: The thread has finished execution.

### Creating Threads in Java
- **Extending Thread Class**: Create a class that extends the `Thread` class and override the `run()` method.
- **Implementing Runnable Interface**: Create a class that implements the `Runnable` interface and pass it to a `Thread` object.

**Example**:
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class TestThread {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}
```

### Thread Methods
- **start()**: Begins the execution of the thread.
- **run()**: Contains the code that will be executed by the thread.
- **sleep(long millis)**: Causes the currently executing thread to sleep for a specified period.
- **currentThread()**: Returns a reference to the currently executing thread.
- **join()**: Waits for the thread to die.
- **getPriority()**: Returns the priority of the thread.
- **setPriority(int priority)**: Sets the priority of the thread.

### Synchronization
- **Synchronization**: Ensures that only one thread can access a resource at a time.
- **Synchronized Keyword**: Used to control access to critical sections of code.
- **Locks**: Mechanisms that control access to resources.

**Example**:
```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

### Thread Pool
- **ExecutorService**: Manages a pool of threads and provides methods to submit tasks for execution.
- **ThreadPoolExecutor**: A concrete implementation of `ExecutorService`.

**Example**:
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
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
## Callable vs Runnable

There are two ways of creating threads – one by extending the Thread class and other by creating a thread with a Runnable. However, one feature lacking in  Runnable is that we cannot make a thread return result when it terminates, i.e. when run() completes. For supporting this feature, the Callable interface is present in Java.

### Runnable
- **Definition**: Represents a task that can be executed by a thread.
- **Method**: `void run()`
- **Return Value**: Does not return a result and cannot throw checked exceptions.
- **Example**:
```java
Runnable task = () -> System.out.println("Task executed");
new Thread(task).start();
```

### Callable
- **Definition**: Represents a task that returns a result and may throw an exception.
- **Method**: `V call()`
- **Return Value**: Returns a result and can throw checked exceptions.
- **Example**:
```java
Callable<String> task = () -> {
    Thread.sleep(2000);
    return "Task completed";
};
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<String> future = executor.submit(task);
```

### Key Differences
- **Method Signature**: Runnable has `run()`, Callable has `call()`.
- **Return Type**: Runnable cannot return a result, Callable can.
- **Exceptions**: Runnable cannot throw checked exceptions, Callable can.

---