// 7465. 창용 마을 무리의 개수 D4
import java.io.*;
import java.util.*;
 
public class SWEA7465 {
    static int N, M, cnt;
    static ArrayList<Integer> [] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new ArrayList[N+1];
            visited = new boolean[N+1];
             
            for(int k = 1; k <= N; k++) {
                arr[k] = new ArrayList<>();
            }
             
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                arr[b].add(a);
            }
             
//          System.out.println(Arrays.deepToString(arr));
//          System.out.println(Arrays.toString(visited));
            cnt = 0;
            for(int i = 1; i <= N; i++) {
                if(!visited[i]) {
                    dfs(i);
                    cnt++;
                }
            }
             
            System.out.println("#"+test_case+" "+cnt);
        }
         
    }
    static void dfs(int idx) {
        if(visited[idx]) return;
        visited[idx] = true;
         
        for(int cur : arr[idx]) {
            if(!visited[cur]) {
                dfs(cur);
            }
        }
         
         
    }
}