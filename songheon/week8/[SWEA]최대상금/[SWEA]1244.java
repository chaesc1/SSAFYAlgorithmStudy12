import java.io.*;
import java.util.*;

public class Solution {

	static int tc;
	static char[] chars;
	static int size;
	static boolean[][] visit;
	static int trade;
	static int answer;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			answer = 0;
			String tmp = sc.next();
			size = tmp.length();
			chars = new char[size];
			chars = tmp.toCharArray();
			visit = new boolean[1000000][11];
			
			trade = sc.nextInt();
			
			dfs(0); // 교환 횟수
		
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static int getInt(char[] chars) {
		int num = 0;
		for(int i = 0; i < size; i++) {
			num = num * 10 + (chars[i] - '0');
		}
		return num;
	}
	
	static void dfs(int order) {
		int num = getInt(chars);
		
		if(order == trade) {
			answer = Math.max(num, answer);
			return;
		}
		
		if(visit[num][order]) return;
		
		visit[num][order] = true;
		
		for(int i = 0; i < size; i++) {
			for(int j = i+1; j < size; j++) {
				// swaping
				char tmp = chars[i];
				chars[i] = chars[j];
				chars[j] = tmp;
				
				dfs(order + 1);
				
				chars[j] = chars[i];
				chars[i] = tmp;
			}
		}
	}
	
}