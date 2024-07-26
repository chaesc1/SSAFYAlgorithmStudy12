package Day0726;

class Animal {
    static void staticMethod() {
        System.out.println("Animal static method");
    }

    void instanceMethod() {
        System.out.println("Animal instance method");
    }
}

class Dog extends Animal {
    static void staticMethod() {
        System.out.println("Dog static method");
    }


    void instanceMethod() {
        System.out.println("Dog instance method");
    }
}

public class BindingTest {
    public static void main(String[] args) {
        Animal a = new Dog();
        Dog d = new Dog();
        
        a.staticMethod();
        a.instanceMethod();
        d.staticMethod();
        d.instanceMethod();
    }
}
