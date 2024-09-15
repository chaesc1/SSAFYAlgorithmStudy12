//BOJ 17471 게리맨더링 골드3
// 3시간 걸렸다.. 
//문제점 
//- ArrayList를 활용한 2차원 배열에 대해 사용이 미숙하다.
//- get() 함수 사용할때 자꾸 배열 쓸 때랑 헷갈려서 index 에러 이슈
//- 2개 선거구 area에 인구수 넣어주는 거를 위치 인덱스 넣어주기로 변경  @@@@@@이 부분을 잘못 생각했었다 인구수를 기준으로 저장하면 안됐는데..!
//조합 만들때 return 꼭 넣기.. 까먹는다 자꾸
//1~N까지 구역을 2개의 선거구로 나눠주기 (부분집합)
//나눠준 선거구 내에서 구역들이 전부 연결되었는지 확인 (그래프 탐색 - BFS)
//전부 연결되어 있으면 인구차 구하기
import java.io.*;
import java.util.*;

public class BOJ17471 {
	static int N;
	static int diffCnt;
	static int[] population;
	static ArrayList<Integer>[] graph;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    diffCnt = Integer.MAX_VALUE;
	    population = new int[N+1];
	    isSelected = new boolean[N+1];
	    
	    //그래프 초기화
	    graph = new ArrayList[N+1];
	    for(int i = 1; i <= N; i++) {
	    	graph[i] = new ArrayList<>();
	    }


	    //인구수 등록
	    st = new StringTokenizer(br.readLine());
	    for(int i = 1; i <= N; i++) {
	    	population[i] = Integer.parseInt(st.nextToken());
	    }
//	    System.out.println(Arrays.toString(population));
	    
	    //각 구역마다 인접한 구역 연결
	    for(int i = 1; i <= N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int vertex = Integer.parseInt(st.nextToken());
	    	for(int j = 1; j <= vertex; j++) {
	    		graph[i].add(Integer.parseInt(st.nextToken()));
	    	}
	    }
//	    System.out.println(Arrays.toString(graph));
	    
	    generateSubset(1);
	    
	    bw.write((diffCnt == Integer.MAX_VALUE ? -1 : diffCnt)+"\n");
	    bw.flush();
	    bw.close();
	    br.close();
	}
	
	//부분 집합 만드는 함수
	static void generateSubset(int depth) {
		if(depth == N+1) {
			ArrayList<Integer> areaA = new ArrayList<>();
			ArrayList<Integer> areaB = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				if(isSelected[i]) {
					areaA.add(i); //인구수 넣어주는 거에서 위치 인덱스 넣어주기로 변경
				} else {
					areaB.add(i); //인구수 넣어주는 거에서 위치 인덱스 넣어주기로 변경
				}
			}
			
			if(areaA.size() == 0 || areaB.size() == 0)
				return;
			
			if(check(areaA) && check(areaB)) {
				calc(areaA, areaB);
			}
			
			return;
		}
		
		// true는 A구역에 넣겠다는 뜻
		isSelected[depth] = true;
		generateSubset(depth+1);
		// false는 B구역에 넣겠다는 뜻
		isSelected[depth] = false;
		generateSubset(depth+1);
		
	}
	
	static boolean check(ArrayList<Integer> area) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
        visited[area.get(0)] = true;  // 1~N 번까지 배열에 담아서 쓰던게 익숙해서 area.get(1)해서 index 에러 났다..
        q.add(area.get(0));
		
		int count = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			 
			//선거구에 해당하고, 아직 미방문
			for(int next : graph[cur]) {
				// area에도 idx가 들어있고 graph[cur] 에 담긴 값도 idx라서 contains로 검사한다.
				if(area.contains(next) && !visited[next]) {
					visited[next] = true;
					q.add(next); //이거 넣는거 깜빡함;;;
					count++;
				}
			
			}

		}
		return count == area.size();
	}
	
	//두 구역의 차이를 검사하는 함수
	static void calc(ArrayList<Integer> areaA, ArrayList<Integer> areaB) {
		int A = 0;
		int B = 0;
		
		// ArrayList에 N개 만큼 없는데 N번 만큼 돌아보게해서 틀림...
//		for(int i = 1; i <= N; i++) {
		// area에 담긴게 idx니까 이제 인구수를 idx를 활용해서 구하면 된다.
		for(int i : areaA) {
			A += population[i];
		}
		
		for(int i : areaB) {
			B += population[i];
		}
		
		diffCnt = Math.min(diffCnt, Math.abs(A-B));
	}
}








