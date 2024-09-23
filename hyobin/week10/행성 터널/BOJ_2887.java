import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static int[] parent;
    static int[][] position;
    static int[] dist;
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
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dist = new int[n+1];
        //좌표 저장
        position = new int[n+1][3];
        ArrayList<node> listx = new ArrayList<>();
        ArrayList<node> listy = new ArrayList<>();
        ArrayList<node> listz = new ArrayList<>();
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int tempx = Integer.parseInt(st.nextToken());
            int tempy  = Integer.parseInt(st.nextToken());
            int tempz  = Integer.parseInt(st.nextToken());
            listx.add(new node(i,tempx));
            listy.add(new node(i,tempy));
            listz.add(new node(i,tempz));
        }
        Collections.sort(listx);
        Collections.sort(listy);
        Collections.sort(listz);
//      for(int i=0; i<listx.size();i++) {
//    	System.out.println(listx.get(i).cost);
//    }
        ArrayList<Edge> list = new ArrayList<>();
        //좌표간 거리 계산
        for(int i=0; i<n-1; i++){
            int x = Math.abs(listx.get(i).cost-listx.get(i+1).cost);
            int y = Math.abs(listy.get(i).cost-listy.get(i+1).cost);
            int z = Math.abs(listz.get(i).cost-listz.get(i+1).cost);
            list.add(new Edge(listx.get(i).from,listx.get(i+1).from,x));
            list.add(new Edge(listy.get(i).from,listy.get(i+1).from,y));
            list.add(new Edge(listz.get(i).from,listz.get(i+1).from,z));

        }
        
        Collections.sort(list);
//        for(int i=0; i<list.size();i++) {
//        	System.out.println(list.get(i).cost);
//        }
        make();
        int cnt = 0;
        long result = 0;
        for(Edge edge: list){
            if(cnt == n-1) break; 
            if(union(edge.from, edge.to)){
                result += edge.cost;
                cnt++;
            }
        }
        System.out.println(result);

    }
    static class node implements Comparable<node>{
        int from;
        int cost;
        public node(int from,  int cost){
            this.from = from;
            this.cost = cost;
        }
        @Override
        public int compareTo(node o){
            return Integer.compare(this.cost, o.cost );
        }
    }
    static class Edge implements Comparable<Edge>{
        int from, to;
        int cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.cost, o.cost );
        }
    }
}
