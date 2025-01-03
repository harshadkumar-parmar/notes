# OOP

Object-Oriented Programming (OOP) in TypeScript brings familiar OOP concepts like classes, inheritance, polymorphism, and encapsulation to a robust type system. Here's a quick overview:

## Classes:
   - TypeScript extends JavaScript classes with type annotations and other features.
   - Example:
```typescript
class Animal {
  name: string;

  constructor(name: string) {
    this.name = name;
  }

  move(distance: number) {
    console.log(`${this.name} moved ${distance} meters.`);
  }
}

const dog = new Animal('Dog');
dog.move(10);
```

## Inheritance:
   - TypeScript supports inheritance using the `extends` keyword.
   - Example:
```typescript
class Bird extends Animal {
  fly(distance: number) {
    console.log(`${this.name} flew ${distance} meters.`);
  }
}

const bird = new Bird('Sparrow');
bird.fly(15);
```

## Interfaces:
   - Interfaces define the structure that a class should follow.
   - Example:
```typescript
interface Drivable {
  speed: number;
  drive(): void;
}

class Car implements Drivable {
  speed: number;
  
  constructor(speed: number) {
    this.speed = speed;
  }

  drive() {
    console.log(`Driving at ${this.speed} km/h.`);
  }
}

const myCar = new Car(80);
myCar.drive();
```

## Access Modifiers:
   - `public`, `private`, and `protected` control the visibility of properties and methods.
   - Example:
```typescript
class Person {
  private age: number;

  constructor(age: number) {
    this.age = age;
  }

  getAge() {
    return this.age;
  }
}

const person = new Person(25);
console.log(person.getAge()); // Allowed
// console.log(person.age); // Error: 'age' is private
```

## Abstract Classes:
   - Abstract classes cannot be instantiated and are designed to be extended.
   - Example:
```typescript
abstract class Shape {
  abstract area(): number;
}

class Circle extends Shape {
  radius: number;

  constructor(radius: number) {
    super();
    this.radius = radius;
  }

  area() {
    return Math.PI * this.radius ** 2;
  }
}

const circle = new Circle(10);
console.log(circle.area());
```

These concepts make TypeScript a powerful language for building complex and scalable applications while leveraging the benefits of strong typing and modern JavaScript features.


## Polymorphism

Polymorphism is one of the core principles of Object-Oriented Programming (OOP) and it allows objects of different classes to be treated as objects of a common superclass. There are two types of polymorphism: **compile-time (or static)** and **run-time (or dynamic)**.

### Compile-time Polymorphism
This type of polymorphism is achieved through method overloading and operator overloading. It is resolved during compile time.

**Example of Method Overloading**:
```typescript
class Calculator {
  add(a: number, b: number): number;
  add(a: string, b: string): string;
  add(a: any, b: any): any {
    return a + b;
  }
}

const calc = new Calculator();
console.log(calc.add(10, 20)); // Outputs: 30
console.log(calc.add('Hello, ', 'World!')); // Outputs: Hello, World!
```

### Run-time Polymorphism
This type of polymorphism is achieved through method overriding and is resolved at run-time.

**Example of Method Overriding**:
```typescript
class Animal {
  makeSound(): void {
    console.log('Animal makes a sound');
  }
}

class Dog extends Animal {
  makeSound(): void {
    console.log('Dog barks');
  }
}

class Cat extends Animal {
  makeSound(): void {
    console.log('Cat meows');
  }
}

const animals: Animal[] = [new Dog(), new Cat(), new Animal()];
animals.forEach(animal => animal.makeSound());
// Outputs: Dog barks, Cat meows, Animal makes a sound
```

## Interfaces and Polymorphism
Interfaces in TypeScript allow us to define a contract for classes and enable polymorphism by ensuring that different classes can be treated uniformly.

**Example**:
```typescript
interface Shape {
  area(): number;
}

class Circle implements Shape {
  radius: number;
  
  constructor(radius: number) {
    this.radius = radius;
  }
  
  area(): number {
    return Math.PI * this.radius * this.radius;
  }
}

class Rectangle implements Shape {
  width: number;
  height: number;
  
  constructor(width: number, height: number) {
    this.width = width;
    this.height = height;
  }
  
  area(): number {
    return this.width * this.height;
  }
}

const shapes: Shape[] = [new Circle(10), new Rectangle(5, 10)];
shapes.forEach(shape => console.log(shape.area()));
// Outputs: 314.1592653589793, 50
```

Polymorphism allows you to write more flexible and reusable code. You can define general behaviors in a base class or interface, and then override or implement these behaviors in derived classes, enabling different types of objects to be used interchangeably.

### Interfaces
- **Purpose**: Define a contract that a class must adhere to. They describe what methods and properties a class should have, but they don't provide any implementation.
- **Implementation**: Classes can implement multiple interfaces.
- **Flexibility**: Ideal for defining the shape of objects and ensuring consistency across different parts of an application.

**Example**:
```typescript
interface Flyable {
  fly(): void;
}

interface Swimmable {
  swim(): void;
}

class Duck implements Flyable, Swimmable {
  fly(): void {
    console.log("Duck is flying");
  }
  
  swim(): void {
    console.log("Duck is swimming");
  }
}
```

### Abstract Classes
- **Purpose**: Provide a base class with some implemented methods while allowing derived classes to complete the implementation. They can define both abstract methods (without implementation) and non-abstract methods (with implementation).
- **Implementation**: A class can extend only one abstract class.
- **Flexibility**: Useful when you want to share common behavior among multiple classes while still requiring derived classes to implement specific methods.

**Example**:
```typescript
abstract class Animal {
  constructor(public name: string) {}

  abstract makeSound(): void;

  move(): void {
    console.log(`${this.name} is moving`);
  }
}

class Dog extends Animal {
  makeSound(): void {
    console.log("Woof! Woof!");
  }
}

class Cat extends Animal {
  makeSound(): void {
    console.log("Meow! Meow!");
  }
}

const dog = new Dog("Buddy");
dog.makeSound(); // Outputs: Woof! Woof!
dog.move();      // Outputs: Buddy is moving
```

### Key Differences:
1. **Multiple Inheritance**: 
   - Interfaces allow for the implementation of multiple interfaces, providing greater flexibility.
   - Abstract classes support single inheritance but can implement multiple interfaces.

2. **Implementation**:
   - Interfaces do not contain any implementation; they only declare members.
   - Abstract classes can provide some implementation details, allowing derived classes to reuse code.

3. **Usage**:
   - Use interfaces when you want to define a contract that various classes can implement.
   - Use abstract classes when you want to provide a common base class with shared code and enforce specific method implementation in derived classes.

Here's a summary table for clarity:

| Feature              | Interface                           | Abstract Class                  |
|----------------------|-------------------------------------|---------------------------------|
| Purpose              | Define a contract                   | Define a common base class      |
| Multiple Inheritance | Yes                                 | No                              |
| Implementation       | No implementation                   | Can contain implementation      |
| Flexibility          | High (can be implemented by any class) | Moderate (single inheritance)   |
