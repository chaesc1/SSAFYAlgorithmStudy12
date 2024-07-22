// 백준 2748번 피보나치 수2
import java.io.*;
import java.util.*;

public class BOJ2748 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		long [] list = new long[n+1];

		
		list[0] = 0;
		list[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			list[i] = list[i-2] + list[i-1];
		}
		System.out.println(list[n]);
		
		
	}
}


