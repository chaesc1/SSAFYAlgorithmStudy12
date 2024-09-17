package week9.친구비;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16562 {
    static int numOfRelation;
    static int N, M, K;
    static int[] parent;
    static int[] rank;
    static int[] friends;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        rank = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
            rank[i] = 1;
        }

        friends = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int cost = Integer.parseInt(st.nextToken());
            friends[i] = cost;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }

        numOfRelation = 0;
        int cost = getCost();
        if(numOfRelation!=N){
            System.out.println("Oh no");
        }else{
            System.out.println(cost);
        }
    }

    static void updateRelation(int num){
        for(int i=1; i<=N; i++){
            if(parent[i]==num)numOfRelation++;
        }
    }

    static int getCost(){
        int cost = 0;
        boolean[] visited = new boolean[N+1];
        for(int i=1; i<=N; i++){
            if(visited[parent[i]])continue;
            if(cost+friends[parent[i]]>K)break;
            cost += friends[parent[i]];
            visited[parent[i]] = true;
            updateRelation(parent[i]);
        }
        return cost;
    }

    static int find(int x){
        if(parent[x]==x)return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y)return;

        if(friends[x] <= friends[y]){
            parent[y] = x;

            for(int i=1; i<=N; i++){
                if(parent[i]==y){
                    parent[i]=x;
                }
            }
        }else{
            parent[x] = y;
            for(int i=1; i<=N; i++){
                if(parent[i]==x){
                    parent[i]=y;
                }
            }
        }
    }
}
