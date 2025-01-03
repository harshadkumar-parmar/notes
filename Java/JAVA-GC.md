
# Garbage Collection (GC)

Garbage collection in Java is the process of automatically reclaiming memory by the Java Virtual Machine (JVM) when objects are no longer needed. This helps prevent memory leaks and optimize the use of available memory. Java's garbage collection is done by the JVM's Garbage Collector (GC).



### How Garbage Collection Works
Java's garbage collector follows several principles and algorithms to manage memory:
1. **Mark and Sweep:** The GC marks all reachable objects during the "mark" phase and sweeps away all unmarked objects during the "sweep" phase.
2. **Generational Collectors:** Java heap memory is divided into generations: Young Generation (Eden Space, Survivor Space) and Old Generation (Tenured Space). Most objects are collected in the Young Generation.
3. **Garbage Collection Algorithms:** There are various GC algorithms, such as Serial GC, Parallel GC, CMS (Concurrent Mark-Sweep) GC, and G1 (Garbage-First) GC.

### Example
Here's an example that demonstrates the basics of garbage collection in Java:

```java
public class GarbageCollectionDemo {
    public static void main(String[] args) {
        // Creating an object and assigning it to a reference variable
        GarbageCollectionDemo demo = new GarbageCollectionDemo();
        
        // Making the object eligible for garbage collection
        demo = null;
        
        // Requesting JVM to run Garbage Collector
        System.gc();
        
        // Creating multiple objects in a loop
        for (int i = 0; i < 10000; i++) {
            new GarbageCollectionDemo();
        }
        
        // Requesting JVM to run Garbage Collector again
        System.gc();
    }
    
    @Override
    protected void finalize() throws Throwable {
        // This method is called by GC before the object is reclaimed
        System.out.println("Garbage collector called");
        System.out.println("Object garbage collected: " + this);
    }
}
```

### Explanation
1. **Creating Objects:** The code creates an instance of `GarbageCollectionDemo` and then sets the reference to `null`, making it eligible for garbage collection.
2. **Requesting GC:** The `System.gc()` method suggests that the JVM run the garbage collector. Note that this is just a request, and the JVM is free to ignore it.
3. **Creating Multiple Objects:** A loop creates multiple instances of `GarbageCollectionDemo`, eventually making some of them eligible for garbage collection.
4. **Finalize Method:** The `finalize` method is overridden to provide a custom behavior when the GC is about to reclaim the object's memory. However, it's worth noting that relying on `finalize` is generally discouraged, as it may not be called in a timely manner or at all.

### Best Practices
- **Avoid Explicit GC Calls:** Do not rely on `System.gc()` for garbage collection. Let the JVM handle it.
- **Optimize Object Lifecycle:** Reduce object creation and reuse objects where possible.
- **Profiling and Monitoring:** Use tools like VisualVM, JConsole, or YourKit to monitor and profile your application's memory usage.


## Generational Collectors

### Young Generation
- **Eden Space:** This is where all new objects are initially allocated. When the space fills up, a minor GC is triggered.
- **Survivor Spaces (S0 and S1):** There are two survivor spaces, S0 and S1. After a minor GC, live objects from Eden are moved to one of the survivor spaces. On subsequent GCs, live objects are moved between the two survivor spaces.

### Old Generation (Tenured Generation)
- **Old Generation:** Objects that survive multiple cycles of minor GCs are eventually promoted to the old generation. This generation holds long-lived objects. Major GCs are performed here, which are less frequent but more time-consuming than minor GCs.

### PermGen/Metaspace
- **PermGen:** In older versions of Java (before Java 8), PermGen was a separate memory space for storing metadata such as classes and methods. This space was managed separately from the Heap.
- **Metaspace:** Starting from Java 8, PermGen was replaced by Metaspace, which dynamically adjusts its size according to the needs of the application. Metaspace is part of the native memory and can grow as needed, avoiding the limitations of PermGen.

## GC Process
- **Minor GC:** Occurs in the young generation. It is fast and efficient because it only deals with short-lived objects. Live objects are moved to survivor spaces, and the rest are collected.
- **Major GC (Full GC):** Occurs in the old generation. It is more time-consuming as it deals with long-lived objects. This type of GC can lead to longer pause times, affecting application performance.


## GC algorithms

Garbage Collection (GC) algorithms are essential for automatic memory management in Java, ensuring that unused objects are efficiently reclaimed to free up memory. Here's an overview of some of the key GC algorithms used in the Java Virtual Machine (JVM):

