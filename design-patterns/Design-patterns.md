# Design Patterns

| **Pattern Type**  | **Description**                                                                                     | **Examples of Patterns**                                                              |
|--------------------|-----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Creational**     | These patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. | Singleton, Factory Method, Abstract Factory, Builder, Prototype                        |
| **Structural**     | These patterns deal with object composition, or how classes and objects are composed to form larger structures.       | Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy                        |
| **Behavioral**     | These patterns are concerned with algorithms and the assignment of responsibilities between objects.              | Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor |

Absolutely! Here are simple definitions for some common design patterns:

### Creational Patterns

1. **Singleton**
   - Ensures a class has only one instance and provides a global point of access to it.

2. **Factory Method**
   - Defines an interface for creating an object but lets subclasses alter the type of objects that will be created.

3. **Abstract Factory**
   - Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

4. **Builder**
   - Separates the construction of a complex object from its representation, allowing the same construction process to create various representations.

5. **Prototype**
   - Specifies the kind of objects to create using a prototypical instance and creates new objects by copying this prototype.

### Structural Patterns

1. **Adapter**
   - Allows incompatible interfaces to work together by acting as a bridge between them.

2. **Bridge**
   - Decouples an abstraction from its implementation so that the two can vary independently.

3. **Composite**
   - Composes objects into tree structures to represent part-whole hierarchies, allowing clients to treat individual objects and compositions uniformly.

4. **Decorator**
   - Adds additional responsibilities to an object dynamically, providing a flexible alternative to subclassing for extending functionality.

5. **Facade**
   - Provides a simplified interface to a complex subsystem, making it easier to use.

6. **Flyweight**
   - Reduces memory usage by sharing as much data as possible with similar objects, often used for large numbers of similar objects.

7. **Proxy**
   - Provides a surrogate or placeholder for another object to control access to it.

### Behavioral Patterns

1. **Chain of Responsibility**
   - Passes a request along a chain of handlers, where each handler either processes the request or passes it to the next handler.

2. **Command**
   - Turns a request into an object, allowing parameterization of clients with queues, requests, and operations.

3. **Interpreter**
   - Defines a grammatical representation for a language and an interpreter to interpret sentences in the language.

4. **Iterator**
   - Provides a way to access elements of a collection sequentially without exposing its underlying representation.

5. **Mediator**
   - Defines an object that encapsulates how a set of objects interact, promoting loose coupling.

6. **Memento**
   - Captures and externalizes an object's internal state so it can be restored later without violating encapsulation.

7. **Observer**
   - Defines a one-to-many dependency so that when one object changes state, all its dependents are notified and updated automatically.

8. **State**
   - Allows an object to change its behavior when its internal state changes, appearing to change its class.

9. **Strategy**
   - Defines a family of algorithms, encapsulates each one, and makes them interchangeable.

10. **Template Method**
    - Defines the skeleton of an algorithm, allowing subclasses to redefine certain steps without changing the algorithm's structure.

11. **Visitor**
    - Represents an operation to be performed on the elements of an object structure, allowing you to define new operations without changing the classes of the elements on which it operates.
