//BOJ20444 색종이와 가위, 골드5
//이분탐색 문제
//원래 이분탐색 문제는 배열에 값 저장하고 정렬한 값들을 활용해서 탐색했는데
//이 문제는 배열에 값을 담을게 없어서 당황스러웠다.
//처음에 보고 어떻게 풀어야할지 감을 1도 못잡음;;
//int로 N, K를 받으니 런타임 에러(NumberFormat)발생 -> Long으로 변경

import java.io.*;
import java.util.*;

public class BOJ20444 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		//기준은 자르는 횟수이다. 
		//최솟값은 0, 최댓값은 N/2이다.
		//가로로 0번 자름 -> 세로로 N번 자름
		//가로로 N번 자름 -> 세로로 0번 자름
		//가로로 N/2 자름 -> 세로로 N/2번 자름
		//즉, 가로와 세로는 대칭을 이루므로 N/2까지만 탐색하면 된다.
		long start = 0;
		long end = N/2;  // 가로와 세로는 대칭을 이루므로 N/2까지만 탐색하면 된다.
		
		while(start <= end){
			long row = (start + end) / 2; //가로 자르기 횟수
			long col = N - row;  //세로 자르기 횟수
			long num = (row + 1) * (col + 1);
			
			if(num == K) {
				System.out.println("YES");
				return;
			}else if(num < K) { //더 잘라야함, 자르는 횟수 증가
				start = row + 1;  //실수한 부분 -> start++ 해버림 이러면 이분탐색아니고 투 포인터 된다.
			}else { //색종이 조각 너무 많은 경우, 자르는 횟수 감소
				end = row - 1; //실수한 부분 -> end-- 해버림 이러면 이분탐색아니고 투 포인터 된다.
			}
		}
		System.out.println("NO");
	}
}
