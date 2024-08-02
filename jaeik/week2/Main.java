import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String s = "()()";

        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for(char str : s.toCharArray()){
            if(str=='('){
                stack.push(str);
            }else if(str==')'){
                stack.pop();
            }
        }
    }
}
