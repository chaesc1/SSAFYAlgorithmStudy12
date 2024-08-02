package BOJ.괄호의값;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Stack<Character> stack = new Stack<>();
        boolean flag = false;
        int total = 0;
        int state = 1;

        for(int i=0; i<line.length(); i++){
            char token = line.charAt(i);

            if(token=='('){
                stack.push(token);
                state *= 2;
            }
            else if(token == '['){
                stack.push(token);
                state *=3;
            }
            else{
                if(token==')'){
                    if(stack.peek()!='(' || stack.isEmpty()){
                        flag = true;
                        break;
                    } else if (line.charAt(i-1) == '(') {
                        total += state;
                    }
                    stack.pop();
                    state /= 2;
                }
                else if(token==']'){
                    if(stack.peek()!='[' || stack.isEmpty()){
                        flag = true;
                        break;
                    } else if (line.charAt(i-1) == '[') {
                        total += state;
                    }
                    stack.pop();
                    state /= 3;
                }
                else{
                    flag = true;
                    break;
                }
            }
        }

        int result = (flag || !stack.isEmpty())?0:total;
        System.out.println(result);
    }
}
