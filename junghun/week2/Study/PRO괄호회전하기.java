import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        int len = s.length();
        s+=s;
        //[](){}[](){} -> 두개를 이어 붙혀서 회전한 효과를 줌
        A:for(int i=0; i<len; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            for(int j=i; j<i+len; j++) {
                char tmp = s.charAt(j);

                if(!map.containsKey(tmp)) {
                    stack.push(tmp);
                } else {
                    if(stack.isEmpty() || !stack.pop().equals(map.get(tmp))) {
                        continue A;
                    }
                }
            }
            if(stack.isEmpty()) {
                answer++;
            }
        }
        return answer;
    }
}