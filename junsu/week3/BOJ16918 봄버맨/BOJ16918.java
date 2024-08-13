// 백준 16918번 봄버맨 실버1
import java.io.*;
import java.util.*;

//문제 풀이
//1. 입력값을 저장한다.
//2. 2~n만큼 반복문을 돌린다.(1은 아무것도 하지 않는다고 문제에 나와있으므로 제외)
//n이 홀수일 때
//- 폭탄 위치를 큐에 저장한다.
//- 그래프를 모두 O으로 채운다.
//- bfs를 수행한다. -> 큐에서 나온 위치 + 상하좌우 방향에 있는 'O'을 '.'으로 바꾼다.
//3. n이 짝수라면, O으로 채운 그래프를 출력하고 홀수라면 위의 과정을 거친 그래프를 출력한다.

public class BOJ16918 {
	static int r, c, n;
	static char[][] map;
	static Queue<int[]>q = new LinkedList<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    n = Integer.parseInt(st.nextToken());
	    
	    map = new char[r][c];
	    for(int i =0; i<r; i++) {
	    	String s = br.readLine();
	    	for(int j = 0; j < c; j++) {
	    		map[i][j] = s.charAt(j);
	    	}
	    }
	    
	    for(int i = 2; i <= n; i++) {
	    	if(i%2 == 1) {
	    		// 폭탄 위치 저장
	    		for(int k = 0; k < r; k++) {
	    			for(int j = 0; j < c; j++) {
	    				if(map[k][j] == 'O') {
	    					q.add(new int[] {k, j});
	    				}
	    			}
	    		}
	    		for(char[] m : map) {
	    			Arrays.fill(m, 'O');
	    		}
	    		bfs();
	    	}
	    }
	    
	    if(n % 2 == 0) {
	    	for(char[] m : map) {
	    		Arrays.fill(m, 'O');
	    	}
//	    	System.out.println();
	    }
	    for(int i = 0; i < r; i++) {
	    	for(int j = 0; j < c; j++) {
	    		System.out.print(map[i][j]);
	    	}
	    	System.out.println();
	    }
	}
    static void bfs(){
    	while(!q.isEmpty()) {
    		int [] t =  q.poll();
    		int x = t[0];
    		int y = t[1];
    		map[x][y] = '.';
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			if(nx >= 0 && nx < r && ny >= 0 && ny < c) {
    				if(map[nx][ny] == 'O') {
    					map[nx][ny] = '.';
    				}
    			}
    		}
    	}
    }
}




