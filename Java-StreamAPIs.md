## Java Stream API Notes

### Overview
Java Stream API provides a functional programming approach to process sequences of elements (such as collections) efficiently and concisely. Streams allow operations such as map-reduce transformations, filtering, and more.

### Stream Creation
Streams can be created from various data sources like collections, arrays, or I/O channels.

**Example**:
```java
List<String> list = Arrays.asList("Alice", "Bob", "Charlie");
Stream<String> stream = list.stream();
```

### Functions in Streams
Functions in streams refer to the use of functional interfaces, such as `Function`, `Predicate`, and `Consumer`. They enable functional programming operations like map, filter, and forEach.

**Example**:
- **Function**: Represents a function that accepts one argument and produces a result.
```java
Function<String, Integer> lengthFunction = String::length;
Stream<Integer> lengths = stream.map(lengthFunction);
```

### Serial and Parallel Streams
- **Serial Streams**: Process elements sequentially, one after another.
- **Parallel Streams**: Split the stream into multiple sub-streams that are processed concurrently.

**Serial Stream Example**:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream()
     .forEach(name -> System.out.println("Processing: " + name));
```

**Parallel Stream Example**:
```java
names.parallelStream()
     .forEach(name -> System.out.println("Processing: " + name + " - " + Thread.currentThread().getName()));
```

### Predicates in Streams
A `Predicate` is a functional interface that represents a single-argument function returning a boolean. It's commonly used for filtering in streams.

**Example**:
- **Predicate**: Checks if a string starts with a specific letter.
```java
Predicate<String> startsWithA = name -> name.startsWith("A");
List<String> filteredNames = names.stream()
                                   .filter(startsWithA)
                                   .collect(Collectors.toList());
```

### Combining Predicates
Predicates can be combined using methods like `or`, `and`, and `negate`.

**Example**:
- **or()**: Combines predicates to form a logical OR.
```java
Predicate<String> startsWithA = name -> name.startsWith("A");
Predicate<String> endsWithE = name -> name.endsWith("e");
Predicate<String> combined = startsWithA.or(endsWithE);
List<String> filteredNames = names.stream()
                                   .filter(combined)
                                   .collect(Collectors.toList());
```

- **and()**: Combines predicates to form a logical AND.
```java
Predicate<String> startsWithA = name -> name.startsWith("A");
Predicate<String> endsWithE = name -> name.endsWith("e");
Predicate<String> combined = startsWithA.and(endsWithE);
List<String> filteredNames = names.stream()
                                   .filter(combined)
                                   .collect(Collectors.toList());
```

- **negate()**: Negates the predicate.
```java
Predicate<String> startsWithA = name -> name.startsWith("A");
Predicate<String> notStartsWithA = startsWithA.negate();
List<String> filteredNames = names.stream()
                                   .filter(notStartsWithA)
                                   .collect(Collectors.toList());
```

- **isEqual()**: Checks if two arguments are equal.
```java
Predicate<String> isEqualToAlice = Predicate.isEqual("Alice");
List<String> filteredNames = names.stream()
                                   .filter(isEqualToAlice)
                                   .collect(Collectors.toList());
```

### Summary
Java Stream API enhances code readability and efficiency by leveraging functional programming paradigms. Understanding and utilizing functions, serial and parallel flows, and predicates enable robust data processing workflows.

---

## Terminal Operations

In Java's Stream API, terminal operations are operations that produce a result or a side-effect and mark the end of the stream pipeline. Once a terminal operation is invoked, the stream is considered consumed and cannot be used further. Here are some common terminal operations:

### Common Terminal Operations:
1. **forEach**: Performs an action for each element of the stream.
    ```java
    List<String> list = Arrays.asList("a", "b", "c");
    list.stream().forEach(System.out::println);
    ```

2. **collect**: Converts the elements of the stream into a different form, such as a list, set, or map.
    ```java
    List<String> list = Arrays.asList("a", "b", "c");
    List<String> result = list.stream().collect(Collectors.toList());
    ```

3. **reduce**: Combines the elements of the stream into a single result using an associative accumulation function.
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    int sum = list.stream().reduce(0, Integer::sum);
    ```

4. **count**: Returns the number of elements in the stream.
    ```java
    List<String> list = Arrays.asList("a", "b", "c");
    long count = list.stream().count();
    ```

5. **findFirst**: Returns the first element of the stream, if present.
    ```java
    List<String> list = Arrays.asList("a", "b", "c");
    Optional<String> first = list.stream().findFirst();
    ```

6. **findAny**: Returns any element of the stream, if present.
    ```java
    List<String> list = Arrays.asList("a", "b", "c");
    Optional<String> any = list.stream().findAny();
    ```

7. **allMatch**: Returns `true` if all elements of the stream match the given predicate.
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    boolean allEven = list.stream().allMatch(n -> n % 2 == 0);
    ```

8. **anyMatch**: Returns `true` if any element of the stream matches the given predicate.
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    boolean anyEven = list.stream().anyMatch(n -> n % 2 == 0);
    ```

9. **noneMatch**: Returns `true` if no elements of the stream match the given predicate.
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    boolean noneEven = list.stream().noneMatch(n -> n % 2 == 0);
    ```

10. **min**: Returns the minimum element of the stream according to the provided comparator.
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    Optional<Integer> min = list.stream().min(Integer::compareTo);
    ```

11. **max**: Returns the maximum element of the stream according to the provided comparator.
    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    Optional<Integer> max = list.stream().max(Integer::compareTo);
    ```

### Characteristics:
- **Consumes the Stream**: After a terminal operation is performed, the stream is closed and cannot be reused.
- **Produces a Result**: Terminal operations produce a result, such as a collection, a single value, or a side-effect.
