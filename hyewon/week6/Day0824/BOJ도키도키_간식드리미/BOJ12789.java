package BOJ.DAY0824.BOJ도키도키_간식드리미;

import java.util.*;
import java.io.*;

public class BOJ12789 {
    static Stack<Integer> stack = new Stack<>();
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        cnt = 1;
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(cnt != num) stack.push(num);
            else {
                cnt++;
                check();
            }
        }
        check();
        if(stack.isEmpty()) System.out.println("Nice");
        else System.out.println("Sad");
    }
    public static void check() {
        while(!stack.isEmpty()) {
            if(cnt == stack.peek()) {
                stack.pop();
                cnt++;
            }
            else break;
        }
    }
}
