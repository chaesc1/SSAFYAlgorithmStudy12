import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static int[] parent;
    static int[][] position;
    static double[][] dist;
    static void make(){
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return false;
        parent[y] = x;
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new double[n+1][n+1];
        //좌표 저장
        position = new int[n+1][2];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            position[i][0] = Integer.parseInt(st.nextToken());
            position[i][1] = Integer.parseInt(st.nextToken());
        }
        //각 좌표간 거리 계산
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j) continue;
                double x = Math.pow(position[i][0] - position[j][0], 2);
                double y = Math.pow(position[i][1] - position[j][1], 2);
                dist[i][j] = (Math.sqrt(x+y));

            }
        }
        ArrayList<Edge> list = new ArrayList<>();
        //연결된 좌표 -> 가중치 0으로 하여 가장 먼저 연결
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Edge(a,b,0));
        }
        //가까운 순으로 연결
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                list.add(new Edge(i,j, dist[i][j]));
            }
        }
        Collections.sort(list);
        make();
        int cnt = 0;
        double result = 0;
        for(Edge edge: list){
            if(cnt == n-1) break;
            if(union(edge.from, edge.to)){
                result += edge.cost;
                cnt++;
            }
        }
        System.out.println(String.format("%.2f", result));

    }
    static class Edge implements Comparable<Edge>{
        int from, to;
        double cost;
        public Edge(int from, int to, double cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){
            return Double.compare(this.cost, o.cost);
        }
    }
}
