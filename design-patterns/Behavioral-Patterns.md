# Behavioral patterns

Behavioral patterns are design patterns that deal with object collaboration and how they interact with each other to achieve complex tasks. They help in defining communication between objects in a flexible and maintainable way. Here's an overview of some common behavioral patterns along with examples in Java:


## The Chain of Responsibility pattern
The Chain of Responsibility pattern is a behavioral design pattern that allows a request to be passed along a chain of handlers. Each handler in the chain either handles the request or passes it to the next handler. This pattern is useful for scenarios where multiple objects might need to process a request, but the handler is determined at runtime.

### Key Concepts

- **Handler**: An abstract class or interface that declares the method to process the request.
- **ConcreteHandler**: Subclasses of the Handler that implement the request processing and decide whether to pass the request to the next handler.
- **Client**: The entity that initiates the request and sets up the chain of handlers.

### Advantages

- **Decoupling**: The sender of a request is decoupled from its receivers.
- **Flexibility**: You can change the chain of handlers dynamically.
- **Responsibility Sharing**: Multiple objects get a chance to handle the request.

### Example in Java

Here’s an example of how to implement the Chain of Responsibility pattern in Java:

```java
// Step 1: Create the Handler Interface
public interface Handler {
    void setNextHandler(Handler handler);
    void handleRequest(String request);
}

// Step 2: Create Abstract Handler Class
public abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    public void handleRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Step 3: Create Concrete Handlers
public class ConcreteHandlerA extends AbstractHandler {
    public void handleRequest(String request) {
        if (request.equals("A")) {
            System.out.println("Handler A processed the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

public class ConcreteHandlerB extends AbstractHandler {
    public void handleRequest(String request) {
        if (request.equals("B")) {
            System.out.println("Handler B processed the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

// Step 4: Set Up the Chain and Handle the Request
public class Client {
    public static void main(String[] args) {
        // Create handlers
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        // Set up the chain
        handlerA.setNextHandler(handlerB);

        // Handle requests
        handlerA.handleRequest("A");
        handlerA.handleRequest("B");
        handlerA.handleRequest("C");
    }
}
```

### Explanation

1. **Handler Interface**: Defines the methods for setting the next handler and handling the request.
2. **AbstractHandler Class**: Implements the methods of the Handler interface and provides a default implementation for passing the request to the next handler.
3. **ConcreteHandlers**: Implement the request handling logic and decide whether to process the request or pass it to the next handler.
4. **Client**: Sets up the chain of handlers and initiates the request.

## Use cases

The Chain of Responsibility pattern is quite versatile and can be applied in various scenarios where multiple handlers may process a request. Here are some common use cases:

### Use Cases of the Chain of Responsibility Pattern

#### 1. **Logging Systems**
In logging frameworks, different loggers (e.g., console logger, file logger, remote logger) can be chained together. Each logger decides whether to log the message based on its level (e.g., INFO, DEBUG, ERROR) and passes the message to the next logger in the chain if necessary.

#### 2. **Event Handling**
In GUI applications, events like mouse clicks or keyboard presses can be handled by different components. Each component in the chain checks if it can handle the event, and if not, it passes the event to the next component.

#### 3. **Middleware in Web Servers**
Middleware components in web servers handle various aspects of HTTP requests, such as authentication, logging, data compression, etc. Each middleware component processes the request and either handles it or passes it to the next middleware component.

#### 4. **Error Handling**
In error handling systems, different error handlers can be chained to process various types of errors. Each handler checks if it can handle the specific error and either processes it or passes it to the next handler.

#### 5. **Request Processing Pipelines**
In enterprise applications, requests might need to go through various processing stages, such as validation, authentication, authorization, and business logic execution. Each stage in the chain processes the request and passes it to the next stage if necessary.

#### 6. **Form Validation**
In web applications, form fields can be validated by different validators. Each validator checks if a specific validation rule is satisfied, and if not, it passes the form data to the next validator.

#### 7. **Command Processing**
In command-based systems, different commands can be processed by different handlers. Each handler checks if it can process the given command and either processes it or passes it to the next handler.

### Summary

The Chain of Responsibility pattern is useful for scenarios where a request can be handled by multiple handlers, but the handler is determined at runtime. This pattern promotes flexibility, decouples the sender from the receiver, and allows dynamic changes to the chain of handlers.


