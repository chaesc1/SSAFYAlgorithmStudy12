// 백준 1748번 수 이어 쓰기1
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		int pow = 10;
		int plus = 1;
		
		for(int i = 1; i <= N; i++) {
			if(i < pow) {
				result += plus;
			}else {
				pow *= 10;
				plus++;
				result += plus;
			}
			
		}
		
		System.out.println(result);
		
	}
}


