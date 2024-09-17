package week9.군사이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11085 {
    static int[] parent;
    static int p, w, c, v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        parent = new int[p+1];
        for(int i=1; i<=p; i++){
            parent[i] = i;
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2[2]-o1[2]));
        for(int i=0; i<w; i++){
            priorityQueue.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        int result = 0;
        while(!priorityQueue.isEmpty()){
            int[] now = priorityQueue.poll();
            int start = now[0];
            int end = now[1];
            int width = now[2];

            union(start, end);

            if(find(c)==find(v)){
                result = width;
                break;
            }
        }

        System.out.println(result);
    }

    static int find(int x){
        if(x==parent[x])return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y)return;

        parent[y] = x;
    }
}
