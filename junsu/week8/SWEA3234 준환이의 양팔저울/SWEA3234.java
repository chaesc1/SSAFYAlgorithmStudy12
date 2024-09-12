//3234 . 준환이의 양팔저울
//쉬운줄 알았으나 생각보다 어떻게 구현해야할지 스스로 떠올리지 못한 문제
//주변 친구들의 아이디어를 얻고 강의를 들어서 풀었지만 테케 21/20으로 틀림
//원래는 dfs 순열 함수 안에서 한번에 처리하려 했으나 함수를 2개로 나눠서 방식 변경
import java.io.*;
import java.util.*;

public class SWEA3234 {
	static int N, result;
	static int[] input;
	static boolean[] visited;
	static int[] select;
	
 public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringTokenizer st;
     StringBuilder sb = new StringBuilder();

     int T = Integer.parseInt(br.readLine());
     for(int tc = 1; tc <= T; tc++) {
     	N = Integer.parseInt(br.readLine());
     	int right = 0;
     	int left = 0;
     	result = 0;
     	input = new int[N];
     	visited = new boolean[N];
     	select = new int[N];
     	st = new StringTokenizer(br.readLine());
     	for(int i = 0; i < N; i++) {
     		input[i] = Integer.parseInt(st.nextToken());
     	}
     	
//     	System.out.println(Arrays.toString(input));
     	dfs(0);
//     	System.out.println("#"+tc+" " +result);
     	sb.append("#").append(tc).append(" ").append(result+"\n");
     }
     System.out.println(sb);
     
 }
 static void dfs(int cnt) {
 	if(cnt == N) {
 		backtrack(select, 0, 0, 0);
 		return;
 	}
 	
 	for(int i = 0; i < N; i++) {
 		if(!visited[i]) {
 			visited[i] = true;
     		select[cnt] = input[i];
     		dfs(cnt+1);
     		visited[i] = false;
 		}
 	}
 	
 }
 static void backtrack(int[] select, int depth, int left, int right) {
// 	System.out.println(depth + " " + left+" "+ right);
 	if(left < right) {
 		return;
 	}
 	if(depth == N) {
 		result++;
 		return;
 	}
 	backtrack(select, depth+1, left+select[depth], right);
 	backtrack(select, depth+1, left, right+select[depth]);
 	
 }
}




