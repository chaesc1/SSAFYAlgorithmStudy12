// BOJ7662 이중 우선순위 큐 골드4
// 우선순위 큐인줄 알았는데 아니었다;;;
import java.io.*;
import java.util.*;

public class BOJ7662 {
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			TreeMap<Integer, Integer> map = new TreeMap<>();
			
			int N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String temp = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				if(temp.equals("I")) {
//					System.out.println(temp);
					map.put(num,  map.getOrDefault(num, 0)+1);
//					System.out.println(minPQ + " " + maxPQ);
				}else {
					if(map.size() == 0) continue;
					if(num == 1) {
						num = map.lastKey();
					}else {
						num = map.firstKey();
					}
					if(map.put(num, map.get(num)-1)==1) {
//						System.out.println(map +" " + num);
						map.remove(num);
					}
				}
			}
			
            if (map.size()==0) {
	            sb.append("EMPTY\n");
	        } else {
	        	sb.append(map.lastKey()+" " + map.firstKey()+"\n");
	        }

		}
		System.out.println(sb.toString());
	}
}
