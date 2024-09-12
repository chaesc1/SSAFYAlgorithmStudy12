import java.io.*;
import java.util.*;

public class Main {

	static int n, m, r;
	static int map[][], buffer[][]; //기존 배열, 정답 저장 버퍼

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		//입력
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();
		map = new int[100][100];
		buffer = new int[100][100];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//회전하기
		for (int i = 0; i < r; i++) {
			rotate(sc.nextInt());
		}
		
		//정답 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}

	static void rotate(int type) {
		
		//초기화
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				buffer[i][j] = 0;
			}
		}
		
		
		if (type == 1) {
			//상하반전
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					buffer[n-1-i][j] = map[i][j];
				}
			}
			
		} else if (type == 2) {
			//좌우 반전
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					buffer[i][m-1-j] = map[i][j];
				}
			}
		} else if (type == 3) {
			//오른쪽 회전
			int tn = m;
			int tm = n;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					buffer[j][tm-1-i] = map[i][j];
				}
			}
			n = tn;
			m = tm;
			
			
		} else if (type == 4) {
			//왼쪽 회전
			int tn = m;
			int tm = n;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					buffer[tn-1-j][i] = map[i][j];
				}
			}
			n = tn;
			m = tm;
		} else if (type == 5) {
			//시계 방향
			//1구역
			for(int i = 0; i < n/2; i++) {
				for(int j = 0; j < m/2; j++) {
					buffer[i][j + m/2] = map[i][j];
				}
			}
			//2구역
			for(int i = 0; i < n/2; i++) {
				for(int j = m/2; j < m; j++) {
					buffer[i + n/2][j] = map[i][j];
				}
			}
			//3구역
			for(int i = n/2; i < n; i++) {
				for(int j = m/2; j < m; j++) {
					buffer[i][j - m/2] = map[i][j];
				}
			}
			//4구역
			for(int i = n/2; i < n; i++) {
				for(int j = 0; j < m/2; j++) {
					buffer[i - n/2][j] = map[i][j];
				}
			}
		} else if (type == 6) {
			//반시계 방향
			//1구역
			for(int i = 0; i < n/2; i++) {
				for(int j = 0; j < m/2; j++) {
					buffer[i + n/2][j] = map[i][j];
				}
			}
			//2구역
			for(int i = 0; i < n/2; i++) {
				for(int j = m/2; j < m; j++) {
					buffer[i][j - m/2] = map[i][j];
				}
			}
			//3구역
			for(int i = n/2; i < n; i++) {
				for(int j = m/2; j < m; j++) {
					buffer[i - n/2][j] = map[i][j];
				}
			}
			//4구역
			for(int i = n/2; i < n; i++) {
				for(int j = 0; j < m/2; j++) {
					buffer[i][j + m/2] = map[i][j];
				}
			}
		}
		//옮기기
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				map[i][j] = buffer[i][j];
			}
		}
	}
}