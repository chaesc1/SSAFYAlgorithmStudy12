package week2.PGS;

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for(char str : s.toCharArray()){
            if(str=='('){
                stack.push(str);
            }else if(!stack.isEmpty() && str==')'){
                stack.pop();
            }else{
                return false;
            }
        }

        answer = (stack.isEmpty())?true:false;

        return answer;
    }
}