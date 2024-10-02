//BOJ14891 톱니바퀴, 골드5
//빡구현 문제, 집중 흐름이 중간마다 끊기면서 푸니까 오래걸렸다 3~4시간
//혼자 집중하면서 풀었으면 1~2시간 안에 풀었을듯
import java.io.*;
import java.util.*;

public class BOJ14891 {
	static int[][] cogwheel = new int[4+1][8];
	static int[] Top = new int[4+1];
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//톱니바퀴 값 받기
		for(int i = 1; i <= 4; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 0; j < 8; j++) {
				char ch = temp.charAt(j);
				String Str = Character.toString(ch);
				cogwheel[i][j] = Integer.parseInt(Str);
			}
		}
		
//		for(int i = 0; i < 4; i++) {
//			for(int j = 0; j < 8; j++) {
//				System.out.print(cogwheel[i][j]);
//			}
//			System.out.println();
//		}

		
		K = Integer.parseInt(br.readLine());
		
		int selectGear;
		int dirction;
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			selectGear = Integer.parseInt(st.nextToken());
			dirction = Integer.parseInt(st.nextToken());
			func(selectGear, dirction);
		}
		
//		System.out.println("점수");
//		System.out.print(cogwheel[1][Top[1]] + " ");
//		System.out.print(cogwheel[2][Top[2]] + " ");
//		System.out.print(cogwheel[3][Top[3]] + " ");
//		System.out.print(cogwheel[4][Top[4]] + " ");
//		System.out.println();
		//점수 계산
		int ans = 0;
		if(cogwheel[1][Top[1]] == 1) {
			ans += 1;
		}
		if(cogwheel[2][Top[2]] == 1) {
			ans += 2;
		}
		if(cogwheel[3][Top[3]] == 1) {
			ans += 4;
		}
		if(cogwheel[4][Top[4]] == 1) {
			ans += 8;
		}
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void func(int selectGear, int dirction) {
		//양 톱니바퀴로 퍼져야함
		
		int back = selectGear-1;
		int front = selectGear+1;
		if(back > 0) {
			//왼쪽 번호 바퀴랑 극을 비교하고 다르면 작동
			int backNum = (Top[back]+2) % 8;
			int curNum = (Top[selectGear]+6) % 8;
//			System.out.println("왼쪽꺼"+backNum + " "+ curNum);
//			System.out.println(back);
//			for(int z = 0; z < 8; z++) {
//				System.out.print(cogwheel[back][z] + " ");
//			}
//			System.out.println(cogwheel[back][backNum] + " " + cogwheel[selectGear][curNum]);
			if(cogwheel[back][backNum] != cogwheel[selectGear][curNum]) {
//				System.out.println("왼쪽 실행");
				int dic;
				if(dirction == 1) {
					dic = -1;
				}else {
					dic = 1;
				}
				left(back, dic);
			}
		}
		if(front < 5) {
			int curNum = (Top[selectGear]+2) % 8;
			int frontNum = (Top[front]+6) % 8;
//			System.out.println("오른쪽꺼" + curNum + " " + frontNum);
			if(cogwheel[selectGear][curNum] != cogwheel[front][frontNum]) {
//				System.out.println("오른쪽 실행");
				int dic;
				if(dirction == 1) {
					dic = -1;
				}else {
					dic = 1;
				}
				right(front, dic);
			}
		}
		
		if(selectGear == 1) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 2) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 3) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 4) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}
	}
	
	static void left(int selectGear, int dirction) {
		//양 톱니바퀴로 퍼져야함
		
		int back = selectGear-1;
		if(back > 0) {
			//왼쪽 번호 바퀴랑 극을 비교하고 다르면 작동
			int backNum = (Top[back]+2) % 8;
			int curNum = (Top[selectGear]+6) % 8;
			if(cogwheel[back][backNum] != cogwheel[selectGear][curNum]) {
				int dic;
				if(dirction == 1) {
					dic = -1;
				}else {
					dic = 1;
				}
				left(back, dic);
			}
		}
		
		
		if(selectGear == 1) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 2) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 3) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 4) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}
	}
	
	
	static void right(int selectGear, int dirction) {
		//양 톱니바퀴로 퍼져야함
		int front = selectGear+1;
		if(front < 5) {
			int curNum = (Top[selectGear]+2) % 8;
			int frontNum = (Top[front]+6) % 8;
			if(cogwheel[selectGear][curNum] != cogwheel[front][frontNum]) {
				int dic;
				if(dirction == 1) {
					dic = -1;
				}else {
					dic = 1;
				}
				right(front, dic);
			}
		}
		
		if(selectGear == 1) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 2) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 3) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}else if(selectGear == 4) {
			Top[selectGear] -= dirction;
			if(Top[selectGear] == -1) Top[selectGear] = 7; //톱니바퀴 범위 설정
			if(Top[selectGear] == 8) Top[selectGear] = 0;
		}
	}
}




