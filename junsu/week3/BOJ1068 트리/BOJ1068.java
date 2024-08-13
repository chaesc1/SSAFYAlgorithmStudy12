// 백준 1068번 트리 골드5
import java.io.*;
import java.util.*;

public class BOJ1068 {
	static ArrayList<Integer>[] tree;
	static boolean visited[];
	static int delete;
	static int ans;
	
	public static void main(String[] args) throws IOException {
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	       StringTokenizer st;
	       //노드 수
	       int n = Integer.parseInt(br.readLine());
	       //트리 노드들
	       tree = new ArrayList[n+1];
	       visited = new boolean[n+1];
	       
	       for(int i = 0; i < n; i++) {
	    	   tree[i] = new ArrayList<>();
	       }
	       int root = -1;
	       st = new StringTokenizer(br.readLine());
	       for(int i = 0; i < n; i++) {
	    	   int p = Integer.parseInt(st.nextToken());
	    	   if(p == -1) {
	    		   // i번째 노드가 루트 노드이다.
	    		   root = i;
	    	   }else {
	    		   // p가 i번째 노드의 부모임
	    		   tree[i].add(p);
	    		   tree[p].add(i);
	    	   }
	       }
//	       for(int i = 0; i<n; i++)	{
//	    	   System.out.println(tree[i]);
//	       }
	       delete = Integer.parseInt(br.readLine());
	       //지우는 노드가 루트 노드일 경우
	       if(delete == root) {
	    	   System.out.println(0);
	    	   return;
	       }else {
	    	   dfs(root);
	       }
	       System.out.println(ans);
	}
	static void dfs(int v) {
		visited[v] = true;
		int nodes = 0;
		for(int cur : tree[v]) {
			//연결 노드 탐색
			if(cur != delete && !visited[cur]) {
				nodes++;
				dfs(cur);
			}
		}
		if(nodes == 0) {
			ans++;
//			System.out.println(ans);
		}
	}
	
}