## Command Pattern

The Command pattern is a behavioral design pattern that turns a request into a stand-alone object containing all the information about the request. This transformation allows you to parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

### Key Concepts

- **Command**: An interface or abstract class that declares an execution method.
- **ConcreteCommand**: A class that implements the Command interface and defines a binding between a Receiver object and an action.
- **Receiver**: The object that performs the actual work when the Command's execute method is called.
- **Invoker**: The object that knows how to execute a command but doesn't know anything about the concrete command.
- **Client**: The object that creates and configures the Command objects.

### Advantages

- **Decoupling**: The Command pattern decouples the object that invokes the operation from the one that knows how to perform it.
- **Undo/Redo**: You can implement undo/redo functionality by storing a history of executed commands.
- **Parameterization and Queuing**: You can parameterize objects with operations and support queuing of requests.

### Example in Java

Here’s an example of how to implement the Command pattern in Java:

```java
// Step 1: Create the Command Interface
public interface Command {
    void execute();
}

// Step 2: Create Concrete Command Classes
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}

// Step 3: Create the Receiver Class
public class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

// Step 4: Create the Invoker Class
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Step 5: Set Up and Execute Commands
public class Client {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        // Turn the light on
        remote.setCommand(lightOn);
        remote.pressButton();

        // Turn the light off
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
```

### Explanation

1. **Command Interface**: Defines the execute method that all concrete commands must implement.
2. **Concrete Command Classes**: Implement the Command interface and define the execute method to invoke the corresponding method on the Receiver.
3. **Receiver Class**: Contains the actual business logic to perform the action (e.g., turning the light on/off).
4. **Invoker Class**: Stores the Command object and calls its execute method.
5. **Client**: Creates the Receiver, Command objects, and Invoker, sets up the commands, and triggers their execution.

### Use Cases

- **Undo/Redo Functionality**: Implement undo and redo operations in text editors, drawing applications, and other software requiring reversible operations.
- **Macro Recording**: Record a series of operations to be executed later in applications like macros in spreadsheets or scriptable software.
- **Job Scheduling**: Schedule and execute jobs or tasks in a controlled manner, such as cron jobs or task queues.
- **Remote Control Systems**: Control various devices or systems by encapsulating requests as commands (e.g., home automation systems).

### Summary

The Command pattern encapsulates requests as objects, allowing for decoupling, undo/redo operations, and flexible request management. It's a powerful pattern for managing behaviors and actions in software systems.


3. Interpreter
Defines a grammatical representation for a language and an interpreter to interpret sentences in the language.

Example: Compilers and interpreters for programming languages.

4. Iterator
Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

Example: Iterating over collections like lists, arrays, or custom data structures.

5. Mediator
Defines an object that encapsulates how a set of objects interact. This pattern promotes loose coupling by preventing objects from referring to each other explicitly.

Example: Chat applications where the mediator (chat server) handles communication between users.

6. Memento
Captures and externalizes an object's internal state so that it can be restored later without violating encapsulation.

Example: Implementing save and restore functionality in applications.

7. Observer
Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

Example: Implementing the publish-subscribe pattern in event-driven systems.

8. State
Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

Example: State machines for game development where an object's behavior changes based on its state (e.g., character states like idle, running, jumping).

## Strategy pattern

The Strategy pattern is a behavioral design pattern that allows you to define a family of algorithms, encapsulate each one, and make them interchangeable. This pattern lets the algorithm vary independently from the clients that use it.

### Key Concepts

- **Strategy**: An interface that defines a set of algorithms.
- **ConcreteStrategy**: Classes that implement the Strategy interface and provide specific algorithms.
- **Context**: A class that uses a Strategy to perform a task. The Context doesn't know which Strategy it is using; it just relies on the Strategy interface.

### Advantages

- **Flexibility**: You can change the algorithm used by the Context at runtime.
- **Encapsulation**: Each algorithm is encapsulated in its own class, making it easier to understand and maintain.
- **Decoupling**: The Context class is decoupled from the specific implementation of the algorithms.

### Example in Java

Here’s an example of how to implement the Strategy pattern in Java:

