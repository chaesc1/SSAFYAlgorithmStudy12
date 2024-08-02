package BOJ.제로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<k; i++){
            int number = Integer.parseInt(br.readLine());

            if(number == 0){
                stack.pop();
                continue;
            }
            stack.add(number);
        }

        int result = 0;
        for(Integer num : stack){
            result += num;
        }

        System.out.println(result);
    }
}
