import java.util.Arrays;
import java.util.Scanner;

public class Main {

	//배열에 입력 받음
	//해당 배열을 오름차순으로 정렬
	//해당 배열을 순회 -> 숫자 배열, 개수 배열 같은 크기로
		//앞의 수와 다른 숫자면, 
			//전체 카운트
			//현재 해당 숫자 개수를 개수 배열에 저장
			//하나씩 다른 배열로 옮김, 
		//같은 숫자면 
			//옮기지 않고 해당 숫자만 카운트
	//숫자 배열을 가지고 순열 제작
	//개수 배열 참고해서 남아있는 숫자가 없으면 가지치기
	//순열 완성 시 출력
	
	
	static int n, m;
	static int input[];
	static int num[];
	static int cnt[];
	static int output[];
	static int total;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		input = new int[n];
		num = new int[n];
		cnt = new int[n];
		output = new int[m];
		
		for(int i = 0; i < n; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		
		total = 1;
		cnt[0] = 1;
		num[0] = input[0];
		int idx = 0;
		for(int i = 1; i < n; i++) {
			if(input[i] == num[idx]) {
				cnt[idx]++;
			}
			else {
				total++;
				idx++;
				num[idx] = input[i]; 
				cnt[idx] = 1;
			}	
		}
		
		permu(0);
		
		System.out.print(sb);
	}
	
	
	static void permu(int order) {
		
		if(order == m) {
			for(int i = 0; i < m; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 0; i < total; i++) {
			if(cnt[i] > 0) {
				cnt[i]--;
				output[order] = num[i];
				permu(order + 1);
				cnt[i]++;
			}
		}
	}
}