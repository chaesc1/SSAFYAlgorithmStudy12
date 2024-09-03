//백준 구간 합 구하기4 실버3
import java.io.*;
import java.util.*;

public class BOJ11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int []input = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken()) + input[i-1];
		}
//		System.out.println(Arrays.toString(input));
		
		for(int i = 0; i < M; i++) {
			int total = 0;
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); 
			int end = Integer.parseInt(st.nextToken()); 
			total = input[end] - input[start-1];
			
//			System.out.println(total);
			sb.append(total+"\n");
			total = 0;
		}
		
		System.out.println(sb);
	
	}
}
