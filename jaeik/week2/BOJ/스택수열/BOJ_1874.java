package BOJ.스택수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1874 {
public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder in = new StringBuilder();
        StringBuilder out = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        int cursor = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            int number = Integer.parseInt(br.readLine());
            in.append(number);

            if(cursor<number){
                for (int j=cursor+1; j<=number; j++){
                    stack.push(j);
                    sb.append("+").append("\n");
                }

                int pop = stack.pop();
                sb.append("-").append("\n");
                out.append(pop);
                cursor = number;
            }else {
                int pop = stack.pop();
                out.append(pop);
                sb.append("-").append("\n");
            }

        }

        String answer = (in.compareTo(out) == 0)?sb.toString():"NO";
        System.out.println(answer);
    }
}
