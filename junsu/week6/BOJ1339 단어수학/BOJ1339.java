//백준 단어 수학 골드4
import java.io.*;
import java.util.*;

public class BOJ1339 {
	static int N;
	static Integer [] arr = new Integer[26];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 배열을 0으로 초기화
		Arrays.fill(arr, 0);
//     System.out.println(Arrays.toString(arr));
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
//				System.out.println(c+" "+ (c-'A'));
				//배열 위치마다 알파벳 위치가 지정되고 해당하는 값들이 더해진다.
				arr[c-'A'] += (int)Math.pow(10, str.length()-1 - j);
			}
		}
		Arrays.sort(arr, Collections.reverseOrder());
//		System.out.println(Arrays.toString(arr));
		
		int ans = 0;
		int weight = 9;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 0)
				break;
			ans += weight * arr[i];
			weight--;
		}
		System.out.println(ans);
		
	}
}