### 1. Serial GC
**Description:** A simple GC algorithm suitable for single-threaded environments. It uses a single thread to perform all garbage collection work.

**Use Case:** Best suited for small applications or environments with limited memory where pause times are not critical.

**JVM Option:** `-XX:+UseSerialGC`

### 2. Parallel GC
**Description:** Also known as the throughput collector, it uses multiple threads for garbage collection, aiming to maximize throughput.

**Use Case:** Suitable for applications that can tolerate longer pause times but require high throughput.

**JVM Option:** `-XX:+UseParallelGC`

### 3. CMS (Concurrent Mark-Sweep) GC
**Description:** A low-pause GC algorithm that performs most of its work concurrently with the application threads. It reduces pause times by splitting the garbage collection process into smaller steps.

**Use Case:** Ideal for applications that require low pause times, such as interactive applications.

**JVM Option:** `-XX:+UseConcMarkSweepGC`

### 4. G1 (Garbage-First) GC
**Description:** Designed for large heaps, G1 GC aims to provide predictable pause times while maintaining good throughput. It divides the heap into regions and performs incremental garbage collection.

**Use Case:** Suitable for large applications that require predictable pause times and efficient memory management.

**JVM Option:** `-XX:+UseG1GC`

### 5. ZGC (Z Garbage Collector)
**Description:** A scalable, low-latency GC algorithm designed for applications requiring large heaps and low pause times. It performs most of its work concurrently with the application.

**Use Case:** Ideal for large-scale applications where minimizing pause times is critical.

**JVM Option:** `-XX:+UseZGC`

### 6. Shenandoah GC
**Description:** Another low-pause GC algorithm that aims to minimize pause times by performing concurrent garbage collection.

**Use Case:** Suitable for applications that require low-latency and consistent performance.

**JVM Option:** `-XX:+UseShenandoahGC`

### Example: Switching GC Algorithms
You can switch between different GC algorithms using JVM options. For example, to use the G1 GC, you can start your Java application with the following option:

```shell
java -XX:+UseG1GC -jar your-application.jar
```

### Choosing the Right GC Algorithm
The choice of GC algorithm depends on several factors:
- **Application Type:** Interactive applications may require low-pause GC, while batch processing might benefit from throughput-oriented GC.
- **Heap Size:** Larger heaps might require more sophisticated GC algorithms like G1 or ZGC.
- **Pause Time Requirements:** Applications with strict pause time requirements benefit from low-latency GC algorithms like CMS, G1, ZGC, or Shenandoah.
- **Performance Goals:** Balance between throughput and latency based on your application's performance needs.

### Tuning GC Performance
To further optimize GC performance, you can tune various GC parameters such as heap size (`-Xmx`, `-Xms`), young generation size (`-XX:NewSize`, `-XX:MaxNewSize`), and more. Profiling and monitoring tools like VisualVM, JConsole, or Java Mission Control can help you analyze and optimize GC behavior.


## Memory leaks

Memory leaks in Java can occur when objects are no longer needed but are still being referenced, preventing the Garbage Collector (GC) from reclaiming their memory. Here are some common causes of memory leaks in Java:

1. **Unclosed Resources:**
   - Failing to close resources like database connections, file streams, or network connections can cause memory leaks. Always use a try-with-resources statement or explicitly close resources in the finally block.

2. **Static Collections:**
   - Using static collections to hold large amounts of data can cause memory leaks, as static fields are tied to the class loader and persist for the lifetime of the application.

3. **Listeners and Callbacks:**
   - Adding listeners or callbacks without removing them properly can lead to memory leaks, as the objects they reference might not be garbage collected.

4. **Inner Classes and Anonymous Classes:**
   - Inner classes and anonymous classes can hold implicit references to their outer class instances. If the outer class instance is no longer needed but still referenced by these inner classes, it can't be garbage collected.

5. **ThreadLocal Variables:**
   - Misuse of `ThreadLocal` can cause memory leaks if the variables are not removed properly. Always call `remove()` on a `ThreadLocal` variable when it is no longer needed.

6. **Custom Data Structures:**
   - Custom data structures that do not properly manage memory can cause leaks. For instance, forgetting to remove items from a cache can cause a memory buildup.

7. **Finalizers:**
   - Relying on finalizers can cause memory leaks because objects with finalizers have to go through an extra GC cycle before being collected, potentially delaying memory reclamation.

8. **Long-living Sessions:**
   - Holding references to objects in long-living sessions (e.g., HTTP sessions) can cause memory leaks. Ensure that sessions are properly managed and expired.

