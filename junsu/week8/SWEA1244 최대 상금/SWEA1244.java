
//1244 .[S/W 문제해결 응용] 2일차 - 최대 상금
//완전 탐색에 대해 다시 생각하게 되었다. (평소 시야에서 살짝 더 넓어진 느낌)
//HashSet, String, char[] 과 같은 다양한 자료형 타입을 사용하며 서로 상호 변환 하는 연습이 됨
//평소에 잘 사용하지 않는 내장 함수( toCharArray(), Collections.max ), 자료형 변환을 연습할 수 있었음
import java.io.*;
import java.util.*;

public class SWEA1244 {
	static HashSet<String> input1;
	static HashSet<String> input2;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			input1 = new HashSet<>();
			input2 = new HashSet<>();  // 체크못한부분
			
			input1.add(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			for(int a = 0; a < N; a++) {
				String str = "";
				Iterator iter = input1.iterator();
				while(iter.hasNext()) {
//					System.out.println((String)iter.next());
					str = (String)iter.next();
					for(int i = 0; i < str.length(); i++) {
						for(int j = 0; j < str.length(); j++) {
							if(i == j) continue;
							char[] ch = str.toCharArray();
							ch[i] = str.charAt(j);
							ch[j] = str.charAt(i);
							String temp = new String(ch);
//							System.out.println(temp);
							input2.add(temp);
						}
					}
				}
				input1 = input2;
				input2 = new HashSet<>();
			}
			String max = Collections.max(input1);
				System.out.println("#"+tc+" "+max);
		}
	}
}
