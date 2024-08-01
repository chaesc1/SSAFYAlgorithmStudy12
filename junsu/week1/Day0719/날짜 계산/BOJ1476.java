// 백준 1476번 날짜 계산 실버5
import java.io.*;
import java.util.*;
 
public class BOJ1476 {
	public static void main(String[] args) throws IOException {		
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int E = 0;
		int S = 0;
		int M = 0;
		
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		System.out.println(E+" "+S+" "+M);
		
		int cnt = 0;
		while(true) {
			cnt++;
			
			if(E==1 && S==1 && M==1) {
				break;
			}
			
			
			
			if(E > 1) {
				E--;
			}else {
				E = 15;
			}
			
			if(S > 1) {
				S--;
			}else {
				S = 28;
			}
			
			if(M > 1) {
				M--;
			}else {
				M = 19;
			}
			
		}
		System.out.println(cnt);
		
		
		
	}
}