import java.io.*;
import java.util.*;

public class Solution {
	//10개의 테스트 케이스
	//인덱스 주의
	//데이터 길이, 시작점
	//데이터(from, to)
	//최대 연락인원 100명 -> 100x100 인접행렬 생성
	//데이터 받으면서 데이터 길이 /2 반복 : from, to -> 행렬 쌍으로 인접행렬에 기록
	//방문체크
	//시작점부터 bfs 실행 (파라메터 : 현재 번호)
		//큐에 시작점 번호 삽입
		//시작점의 행을 살펴보면서 1인 경우 큐에 삽입
		//각 bfs 단계마다 최댓값 기록
	//bfs 끝나면 기록한 최댓값 출력
	
	static int data;
	static int start;
	static boolean map[][];
	static boolean visit[];
	static int answer;
	
	static class Node{
		int num;
		int len;
		public Node(int num, int len) {
			super();
			this.num = num;
			this.len = len;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		map = new boolean[101][101];
		visit = new boolean[101];
		
		while(tc <= 10) {
			sb.append("#").append(tc).append(" ");
			
			data = sc.nextInt();
			start = sc.nextInt();
			
			for(int i = 0; i < data/2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				map[from][to] = true;
			}
			
			answer = -1;
			bfs(start);
			sb.append(answer).append('\n');
			
			
			//초기화
			for(int i = 0; i < 101; i++) {
				Arrays.fill(map[i], false);
				visit[i] = false;
			}
			
			
			tc++;
		}
		System.out.print(sb);

	}
	
	static void bfs(int start) {
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(start, 0));
		visit[start] = true;
		int maxlen = 0;
	
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			//최댓값 기록
			if(maxlen < cur.len) {
				answer = -1;
				maxlen = cur.len;
			}
			if(cur.len == maxlen) {
				answer = Math.max(answer, cur.num);
			}
			
			//추가
			for(int i = 1; i <= 100; i++) {
				if(map[cur.num][i] && !visit[i]) {
					q.add(new Node(i, cur.len + 1));
					visit[i] = true;
				}
			}
		}
	}
	
}