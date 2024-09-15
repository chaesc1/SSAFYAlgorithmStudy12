//BOJ 17070 ������ �ű�� 1 ����5
//ó���� 30�� ���� ���������� ���̵� ���ø��� ���ߴ�. - ã�ƺ��� DP or DFS�� Ǫ�°ſ���
// DFS�� �����ϴ� ������ �����Ǹ� ��� �ؾ����� ������ ����
// ���⿡ ���� 0, 1, 2�� �����Ͽ� switch�� Ȱ���ϰ� �밢���� ��� ���⿡�� �� �� �־
// �������� ���� ���־���.
import java.io.*;
import java.util.*;

public class BOJ17070 {
	static int N;
	static int home[][];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		dfs(0, 1, 0);
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void dfs(int x, int y, int direction) {
//		System.out.println(x+" "+y+" "+direction);
		if(x == N-1 && y == N-1) {
			ans++;
			return;
		}
		
		//�� ���⿡ ���� ���ǹ��� ����
		switch(direction) {
		case 0: //������ ��� �� �� �ִ� ��� ����, �밢��
			if(y+1 < N && home[x][y+1] == 0) {
				dfs(x, y+1, 0);
			}
			break;
		case 1: //������ ��� �� �� �ִ� ��� ����, �밢��
			if(x+1 < N && home[x+1][y] == 0) {
				dfs(x+1, y, 1);
			}
			break;
		case 2: //�밢���� ��� �� �� �ִ� ��� ����, ����, �밢��
			if(y+1 < N && home[x][y+1] == 0) {
				dfs(x, y+1, 0);
			}
			if(x+1 < N && home[x+1][y] == 0) {
				dfs(x+1, y, 1);
			}
			break;
		}
		
		//����ġ�� ������ �밢���� �����̴� ��� ���̽��� ����� ���� �˻�����
		//�밢���� ��ΰ� �� �� ������ �������� ����
		if(x+1 < N && y+1 < N && home[x][y+1] == 0 && home[x+1][y] == 0 && home[x+1][y+1] == 0) { 
			dfs(x+1, y+1, 2);
		}
	}
}
