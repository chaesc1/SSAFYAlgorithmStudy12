import java.io.*;
import java.util.*;

public class Main {

	// 퀸 이동 가능 방향 : 같은 행, 같은 열, 대각선 방향
	// 같은 행에 퀸을 두지 않는 방식 -> 이차원 배열으로 관리할 필요 없음
	static int N;
	static int col[];
	static int diag1[], diag2[]; 
	static int answer;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N+1]; // 1행부터 사용
		diag1 = new int[2 * N];
		diag2 = new int[2 * N + 1];
		setQueens(1);
		
		System.out.println(answer);
		
	}
	
	static void setQueens(int rowNo) {
		
		if(rowNo > N) {
			answer++;
			return; // 무조건 답 (끝까지 유망했던 것)
		}
		
		for(int c = 1; c <= N; c++) {// 모든 열 점검
			
			if(!isAvailable(rowNo, c)) continue; // 가지치기 : 현재의 노드가 유망한가
			
			col[c] = rowNo;
			diag1[rowNo - c + N] = 1;
			diag2[rowNo + c] = 1;
			
			setQueens(rowNo + 1);
			
			col[c] = 0;
			diag1[rowNo - c + N] = 0;
			diag2[rowNo + c] = 0;
		}
	}
	
	static boolean isAvailable(int rowNo, int colNo) {
		//현재 주어진 노드가 이전의 노드와 위협성이 없는지 확인
		if(col[colNo] != 0) {
			return false;
		}
		if(diag1[rowNo - colNo + N] == 1 || diag2[rowNo + colNo] == 1)
			return false;
		
		
		return true;
	}
}