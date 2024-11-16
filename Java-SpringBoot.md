## Repeatable Annotation

In Java, repeatable annotations allow you to apply the same annotation multiple times to a single element (such as a class, method, or field). This feature was introduced in Java 8. To create a repeatable annotation, you need to define two annotations: the repeatable annotation itself and a container annotation that holds an array of the repeatable annotations.

### Steps to Create a Repeatable Annotation:
1. **Define the Repeatable Annotation**: This is the annotation that you want to be repeatable.
2. **Define the Container Annotation**: This annotation holds an array of the repeatable annotations.
3. **Use the `@Repeatable` Annotation**: Apply the `@Repeatable` annotation to the repeatable annotation, specifying the container annotation as its value.

### Example:
Let's create a repeatable annotation called `@Schedule` and a container annotation called `@Schedules`.

#### Step 1: Define the Repeatable Annotation
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Schedules.class)
public @interface Schedule {
    String day();
    String time();
}
```

#### Step 2: Define the Container Annotation
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Schedules {
    Schedule[] value();
}
```

#### Step 3: Use the Repeatable Annotation
```java
public class Meeting {

    @Schedule(day = "Monday", time = "10:00 AM")
    @Schedule(day = "Wednesday", time = "2:00 PM")
    public void teamMeeting() {
        // Method implementation
    }
}
```

### Explanation:
- **Repeatable Annotation (`@Schedule`)**: This annotation can be applied multiple times to the same method.
- **Container Annotation (`@Schedules`)**: This annotation holds an array of `@Schedule` annotations.
- **Usage**: The `teamMeeting` method is annotated with `@Schedule` twice, specifying different days and times.

### Benefits:
- **Flexibility**: Allows you to apply the same annotation multiple times with different values.
- **Readability**: Makes the code more readable and easier to manage.
