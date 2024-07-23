package BOJ.Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 8 이라면
        // 1 2 3 4 5 6 7 8 로 push
        /**
         * ex ) 5 , 4 2 3 1 5
         * 1
         * 1 2
         * 1 2 3
         * 1 2 3 4 < push
         * pop
         * 1 2 3 -> 4
         * 1 2 pop -> 3
         * 1 pop -> 2
         */

        boolean isOk = true;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int tmp = 1;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            for (; tmp <= num; tmp++) {
                stack.push(tmp);
                sb.append("+").append("\n");
            }

            if (stack.peek() == num) {
                stack.pop();
                sb.append("-").append("\n");
            } else {
                isOk = false;
            }
        }

        if(isOk) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }

    }
}
