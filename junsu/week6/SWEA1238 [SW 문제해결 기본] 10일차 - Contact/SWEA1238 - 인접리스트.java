// SWEA [S/W 문제해결 기본] 10일차 - Contact, D4
// 인접리스트
import java.io.*;
import java.util.*;

public class SWEA1238 {
	static ArrayList<Integer>[] graph;
	static int dataL, startP;
	static boolean[] visited;
	static int[] input;
	static int max;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			dataL = Integer.parseInt(st.nextToken());
			startP = Integer.parseInt(st.nextToken());
//			graph = new ArrayList<Integer>();
			graph = new ArrayList[100+1];
			visited = new boolean[100+1];
			input = new int[100+1];
			
			for(int i = 0; i < 100+1; i++) {
				graph [i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < dataL/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
//				System.out.println(from + " " + to);
				graph[from].add(to);
			}
			
			for(int i = 0; i < 100+1; i++) {
				if(graph[i].isEmpty()) {
					continue;
				}
//				System.out.println(i + ": " + graph[i]);	
			}
//			System.out.println();
			max = 0;
			res = 0;
			
			bfs();
//			System.out.println(Arrays.toString(input));
			for(int i = 0; i < 100+1; i++) {
				if(input[i] == max) {
					res = i;
				}
			}
			System.out.println("#"+test_case+" "+res);
//			System.out.println("#"+test_case);
		}
		
		
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
	    int[] levels = new int[101]; // 그래프의 크기에 맞게 배열 크기 조정
	    Arrays.fill(levels, -1); // 모든 레벨을 -1로 초기화, 방문하지 않은 노드를 나타냄
		
		queue.offer(startP);
		visited[startP] = true;
		levels[startP] = 0; // 시작 노드는 레벨 0
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			int currentLevel = levels[node];
			
            for(int neighbor : graph[node]) {
                if(!visited[neighbor]) { // 방문하지 않은 경우
                    visited[neighbor] = true;
                    levels[neighbor] = currentLevel + 1;
//                    System.out.println("level : " + levels[neighbor] + "--- " + node + " -> " + neighbor);
                    queue.offer(neighbor);
                    
                    input[neighbor] = levels[neighbor];
                    if(max < levels[neighbor]) {
                    	max = levels[neighbor];
                    }
  
                    //제일 늦게 도착하는 사람 찾기
//                    if(max < levels[neighbor]) {
//                    	return;
//                    }
                }
            }
		
			
		}
		
	}
	
}


