package Programmers;

import java.util.ArrayDeque;
import java.util.Stack;

public class Level2_괄호회전하기 {
    public static void main(String[] args) {
        String s = "[)(]";

        int answer = 0;

        //ArrayDeque
        ArrayDeque<Character> dq = new ArrayDeque<>();
        for (int i = s.length()-1; i >= 0; i--) {
            dq.addFirst(s.charAt(i));
        }

        //회전 + 판단
        for(int i=0; i<s.length(); i++){
            String rotated = rotate(s, dq, i);

            answer += judge(rotated);
        }

        System.out.println(answer);
    }

    static int judge(String rotated){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<rotated.length(); i++){
            char ch = rotated.charAt(i);

            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }else if(!stack.isEmpty() && (ch == ')' || ch == '}' || ch == ']')){
                stack.pop();
            }else return 0;
        }

        if(stack.isEmpty()){
            System.out.println(rotated);
        }
        return (stack.isEmpty())?1:0;
    }

    static String rotate(String s, ArrayDeque<Character> dq, int count){
        ArrayDeque<Character> copied = new ArrayDeque<>(dq);

        for (int i = 0; i < count; i++) {
            copied.addLast(copied.pop());
        }

        StringBuilder sb = new StringBuilder();
        while(!copied.isEmpty()){
            sb.append(copied.pop());
        }

        return sb.toString();
    }
}
