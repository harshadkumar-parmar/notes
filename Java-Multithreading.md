
## Callable vs Runnable

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