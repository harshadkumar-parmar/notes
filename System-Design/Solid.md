# Solid Principles

The SOLID principles are a set of five design principles in object-oriented programming that help make software more understandable, flexible, and maintainable. Here's a brief overview of each principle, along with an example in Java:

## 1. Single Responsibility Principle (SRP)

The Single Responsibility Principle (SRP) is one of the five SOLID principles of object-oriented programming and design. Introduced by Robert C. Martin, it states that:

**"A class should have only one reason to change."**

In simpler terms, SRP suggests that a class should have only one responsibility or purpose. This means that a class should encapsulate a single functionality or a cohesive set of functionalities, and changes to the class should stem from one specific cause.

### Key Points of SRP:

1. **Single Responsibility:**
   - Each class should focus on a single task or responsibility.
   
2. **Cohesion:**
   - High cohesion within the class, meaning the methods and properties are closely related to the single responsibility.
   
3. **Separation of Concerns:**
   - Different functionalities should be separated into different classes to ensure each class has a single reason to change.

### Example in Java:

Let's illustrate SRP with a Java example.

#### Before Applying SRP:

Consider a class `Employee` that handles multiple responsibilities:

```java
public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void calculatePay() {
        // Calculate the employee's pay
    }

    public void save() {
        // Save the employee to the database
    }

    public void printDetails() {
        // Print the employee's details
    }
}
```

In this scenario, the `Employee` class has multiple responsibilities: calculating pay, saving to the database, and printing details. This violates the SRP because changes to any of these responsibilities will require changes to the `Employee` class.

#### After Applying SRP:

Now, let's refactor the code to adhere to the Single Responsibility Principle:

```java
public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

public class PayCalculator {
    public void calculatePay(Employee employee) {
        // Calculate the employee's pay
    }
}

public class EmployeeRepository {
    public void save(Employee employee) {
        // Save the employee to the database
    }
}

public class EmployeePrinter {
    public void printDetails(Employee employee) {
        // Print the employee's details
    }
}
```

In this refactored example:
- **Employee**: Represents the employee entity with properties `name` and `salary`.
- **PayCalculator**: Handles the responsibility of calculating the employee's pay.
- **EmployeeRepository**: Handles the responsibility of saving the employee to the database.
- **EmployeePrinter**: Handles the responsibility of printing the employee's details.

By separating these responsibilities into different classes, we ensure that each class has a single reason to change, adhering to the SRP.

### Benefits of Adhering to SRP:

- **Improved Maintainability:** Changes in one responsibility do not affect other parts of the system, making the code easier to maintain.
- **Enhanced Readability:** Each class has a clear and specific purpose, making the code more understandable.
- **Greater Reusability:** Classes with single responsibilities can be reused in different contexts without bringing in unnecessary functionality.
- **Simplified Testing:** Smaller, focused classes are easier to test individually.



## 2. Open/Closed Principle (OCP)

The Open/Closed Principle (OCP) is one of the five SOLID principles of object-oriented programming and design. Introduced by Bertrand Meyer, it states that:

**"Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification."**

In simpler terms, OCP suggests that you should be able to extend the functionality of a class or module without modifying its existing code. This promotes code reusability, maintainability, and flexibility.

### Key Points of OCP:

1. **Open for Extension:**
   - You should be able to add new functionality to an existing class or module.
   
2. **Closed for Modification:**
   - Existing code should not be changed when new functionality needs to be added, minimizing the risk of introducing bugs or breaking existing functionality.

### Example in Java:

Let's illustrate the Open/Closed Principle with a Java example.

#### Before Applying OCP:

Consider a class `Rectangle` and a method `calculateArea` that calculates the area of different shapes.

```java
class Rectangle {
    public int length;
    public int width;
}

class AreaCalculator {
    public int calculateArea(Rectangle rectangle) {
        return rectangle.length * rectangle.width;
    }
}
```

Now, suppose we want to add support for a new shape, `Circle`. We would need to modify the `AreaCalculator` class to handle the new shape, violating OCP.

```java
class Circle {
    public int radius;
}

class AreaCalculator {
    public int calculateArea(Rectangle rectangle) {
        return rectangle.length * rectangle.width;
    }

    public int calculateArea(Circle circle) {
        return (int) (Math.PI * circle.radius * circle.radius);
    }
}
```

#### After Applying OCP:

To adhere to the Open/Closed Principle, we can use polymorphism and inheritance. Let's create a base class `Shape` with a method `calculateArea` that can be overridden by subclasses.

```java
abstract class Shape {
    abstract int calculateArea();
}

class Rectangle extends Shape {
    public int length;
    public int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public int calculateArea() {
        return length * width;
    }
}

class Circle extends Shape {
    public int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public int calculateArea() {
        return (int) (Math.PI * radius * radius);
    }
}

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 20);
        Shape circle = new Circle(5);

        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Circle Area: " + circle.calculateArea());
    }
}
```

In this refactored example:
- **Shape**: An abstract class that defines the `calculateArea` method.
- **Rectangle** and **Circle**: Subclasses of `Shape` that implement the `calculateArea` method.

