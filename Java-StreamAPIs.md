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
