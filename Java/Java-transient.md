# transient keyword

### **Purpose of `transient`**
The `transient` keyword is used to mark a member variable not to be serialized when the object is serialized. This is crucial for scenarios where certain fields do not need to be saved, especially if they are sensitive or temporary in nature, like passwords or cached data.

### **Serialization Context**
Serialization is the process of converting an object into a byte stream, which can then be reverted back into a copy of the object. This is essential for saving object states to a file, or for transmitting them across a network.

### **Behavior of `transient` Fields**
When an object is deserialized (converted back from the byte stream to an object), the `transient` fields are not restored to their previous state. Instead, they are initialized to their default values:
- For object references, it is `null`.
- For primitive data types, it is the default value (e.g., `0` for `int`, `false` for `boolean`).

### **Example**
Consider the following code snippet:

```java
import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    transient int ssn;  // Social Security Number, marked as transient
    transient String password;

    Employee(String name, int ssn, String password) {
        this.name = name;
        this.ssn = ssn;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', ssn=" + ssn + ", password='" + password + "'}";
    }
}

public class TransientExample {
    public static void main(String[] args) {
        Employee e = new Employee("John Doe", 123456789, "password123");
        System.out.println("Before Serialization: " + e);

        // Serializing the object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            oos.writeObject(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Deserializing the object
        Employee deserializedEmployee = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.ser"))) {
            deserializedEmployee = (Employee) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.println("After Serialization: " + deserializedEmployee);
    }
}
```
In this example:
- The `ssn` and `password` fields are marked as `transient`.
- When serialized and deserialized, these fields will not retain their original values.

### **Use Cases**
1. **Sensitive Information**: Fields like passwords, credit card numbers, or any other sensitive information that should not be exposed or stored persistently.
2. **Derived Fields**: Fields that can be derived from other fields and do not need to be stored.
3. **Large Fields**: Fields that contain large data that is not necessary for serialization.

By effectively using the `transient` keyword, you can control the serialization process, protect sensitive data, and optimize performance.
