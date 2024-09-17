package week7.청룡마을무리의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465 {
    static int[] parent;
    static int n, m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            set();

            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                union(start, end);
            }

            int sum = 0;
            for(int i=1; i<=n; i++){
                if(parent[i]==i)sum++;
            }

            System.out.println("#"+(tc+1)+" "+sum);
        }
    }

    static void set(){
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
    }

    static int find(int a){
        if(parent[a] == a)return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a<b)parent[b] = a;
        else parent[a] = b;
    }
}
