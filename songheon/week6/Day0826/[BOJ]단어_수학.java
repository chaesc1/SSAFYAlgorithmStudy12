import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int answer;
	static String words[];
	static int alph[];
	static int cnt; //알파벳 개수
	static PriorityQueue<El> pq;
	
	static class El{
		char elem;
		int prio;
		public El(char elem, int prio) {
			super();
			this.elem = elem;
			this.prio = prio;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>((e1, e2) -> e2.prio-e1.prio);
		words = new String[n];
		alph = new int[26];

		for (int i = 0; i < n; i++) {
			words[i] = br.readLine();
			int k = 1;
			for (int j = words[i].length()-1; j >= 0; j--) {
					alph[words[i].charAt(j) - 'A'] += k;
					k *= 10;
			}
		}

		for (int i = 0; i < 26; i++) {
			if (alph[i] != 0) {
				pq.add(new El((char)(i + 'A'), alph[i]));
			}
		}
		
		int k = 9;
		int sz = pq.size();
		for(int i = 0; i < sz; i++) {
			alph[pq.peek().elem -'A'] = k;
			pq.poll();
			k--;
		}
		
		for(int i = 0; i < n; i++) {
			String str = "";
			for(int j = 0; j < words[i].length(); j++) {
				str += alph[words[i].charAt(j) -'A'];
			}
			answer += Integer.parseInt(str);
		}
		
		
		System.out.println(answer);
	}
}