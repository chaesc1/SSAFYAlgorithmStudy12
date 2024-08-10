package week4;

import java.io.*;
import java.io.InputStreamReader;
import java.util.*;
public class 나무높이 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int max = 0;
            int[] tree = new int[n];
            for(int i=0; i<n; i++) {
                int t = Integer.parseInt(st.nextToken());
                tree[i] = t;
                max = Math.max(max, t);
            }

            int even=0;
            int odd=0;
            for(int i=0; i<n; i++) {
                int abs = max-tree[i];

                if(abs == 0)continue;

                even += abs/2;
                odd += abs%2;
            }

            if(even>odd) {
                while(Math.abs(even-odd)>1) {
                    even--;
                    odd+=2;
                }
            }

            int result = 0;
            if(odd>even) {
                result = odd*2-1;
            }else if(even>odd) {
                result = even*2;
            }else {
                result = odd+even;
            }

            System.out.println("#" + tc + " " + result);
        }
    }

}
