package Day0726;

class Parent {
    void call() {
        System.out.println("Parent call");
    }
}

class Child extends Parent {
    @Override
    void call() {
        System.out.println("Child call");
    }

    void specificCall() {
        System.out.println("Specific Child call");
    }
}

public class DynamicBinding {
    public static void main(String[] args) {
        Parent p = new Child();
        p.call();
//        p = new Child();
        ((Child) p).specificCall();
    }
}
