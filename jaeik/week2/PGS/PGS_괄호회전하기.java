package week2.PGS;

import java.util.*;

class PGS_올바른괄호 {
    public int solution(String s) {
        int answer = 0;

        for(int i=0; i<s.length(); i++){
            Stack<Character> stack = new Stack<>();

            String str = s.substring(i, s.length()) + s.substring(0,i);

            for(int j=0; j<str.length(); j++){
                char c = str.charAt(j);

                if(c == ')' && stack.peek() == '('){
                    stack.pop();
                }
                else if(c == '}' && stack.peek() == '{'){
                    stack.pop();
                }
                else if(c == ']' && stack.peek() == '['){
                    stack.pop();
                }
                else{
                    stack.push(c);
                }

            }
            if(stack.isEmpty()){
                answer++;
            }

        }
        return answer;
    }

}