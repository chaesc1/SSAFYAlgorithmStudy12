package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");

            if(input.length==1){
                switch (input[0]){
                    case "top":
                        if (stack.size() == 0) {
                            System.out.println(-1);
                        }else{
                            System.out.println(stack.peek());
                        }
                        break;

                    case "size":
                        System.out.println(stack.size());
                        break;

                    case "empty":
                        if (stack.isEmpty()) {
                            System.out.println(1);
                        } else {
                            System.out.println(0);
                        }
                        break;

                    case "pop":
                        if(stack.isEmpty()){
                            System.out.println(-1);
                        }else {
                            System.out.println(stack.pop());
                        }
                        break;
                }
            }else{
                stack.push(Integer.parseInt(input[1]));
            }
        }
    }
}
