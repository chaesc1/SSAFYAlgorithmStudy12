package week6.패션왕신해빈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9375 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> hashMap = new HashMap<>();
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());

                st.nextToken();
                String category = st.nextToken();
                hashMap.put(category, hashMap.getOrDefault(category, 0)+1);
            }

            int result = 1;
            for(int val : hashMap.values()){
                result *= (val+1);
            }
            System.out.println(result-1);
        }
    }
}