```java
// Step 1: Create the Strategy Interface
public interface Strategy {
    int doOperation(int num1, int num2);
}

// Step 2: Create Concrete Strategy Classes
public class AdditionStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

public class SubtractionStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

public class MultiplicationStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

// Step 3: Create the Context Class
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

// Step 4: Use the Context with Different Strategies
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new AdditionStrategy());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new SubtractionStrategy());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new MultiplicationStrategy());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
```

### Explanation

1. **Strategy Interface**: Defines a common interface for all algorithms (`doOperation` in this case).
2. **Concrete Strategy Classes**: Implement the Strategy interface with specific algorithms (`AdditionStrategy`, `SubtractionStrategy`, `MultiplicationStrategy`).
3. **Context Class**: Uses a Strategy to perform its task. It can change the strategy dynamically using `setStrategy`.
4. **Client Class**: Creates and sets up different strategies for the Context and executes them.

### Use Cases

- **Sorting Algorithms**: Implement different sorting algorithms (e.g., quicksort, mergesort, bubblesort) as strategies and select the appropriate one at runtime based on the data size or characteristics.
- **Payment Methods**: Implement different payment methods (e.g., credit card, PayPal, bank transfer) as strategies and select the appropriate one based on user preference or transaction context.
- **File Compression**: Implement different file compression algorithms (e.g., ZIP, RAR, GZIP) as strategies and select the appropriate one based on user preference or file type.
- **Route Planning**: Implement different route planning algorithms (e.g., shortest path, fastest route, scenic route) as strategies and select the appropriate one based on user preference or travel context.

### Summary

The Strategy pattern allows you to define a family of algorithms, encapsulate them, and make them interchangeable. It promotes flexibility, encapsulation, and decoupling, making it easier to manage and maintain complex algorithms.

Example: Implementing different sorting algorithms that can be selected at runtime.


## Template Method
The Template Method pattern is a behavioral design pattern that defines the skeleton of an algorithm in a base class but allows subclasses to override specific steps of the algorithm without changing its overall structure. This pattern promotes code reuse and helps in defining a clear sequence of steps while providing flexibility for customization.

### Key Concepts

- **Template Method**: A method in the base class that defines the sequence of steps for an algorithm.
- **Abstract Steps**: Steps defined in the base class but implemented in subclasses.
- **Concrete Steps**: Steps implemented in the base class that are common to all subclasses.

### Advantages

- **Code Reuse**: Promotes code reuse by defining common behavior in a base class.
- **Flexibility**: Allows subclasses to customize specific steps of an algorithm.
- **Consistency**: Ensures a consistent structure for algorithms across subclasses.

### Example in Java

Here’s an example of how to implement the Template Method pattern in Java:

```java
// Step 1: Create the Abstract Class with the Template Method
public abstract class Game {
    // Template Method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

    // Abstract methods to be implemented by subclasses
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
}

// Step 2: Create Concrete Classes that Implement the Abstract Methods
public class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

// Step 3: Use the Template Method
public class Client {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();

        game = new Football();
        game.play();
    }
}
```

### Explanation

1. **Abstract Class (Game)**: Defines the Template Method (`play()`) and abstract steps (`initialize()`, `startPlay()`, `endPlay()`) that subclasses must implement.
2. **Concrete Classes (Cricket, Football)**: Implement the abstract steps defined in the base class.
3. **Client Code**: Uses the Template Method to execute the algorithm with the specific implementations provided by the subclasses.

### Use Cases

- **Frameworks and Libraries**: Define the skeleton of algorithms in a framework or library while allowing application developers to provide specific implementations.
- **Document Generation**: Generate documents with a fixed structure (header, content, footer) but allow customization of specific sections.
- **Game Development**: Define the structure of a game (initialization, start, end) while allowing specific games to implement their own details.
- **Workflow Processes**: Define workflows with a common sequence of steps but allow customization of individual steps for different processes.

### Summary

The Template Method pattern defines the skeleton of an algorithm in a base class and allows subclasses to customize specific steps. This promotes code reuse, flexibility, and consistency across different implementations of the algorithm.

11. Visitor
Represents an operation to be performed on the elements of an object structure. This pattern lets you define a new operation without changing the classes of the elements on which it operates.

Example: Implementing operations on complex data structures like ASTs (Abstract Syntax Trees) in compilers.