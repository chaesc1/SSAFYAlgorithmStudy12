package BOJ.Implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class P2504 {
    static String str;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        int length = str.length();

        Stack<Character> stack = new Stack<>();

        int tmp = 1;
        int answer = 0;
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            // 여는 괄호면 스택에 넣어
            if (isOpen(ch)) {
                stack.push(ch); // ( -> 2 , [ -> 3
                tmp *= calculate(ch);
            } else { // 닫힌 괄호가 나오면
                if (stack.isEmpty()) { // 빈 스택이면 ? 괄호 쌍이 이상한 경우
                    answer = 0;
                    break;
                } else {
                    char next  = stack.pop();
                    if (ch == ')') { // 다음 괄호가 닫는거면
                        if (next != '(') { // Pop 한게 짝이 맞지 않으면 이상한 괄호이다.
                            answer = 0;
                            break;
                        } else {
                            if (str.charAt(i-1) == '(') {
                                answer += tmp;
                            }
                            tmp /= 2;
                        }
                    } else {
                        if (next != '[') {
                            answer = 0;
                            break;
                        } else {
                            if (str.charAt(i-1) == '[') {
                                answer += tmp;
                            }
                            tmp /= 3;
                        }
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            answer = 0;
        }
        System.out.println(answer);
    }
    private static int calculate(char ch) {
        if (ch == '(') return 2;
        else return 3;
    }
    private static boolean isOpen(char ch) {
        return ch == '(' || ch == '[';
    }
}
