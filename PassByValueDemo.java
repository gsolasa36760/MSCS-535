public class PassByValueDemo {
    
    // Inner class to demonstrate object passing
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + '}';
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java Pass-by-Value Demonstration ===\n");
        
        // 1. Primitive type (passed by value)
        int number = 10;
        System.out.println("1. Primitive Type (int):");
        System.out.println("Before method call: number = " + number);
        modifyPrimitive(number);
        System.out.println("After method call: number = " + number);
        
        // 2. Object reference (reference is passed by value)
        Person person = new Person("Alice", 25);
        System.out.println("\n2. Object Reference:");
        System.out.println("Before method call: " + person);
        modifyObject(person);
        System.out.println("After method call: " + person);
        
        // 3. String (immutable object)
        String message = "Hello";
        System.out.println("\n3. String (Immutable Object):");
        System.out.println("Before method call: message = " + message);
        modifyString(message);
        System.out.println("After method call: message = " + message);
        
        // 4. Reassigning reference
        System.out.println("\n4. Reassigning Reference:");
        Person person1 = new Person("Bob", 30);
        System.out.println("Before method call: " + person1);
        reassignReference(person1);
        System.out.println("After method call: " + person1);
    }
    
    // Method that tries to modify a primitive (won't affect the original)
    public static void modifyPrimitive(int num) {
        System.out.println("  Inside method - before modification: " + num);
        num = 20;
        System.out.println("  Inside method - after modification: " + num);
    }
    
    // Method that modifies the object's state (will affect the original object)
    public static void modifyObject(Person p) {
        System.out.println("  Inside method - before modification: " + p);
        p.age = 30;  // Modifying the object's state
        p.name = "Alice Modified";
        System.out.println("  Inside method - after modification: " + p);
    }
    
    // Method that tries to modify a String (won't affect the original)
    public static void modifyString(String str) {
        System.out.println("  Inside method - before modification: " + str);
        str = str + " World";  // Creates a new String object
        System.out.println("  Inside method - after modification: " + str);
    }
    
    // Method that tries to reassign the reference (won't affect the original reference)
    public static void reassignReference(Person p) {
        System.out.println("  Inside method - before reassignment: " + p);
        p = new Person("New Person", 100);  // Reassigning the reference
        System.out.println("  Inside method - after reassignment: " + p);
    }
}
