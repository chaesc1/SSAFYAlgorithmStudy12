package BOJ.탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int height = Integer.parseInt(st.nextToken());
            stack.push(height);
        }

        int[] result = new int[n];
        List<Integer> list = new ArrayList<>(stack);
        while(!stack.isEmpty()){
            int pop = stack.pop();

            int idx = stack.size();
            while(idx-- > 0){
                if(pop <= list.get(idx)){
                    result[stack.size()] = idx+1;
                    break;
                }
            }
        }

        for(int i=0; i< result.length; i++){
            System.out.println(result[i]);
        }
    }
}
