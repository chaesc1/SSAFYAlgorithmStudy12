import java.io.*;
import java.util.*;

public class Main {
	static int[] height;
	static int total = 0;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		height = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		total = Arrays.stream(height).sum();
		if(total % 3 != 0) {
			System.out.println("NO");
		}
		else {
			int cnt1 = 0; // / 2
			int cnt2 = 0; // % 2
			
			for(int i = 0; i < N; i++) {
				cnt1 += height[i] / 2;
				cnt2 += height[i] % 2;
			}
			
			if((cnt1 - cnt2) % 3 == 0 && cnt1 >= cnt2) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}