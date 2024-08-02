package BOJ.프린터큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());

            LinkedList<int[]> list = new LinkedList<>();
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int number = Integer.parseInt(st.nextToken());
                list.offer(new int[] {j, number});
            }

            int answer = 0;
            while(!list.isEmpty()){
                boolean isBiggest = true;
                int[] head = list.poll();
                int priority = head[1];

                for(int k=0; k<list.size(); k++){
                    if(priority < list.get(k)[1]){
                        list.offer(head);

                        for(int l=0; l<k; l++){
                            list.add(list.poll());
                        }

                        isBiggest = false;
                        break;
                    }
                }

                if(!isBiggest)continue;

                answer++;
                if(head[0] == m)break;
            }

            System.out.println(answer);
        }
    }
}
