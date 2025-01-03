# Dependency Injection

Dependency Injection (DI) in Java is a popular design pattern that helps manage dependencies between objects, promoting loose coupling and enhancing testability and maintainability of code. This pattern is commonly implemented using frameworks like Spring. Here’s an overview of DI in Java:

### Key Concepts of Dependency Injection:
1. **Dependency**: An object that another object requires to function.
2. **Injection**: Supplying an external dependency to a class rather than creating it internally.

### Types of Dependency Injection:
1. **Constructor Injection**:
   - Dependencies are provided through the class constructor.
   ```java
   public class Engine {
       public void start() {
           System.out.println("Engine started");
       }
   }

   public class Car {
       private Engine engine;

       @Autowired
       public Car(Engine engine) {
           this.engine = engine;
       }

       public void drive() {
           engine.start();
           System.out.println("Car is driving");
       }
   }
   ```

2. **Setter Injection**:
   - Dependencies are provided through setter methods.
   ```java
   public class Engine {
       public void start() {
           System.out.println("Engine started");
       }
   }

   public class Car {
       private Engine engine;

       @Autowired
       public void setEngine(Engine engine) {
           this.engine = engine;
       }

       public void drive() {
           engine.start();
           System.out.println("Car is driving");
       }
   }
   ```

3. **Field Injection**:
   - Dependencies are directly assigned to fields using annotations.
   ```java
   public class Engine {
       public void start() {
           System.out.println("Engine started");
       }
   }

   public class Car {
       @Autowired
       private Engine engine;

       public void drive() {
           engine.start();
           System.out.println("Car is driving");
       }
   }
   ```

### Using Spring Framework for Dependency Injection:
Spring is a powerful framework that provides robust support for DI. Here’s a simple example using Spring:

1. **Define the Beans**:
   ```java
   @Configuration
   public class AppConfig {
       @Bean
       public Engine engine() {
           return new Engine();
       }

       @Bean
       public Car car() {
           return new Car(engine());
       }
   }
   ```

2. **Autowired Annotation**:
   ```java
   @Component
   public class Car {
       private Engine engine;

       @Autowired
       public Car(Engine engine) {
           this.engine = engine;
       }

       public void drive() {
           engine.start();
           System.out.println("Car is driving");
       }
   }
   ```

3. **Main Application**:
   ```java
   public class Main {
       public static void main(String[] args) {
           ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
           Car car = context.getBean(Car.class);
           car.drive();
       }
   }
   ```

### Benefits of Dependency Injection:
1. **Decoupling**: Classes are less dependent on concrete implementations.
2. **Testability**: Easier to inject mock dependencies for unit testing.
3. **Maintainability**: Easier to change and manage dependencies.
4. **Flexibility**: Components can be easily configured and reconfigured.
