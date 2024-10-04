//BOJ11286 절대값 힙, 실버1
//우선순위 큐를 사용하는 문제
//Comparator를 생성해야 하는 문제
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//Comparable은 "자기 자신과 매개변수 객체를 비교"하는 것이고, 
//Comparator는 "두 매개변수 객체를 비교"
import java.io.*;
import java.util.*;

public class BOJ11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		//우선순위 조건을 내림차순으로 변경
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) > Math.abs(o2)) {
					return 1;
				} else if(Math.abs(o1) < Math.abs(o2)) {
					return -1;
				} else {
					if(o1 > o2) {
						return 1;
					}else {
						return -1;
					}
				}
			}
		});
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(!pq.isEmpty()) {
					bw.write(pq.poll()+"\n");
				}else {
					bw.write(0 + "\n");
				}
			}else {
				pq.add(num);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