By using polymorphism, we can extend the functionality (i.e., add new shapes) without modifying the existing code in the `Shape` class, adhering to OCP.

### Benefits of Adhering to OCP:

- **Increased Flexibility:** New functionality can be added without changing existing code, making the system more adaptable.
- **Improved Maintainability:** Reduces the risk of introducing bugs when adding new features, as existing code remains unchanged.
- **Enhanced Reusability:** Promotes the creation of reusable components and modules.



## 3. Liskov Substitution Principle (LSP)

The Liskov Substitution Principle (LSP) is one of the five SOLID principles of object-oriented programming and design. Introduced by Barbara Liskov in 1987, this principle states that:

**"Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program."**

In simpler terms, if `S` is a subclass of `T`, then objects of type `T` in a program should be replaceable with objects of type `S` without altering the desirable properties of the program (e.g., correctness, task performed, etc.).

### Key Points of LSP:

1. **Subtypes Must Be Substitutable for their Base Types:**
   - Any instance of a subclass should be able to stand in for its superclass without causing any errors or unexpected behavior.
   
2. **Behavioral Consistency:**
   - Subclasses must override methods in a way that does not break the expectations established by the base class’s methods. This includes method signatures, return types, and the overall functionality.

3. **Contract Inheritance:**
   - Subtypes must adhere to the “contract” defined by the base type. This means preserving the invariants and preconditions/postconditions of the methods.

### Example with LSP Violation

First, let's consider a scenario that violates the Liskov Substitution Principle. We'll define a base class `Bird` with a method `fly()`, and two subclasses: `Sparrow` and `Penguin`.

```java
class Bird {
    public void fly() {
        System.out.println("This bird can fly!");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying!");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

public class Main {
    public static void makeBirdFly(Bird bird) {
        bird.fly();
    }

    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();

        makeBirdFly(sparrow); // Works fine
        makeBirdFly(penguin); // Throws an exception: Penguins can't fly!
    }
}
```

In this example, substituting `Bird` with `Penguin` breaks the code because `Penguin` can't fly, which violates the Liskov Substitution Principle.

### Refactored Example with LSP Compliance

To comply with LSP, we should separate the behavior of flying into an appropriate class hierarchy. Let's introduce a `FlyingBird` class and a `NonFlyingBird` class to handle this.

```java
class Bird {
    // Common properties and methods for all birds
}

class FlyingBird extends Bird {
    public void fly() {
        System.out.println("This bird can fly!");
    }
}

class Sparrow extends FlyingBird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying!");
    }
}

class NonFlyingBird extends Bird {
    // Non-flying birds do not have the fly() method
}

class Penguin extends NonFlyingBird {
    public void swim() {
        System.out.println("Penguin is swimming!");
    }
}

public class Main {
    public static void makeFlyingBirdFly(FlyingBird bird) {
        bird.fly();
    }

    public static void main(String[] args) {
        FlyingBird sparrow = new Sparrow();
        Penguin penguin = new Penguin();

        makeFlyingBirdFly(sparrow); // Works fine
        // makeFlyingBirdFly(penguin); // This won't work as Penguin is not a FlyingBird

        penguin.swim(); // Works fine
    }
}
```

In this refactored example, we have:
- **Bird**: A base class for all birds.
- **FlyingBird**: A subclass of `Bird` that includes the `fly()` method.
- **Sparrow**: A subclass of `FlyingBird`, which can fly.
- **NonFlyingBird**: A subclass of `Bird` for birds that do not fly.
- **Penguin**: A subclass of `NonFlyingBird`, which has its own behavior like `swim()`.

This design adheres to the Liskov Substitution Principle, as substituting `FlyingBird` with `Sparrow` works correctly, and `NonFlyingBird` with `Penguin` doesn't require the `fly()` method.


### Benefits of Adhering to LSP:

- **Code Reusability:** Ensures that code can be reused and extended without breaking existing functionality.
- **Maintainability:** Makes the codebase easier to maintain and modify.
- **Scalability:** Facilitates the addition of new features or classes without disrupting the existing system.


## 4. Interface Segregation Principle (ISP)
The Interface Segregation Principle (ISP) is one of the five SOLID principles of object-oriented design. Introduced by Robert C. Martin, it states that:

**"No client should be forced to depend on methods it does not use."**

In simpler terms, ISP suggests that instead of creating large, monolithic interfaces, we should create smaller, more specific ones. Clients should only need to know about the methods that are relevant to them, rather than being burdened with additional methods they don't need.

### Key Points of ISP:

1. **Specific Interfaces:**
   - Design interfaces that are highly specific to the client’s needs.
   
2. **Avoid Fat Interfaces:**
   - Prevent the creation of large, unwieldy interfaces that have more methods than a client requires.
   
3. **Cohesion:**
   - Ensure that each interface has a single responsibility, improving modularity and maintainability.

### Example in Java:

Let's illustrate ISP with a Java example. 

#### Before Applying ISP:

