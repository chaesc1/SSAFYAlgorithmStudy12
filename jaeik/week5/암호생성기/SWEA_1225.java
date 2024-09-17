package week5.암호생성기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        for(int tc=0; tc<10; tc++){
            br.readLine();
            st = new StringTokenizer(br.readLine());

            Deque<Integer> dq = new ArrayDeque<>();
            for(int i=0; i<8; i++){
                dq.add(Integer.parseInt(st.nextToken()));
            }

            int minus = 1;
            int n=0;
            while(true){
                n = (minus%5==0)?5:minus%5;
                int num = dq.pollFirst()-(n);
                minus++;

                if(num<=0){
                    dq.add(0);
                    break;
                }

                dq.add(num);
            }

            List<Integer> list = new ArrayList<>(dq);
            System.out.print("#"+(tc+1)+" ");
            for(int i=0; i<list.size(); i++){
                System.out.print(list.get(i)+" ");
            }
            System.out.println();
        }
    }
}
