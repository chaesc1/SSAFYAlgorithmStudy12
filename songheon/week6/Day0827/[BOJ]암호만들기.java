package day0827;

import java.util.*;
import java.io.*;

//12880kb	96ms

public class Main_B_1759_암호만들기_한송헌 {
	//증가하는 수열이기 때문에 주어진 알파벳을 사전순으로 정렬
	//주어진 알파벳  C개로 L 개의 알파벳을 뽑을 수 있는 조합 구함
		//주어진 조합으로 증가하는 수열을 만들 수 있는 것은 하나이기 때문
		//파라메터로 현재 모음 개수 전달
	//조합 완성되면 조건에 맞는지 판단
		//조건에 맞으면 해당 알파벳 배열을 출력
	
	static int L,C;
	static char input[];
	static char output[];
	static StringBuilder sb;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		L = sc.nextInt();
		C = sc.nextInt();
		
		input = new char[C];
		output = new char[L];
		
		for(int i = 0; i < C; i++) {
			input[i] = sc.next().charAt(0);
		}
		
		Arrays.sort(input);
		combi(0, 0, 0);
		System.out.print(sb);
	}
	
	static void combi(int start, int order, int cnt) {
		if(order == L) {
			if(cnt < 1 || L-cnt < 2) return;
			
			for(int i = 0; i < L; i++) {
				sb.append(output[i]);
			}
			sb.append('\n');
			return;
		}
		for(int i = start; i < C; i++) {
			output[order] = input[i];
			if(output[order] == 'a' || output[order] == 'e' || output[order] == 'i' || output[order] == 'o' || output[order] == 'u')
				combi(i + 1, order + 1, cnt + 1);
			else
				combi(i + 1, order + 1, cnt);
		}
		
		
		
	}
}
