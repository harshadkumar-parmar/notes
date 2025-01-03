### Inheritance
**Definition:** Inheritance is a mechanism where a new class (subclass) inherits properties and behavior (methods) from an existing class (superclass).

**Pros:**
- Promotes code reuse.
- Establishes an "is-a" relationship.
- Simplifies the code by using a common structure.

**Cons:**
- Can lead to tightly coupled code.
- Might introduce complexity with deep inheritance hierarchies.
- Less flexible than composition for changing behavior at runtime.

**Example:**
```java
// Superclass
class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

// Subclass
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound(); // Output: Bark
    }
}
```
In this example, `Dog` inherits from `Animal` and overrides the `makeSound` method.

### Composition
**Definition:** Composition is a design principle where a class is composed of one or more objects from other classes, allowing for more flexible code reuse and establishing a "has-a" relationship.

**Pros:**
- Promotes code reuse.
- More flexible than inheritance; allows for runtime changes.
- Reduces tight coupling between classes.

**Cons:**
- Can lead to more boilerplate code.
- Requires careful design to manage component interactions.

**Example:**
```java
// Component class
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

// Composite class
class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine();
    }

    void start() {
        engine.start();
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.start(); // Output: Engine started
    }
}
```
In this example, `Car` has an `Engine` object, demonstrating a "has-a" relationship.

### Key Differences
| Aspect              | Inheritance                                      | Composition                                      |
|---------------------|--------------------------------------------------|--------------------------------------------------|
| Relationship        | "is-a"                                           | "has-a"                                          |
| Code Reuse          | Through inheritance                              | Through delegation                               |
| Flexibility         | Less flexible, changes require modifying classes | More flexible, changes can be made by replacing components |
| Coupling            | Tightly coupled                                  | Loosely coupled                                  |
| Runtime Behavior    | Fixed at compile-time                            | Can change at runtime                            |

### When to Use
- **Inheritance:** Use when there is a clear hierarchical relationship, and the subclass truly "is-a" type of the superclass.
- **Composition:** Use when you want to build complex types from simpler components and need more flexibility in changing the behavior.
