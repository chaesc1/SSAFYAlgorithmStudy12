// 백준 10799번 쇠막대기 실버2
import java.io.*;
import java.util.*;
 
public class BOJ10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String bracket = bf.readLine();
        Stack<Character> stack = new Stack<>();
 
        int result = 0;
        for (int i = 0; i < bracket.length(); i++) {
            if (bracket.charAt(i) == '(') { 
                stack.push('(');
                continue;
            }
            if (bracket.charAt(i) == ')') { 
                stack.pop(); 
 
                if (bracket.charAt(i - 1) == '(') { 
                    result += stack.size();
                } else { 
                    result++; 
                }
            }
        }
        System.out.println(result + "\n");
    }
 
}
