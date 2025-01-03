## Object-Oriented Programming (OOP)

Encapsulation, Inheritance, Polymorphism, and Abstraction.

### Encapsulation

Encapsulation is the mechanism of restricting access to some of the object's components. It helps to protect the internal state of the object and to prevent accidental or unauthorized modifications.

- **Data Hiding**: Internal object details are hidden from the outside world. This is typically achieved through the use of access modifiers like `private`, `protected`, and `public` in languages like Java.
- **Getter and Setter Methods**: Instead of accessing fields directly, getter and setter methods are used to retrieve and modify private fields. For example:

```java
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

### Inheritance

Inheritance allows one class (subclass/derived class) to inherit the properties and behaviors (fields and methods) of another class (superclass/base class).

- **Super Class and Sub Class**: The superclass is the parent class, and the subclass is the child class that inherits from it.
- **`extends` Keyword**: In languages like Java, the `extends` keyword is used to define a subclass.

Example:
```java
public class Animal {
    public void eat() {
        System.out.println("This animal eats food.");
    }
}

public class Dog extends Animal {
    public void bark() {
        System.out.println("The dog barks.");
    }
}

// Main method to test
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();  // Inherited method
        dog.bark(); // Subclass method
    }
}
```

### Polymorphism

Polymorphism allows objects to be treated as instances of their parent class rather than their actual class. It enables one interface to be used for a general class of actions, and the specific action is determined by the exact nature of the situation.

- **Compile-time Polymorphism (Method Overloading)**: Multiple methods with the same name but different parameters.
- **Run-time Polymorphism (Method Overriding)**: A subclass provides a specific implementation for a method that is already defined in its superclass.

Example (Method Overloading):
```java
public class MathOperation {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}

// Main method to test
public class Main {
    public static void main(String[] args) {
        MathOperation math = new MathOperation();
        System.out.println(math.add(5, 3));        // Outputs: 8
        System.out.println(math.add(5.0, 3.0));    // Outputs: 8.0
    }
}
```

Example (Method Overriding):
```java
public class Animal {
    public void makeSound() {
        System.out.println("Some sound...");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

// Main method to test
public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.makeSound();  // Outputs: Bark
    }
}
```

### Abstraction

Abstraction focuses on hiding the complex implementation details and showing only the essential features of an object. It can be achieved using abstract classes and interfaces.

- **Abstract Classes**: Cannot be instantiated on their own and often provide a base implementation for other classes.
- **Interfaces**: Define a contract that implementing classes must fulfill.

Example (Abstract Class):
```java
abstract class Vehicle {
    public abstract void startEngine();

    public void commonMethod() {
        System.out.println("This is a common method in all vehicles.");
    }
}

public class Car extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }
}

// Main method to test
public class Main {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        myCar.startEngine();  // Outputs: Car engine started.
        myCar.commonMethod(); // Outputs: This is a common method in all vehicles.
    }
}
```

Example (Interface):
```java
interface Animal {
    void eat();
    void move();
}

public class Bird implements Animal {
    @Override
    public void eat() {
        System.out.println("Bird is eating.");
    }

    @Override
    public void move() {
        System.out.println("Bird is flying.");
    }
}

// Main method to test
public class Main {
    public static void main(String[] args) {
        Animal myBird = new Bird();
        myBird.eat();  // Outputs: Bird is eating.
        myBird.move(); // Outputs: Bird is flying.
    }
}
```

By utilizing these principles, you can create robust and maintainable software that leverages the power of object-oriented design.