9. **WeakHashMap Usage:**
   - While `WeakHashMap` can help avoid memory leaks, misuse can still cause issues. Ensure that keys are not strongly referenced elsewhere.

Here are a few best practices to avoid memory leaks:

- **Use Profiler Tools:**
  - Use tools like VisualVM, YourKit, or JProfiler to monitor memory usage and identify leaks.

- **Follow Design Patterns:**
  - Implement design patterns like Singleton or Factory properly to avoid unintended object retention.

- **Avoid Static References:**
  - Minimize the use of static references, especially for collections.

- **Proper Resource Management:**
  - Always close resources when done using them.

- **Weak References:**
  - Use `WeakReference` or `SoftReference` where applicable to allow GC to reclaim memory if needed.


## Profiling

Profiling in software development is the process of analyzing a program to understand its performance characteristics, including memory usage, CPU usage, and runtime behavior. Profiling helps identify bottlenecks and areas for optimization to improve overall application efficiency. Here's a comprehensive guide to profiling in Java:

### Why Profiling?
- **Performance Optimization:** Identify slow methods and optimize them to improve performance.
- **Memory Management:** Detect memory leaks and optimize memory usage.
- **Resource Allocation:** Understand how resources (CPU, memory) are being used.
- **Debugging:** Diagnose unexpected application behavior.

### Common Profiling Tools for Java
1. **VisualVM:** A powerful profiling tool bundled with the JDK.
2. **JConsole:** A monitoring tool that provides basic profiling capabilities.
3. **Java Mission Control (JMC):** Advanced profiling and diagnostics tool.
4. **YourKit Java Profiler:** A commercial profiler with comprehensive features.
5. **Eclipse MAT (Memory Analyzer Tool):** Analyzes heap dumps to find memory leaks and optimize memory usage.

### Profiling with VisualVM
VisualVM is a free tool that provides detailed insights into the performance of Java applications. Here's how to use it:

1. **Launching VisualVM:**
   - VisualVM is included with the JDK. You can start it from the JDK's `bin` directory:
     ```shell
     jvisualvm
     ```

2. **Attaching to a Java Process:**
   - VisualVM automatically detects running Java applications. Select the process you want to profile from the list.

3. **CPU Profiling:**
   - To profile CPU usage, go to the "Profiler" tab and start CPU profiling. This will give you insights into which methods are consuming the most CPU time.

4. **Memory Profiling:**
   - Similar to CPU profiling, you can start memory profiling to analyze memory usage. This helps in identifying memory leaks and optimizing memory allocation.

5. **Heap Dump Analysis:**
   - You can take heap dumps to analyze the memory state of your application at a particular point in time. Heap dumps help in identifying objects that occupy significant memory and potential memory leaks.

### Profiling with Java Mission Control (JMC)
Java Mission Control (JMC) is another powerful tool for profiling and diagnostics. It provides detailed insights into the runtime behavior of Java applications. Here's how to use JMC:

1. **Launching JMC:**
   - JMC is bundled with the JDK. You can start it from the JDK's `bin` directory:
     ```shell
     jmc
     ```

2. **Recording Flight Recordings:**
   - JMC allows you to record flight recordings, which capture detailed performance data over time. You can start a new recording and specify the duration.

3. **Analyzing Flight Recordings:**
   - Once the recording is complete, you can analyze the data to identify performance bottlenecks, memory usage patterns, and other runtime behaviors.

### Example: Profiling a Java Application
Here's a simple example of a Java application and how to profile it using VisualVM:

```java
public class ProfilingExample {
    public static void main(String[] args) {
        ProfilingExample example = new ProfilingExample();
        example.run();
    }

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            String str = new String("String " + i);
            processString(str);
        }
    }

    private void processString(String str) {
        // Simulate some processing
        for (int i = 0; i < 1000; i++) {
            str.hashCode();
        }
    }
}
```
To profile this application:
1. **Start VisualVM and attach to the `ProfilingExample` process.**
2. **Enable CPU and memory profiling to analyze the performance characteristics.**

### Profiling Tips
- **Profile in a Production-like Environment:** Ensure that your profiling environment closely resembles your production environment.
- **Focus on Hotspots:** Identify and optimize hotspots (methods or sections of code that consume significant resources).
- **Iterative Profiling:** Profile, optimize, and repeat. Performance tuning is an iterative process.
- **Use Multiple Tools:** Different tools provide different insights. Use a combination of tools for comprehensive profiling.

Profiling is an essential part of performance tuning and optimization in software development. By understanding and addressing performance bottlenecks, you can significantly improve your application's efficiency and user experience.

