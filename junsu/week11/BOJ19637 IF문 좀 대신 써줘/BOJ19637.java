//BOJ19637 IF문 좀 대신 써줘, 실버3
//hashmap or class 만들어서 ArrayList에 넣는 자료구조를 사용하면 되지 않을까라는 생각이 
//제일 처음 들었음 - 이렇게 하니 for문을 2중으로 써서 그런지 시간초과뜸
//이분 탐색으로 시도
//문제를 이분 탐색과 연결해서 생각하는 힘이 아직 부족하다.
import java.io.*;
import java.util.*;

public class BOJ19637 {
	static int N, M;
	static String[] title;
	static int[] power;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		title = new String[N];
		power = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			title[i] = st.nextToken();
			power[i] = Integer.parseInt(st.nextToken());
		}
		
		//이게 실패 코드 - 일반적인 for문으로 탐색하는 방법인데 시간초과 뜸
//		for(int i = 0; i < M; i++) {
//			int num = Integer.parseInt(br.readLine());
//			for(int j = 0; j < N; j++) {
//				if(num <= power[j]) {
////					System.out.println(title[j] + " " + num + " " + power[j]);
//					bw.write(title[j]+"\n");
//					break;
//				}
//			}
//		}
		
		//이분 탐색 코드
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(br.readLine());
			
			int start = 0;
			int end = N-1;
			
			while(start <= end) {
				int mid = (start+end) / 2;
				if(power[mid] < num) {
					start = mid + 1;
				}else {
					end = mid - 1;
				}
			}
			bw.write(title[start]+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
