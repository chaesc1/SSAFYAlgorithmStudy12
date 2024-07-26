package Day0726;

public class OperatorChallenge {
    public static void main(String[] args) {
        int a = 5, b = 10;
        boolean result = (++a > 5 && ++b > 10) || (a++ > 5 & b++ > 10);
        System.out.println("a: " + a + ", b: " + b + ", result: " + result);
    }
}