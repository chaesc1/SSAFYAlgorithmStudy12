// ���� 16918�� ������ �ǹ�1
import java.io.*;
import java.util.*;

//���� Ǯ��
//1. �Է°��� �����Ѵ�.
//2. 2~n��ŭ �ݺ����� ������.(1�� �ƹ��͵� ���� �ʴ´ٰ� ������ ���������Ƿ� ����)
//n�� Ȧ���� ��
//- ��ź ��ġ�� ť�� �����Ѵ�.
//- �׷����� ��� O���� ä���.
//- bfs�� �����Ѵ�. -> ť���� ���� ��ġ + �����¿� ���⿡ �ִ� 'O'�� '.'���� �ٲ۴�.
//3. n�� ¦�����, O���� ä�� �׷����� ����ϰ� Ȧ����� ���� ������ ��ģ �׷����� ����Ѵ�.

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
	    		// ��ź ��ġ ����
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




