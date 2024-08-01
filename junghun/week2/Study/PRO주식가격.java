import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        // prices 의 인덱스를 스택에 담아
        stack.push(0);
        
        for(int i=1; i<prices.length; i++) {
            // prices[i] > prices[stack.peek()] --> 증가하는 구간
            // prices[i] < prices[stack.peek()] --> 감소하는 구간
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int day = stack.pop();
                
                answer[day] = i - day;
            }
            stack.push(i);
        }
        // 증가하는 구간의 수의 인덱스만 남아 있어
        while(!stack.isEmpty()) {
            int day = stack.pop();
            // System.out.println(day);
            answer[day] = prices.length - 1 - day;
        }
        return answer;
    }
}