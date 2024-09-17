package week9.적의적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12893 {
    static int N, M;
    static int[] enemy;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }
        enemy = new int[N+1];

        int answer = 1;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int rootX = find(x);
            int rootY = find(y);

            if(rootX==rootY){
                answer = 0;
                break;
            }

            int enemyX = enemy[x];
            int enemyY = enemy[y];

            if(enemyX != 0){
                union(enemyX, rootY);
            }else{
                enemy[rootX] = rootY;
            }

            if(enemyY != 0){
                union(enemyY, rootX);
            }else{
                enemy[rootY] = rootX;
            }
        }

        System.out.println(answer);
    }

    static int find(int x){
        if(parent[x]==x)return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y)return;

        parent[y] = x;
    }
}