Consider an interface `Machine` that is too broad:

```java
public interface Machine {
    void print(Document doc);
    void scan(Document doc);
    void fax(Document doc);
}

class MultiFunctionPrinter implements Machine {
    @Override
    public void print(Document doc) {
        // Implementation
    }

    @Override
    public void scan(Document doc) {
        // Implementation
    }

    @Override
    public void fax(Document doc) {
        // Implementation
    }
}

class OldFashionedPrinter implements Machine {
    @Override
    public void print(Document doc) {
        // Implementation
    }

    @Override
    public void scan(Document doc) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void fax(Document doc) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
```

In this scenario, `OldFashionedPrinter` must implement methods `scan()` and `fax()`, which it does not support, leading to a violation of ISP.

#### After Applying ISP:

Now, let's create more specific interfaces:

```java
public interface Printer {
    void print(Document doc);
}

public interface Scanner {
    void scan(Document doc);
}

public interface Fax {
    void fax(Document doc);
}

class MultiFunctionPrinter implements Printer, Scanner, Fax {
    @Override
    public void print(Document doc) {
        // Implementation
    }

    @Override
    public void scan(Document doc) {
        // Implementation
    }

    @Override
    public void fax(Document doc) {
        // Implementation
    }
}

class SimplePrinter implements Printer {
    @Override
    public void print(Document doc) {
        // Implementation
    }
}
```

By splitting the `Machine` interface into smaller, more specific interfaces (`Printer`, `Scanner`, `Fax`), we ensure that each class only implements the methods it needs. `SimplePrinter` now only implements the `Printer` interface, adhering to ISP.

### Benefits of Adhering to ISP:

- **Increased Modularity:** Smaller interfaces improve modularity, making the codebase easier to manage.
- **Enhanced Flexibility:** Clients are not forced to implement methods they don’t need, allowing for more flexible design.
- **Improved Maintainability:** Smaller, cohesive interfaces make the system easier to understand, maintain, and extend.



## 5. Dependency Inversion Principle (DIP)

The Dependency Inversion Principle (DIP) is another key principle among the SOLID principles of object-oriented design. Introduced by Robert C. Martin, it states that:

**"High-level modules should not depend on low-level modules. Both should depend on abstractions."**
**"Abstractions should not depend on details. Details should depend on abstractions."**

In essence, the DIP aims to decouple high-level and low-level modules by introducing abstractions that both layers depend on. This promotes flexibility and maintainability in the system, allowing for easier modification and extension.

### Key Points of DIP:

1. **Decoupling:**
   - Separate high-level policy-setting modules from low-level implementation details.
   
2. **Abstractions:**
   - Introduce abstractions (interfaces or abstract classes) to handle dependencies, rather than concrete implementations.

3. **Inversion:**
   - Dependencies are inverted; high-level modules depend on abstractions, while low-level modules implement these abstractions.

### Example in Java:

Let's use an example to illustrate the Dependency Inversion Principle.

#### Before Applying DIP:

Consider a scenario where a high-level class `Computer` depends on a low-level class `Keyboard`.

```java
class Keyboard {
    public void type() {
        System.out.println("Typing...");
    }
}

class Computer {
    private Keyboard keyboard;

    public Computer() {
        this.keyboard = new Keyboard();
    }

    public void type() {
        keyboard.type();
    }
}

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.type();
    }
}
```

In this example, the `Computer` class directly depends on the `Keyboard` class, violating the DIP. Any change in the `Keyboard` class would require changes in the `Computer` class.

#### After Applying DIP:

Now, let's refactor the code to comply with the Dependency Inversion Principle by introducing an abstraction.

```java
// Abstraction
interface InputDevice {
    void type();
}

// Low-level module
class Keyboard implements InputDevice {
    @Override
    public void type() {
        System.out.println("Typing...");
    }
}

// High-level module
class Computer {
    private InputDevice inputDevice;

    public Computer(InputDevice inputDevice) {
        this.inputDevice = inputDevice;
    }

    public void type() {
        inputDevice.type();
    }
}

public class Main {
    public static void main(String[] args) {
        InputDevice keyboard = new Keyboard();
        Computer computer = new Computer(keyboard);
        computer.type();
    }
}
```

In this refactored example:
- **InputDevice**: An abstraction (interface) that both the `Computer` and `Keyboard` classes depend on.
- **Keyboard**: Implements the `InputDevice` interface, providing the `type()` method.
- **Computer**: Depends on the `InputDevice` interface, rather than the `Keyboard` class.

By introducing the `InputDevice` interface, we decouple the `Computer` and `Keyboard` classes, making the system more flexible and easier to maintain. We can now introduce new input devices without modifying the `Computer` class.

### Benefits of Adhering to DIP:

- **Increased Flexibility:** High-level modules are not tightly coupled to low-level modules, allowing for easy substitution and extension.
- **Improved Maintainability:** Changes in low-level modules do not affect high-level modules, reducing the impact of modifications.
- **Enhanced Testability:** Abstractions can be easily mocked or stubbed, making unit testing more straightforward.
