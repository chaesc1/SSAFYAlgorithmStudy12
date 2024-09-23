//BOJ14499 �ֻ��� ������, ���4
//���̵�� 
//- �ֻ��� ���� ��ġ���� 1���� �迭�� ��ģ��.
//- ��Ÿ Ž�� ������ �ƴϾ���. �׳� idx�� Ȱ���� �迭 �ű�⿴��
//- �� idx ���� ��ġ�� �����صΰ� 1���� �ֻ��� �迭 �� ��ġ�� �����Ѵ�.
import java.io.*;
import java.util.*;

public class BOJ14499 {
	static int[] dice;
	static int[][] map;
	static int N, M, x, y, K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dice = new int[6+1];
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int direction = Integer.parseInt(st.nextToken());
			int ans = tumble(direction);
			if(ans == -1) //�ٱ����� �̵��ϴ� ��� ��� ���÷� ������� �ʴ´�.
				continue;
			bw.write(ans+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	static int tumble(int direction) {
		int tmp;
		switch(direction) {
		case 1: //����
			y += 1; //�������� �̵��̶� ��ǥ�� ����
			if(y >= M) {
				//�ٱ����� �̵��ϴ� ��� ��� ���÷� ������� �ʴ´�.
				y -= 1;
				return -1; 
			}
			tmp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
			break;
		case 2: //����
			y -= 1; // ��ǥ�� �̵�
			if(y < 0) {
				y += 1;
				return -1;
			}
			tmp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
			break;
		case 3: //����
			x -= 1; // ��ǥ�� �̵�
			if(x < 0) {
				x += 1;
				return -1;
			}
			tmp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
			break;
		case 4: //����
			x += 1; // ��ǥ�� �̵�
			if(x >= N) {
				x -= 1;
				return -1;
			}
			tmp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
			break;
		}
		
		if(map[x][y] == 0) {
			//�̵��� ĭ�� ���� �ִ� ���� 0�̸�, �ֻ����� �ٴڸ鿡 ���� �ִ� ���� ĭ�� ����ȴ�. 
			map[x][y] = dice[1];
		} else {
			//0�� �ƴ� ��쿡�� ĭ�� ���� �ִ� ���� �ֻ����� �ٴڸ����� ����Ǹ�, ĭ�� ���� �ִ� ���� 0�� �ȴ�.
			dice[1] = map[x][y];
			map[x][y] = 0;
		}
		
		return dice[6];
	}
	
}
