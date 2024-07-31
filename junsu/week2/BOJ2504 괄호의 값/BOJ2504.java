import java.io.*;
import java.util.*;

public class BOJ2504 {
	private static int getWeight(char c) {
		if(c == '(') {
			return 2;
		}else {
			return 3;
		}
	}

	private static boolean isMatch(Character peek, char c) {
		if (peek == '(' && c == ')')
			return true;
		if (peek == '[' && c == ']')
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String bracket = br.readLine();

		int len = bracket.length();
		int result = 0;
		int weight = 1; //가중치, 곱할거라서 초기값을 1로 설정해준다.
		
		Stack<Character> s = new Stack<>(); //괄호와 문자를 하나씩 받음
		
		for (int i = 0; i < len; ++i) {
			char c = bracket.charAt(i); // 문자열을 하나씩 char 문자로 받아냄
			
			if (c == '(' || c == '[') { // 여는 괄호열일 경우
				s.add(c); // 스택에 넣고
				weight *= getWeight(c); // 가중치를 곱해준다. ( 분배법칙 적용 )
			} else {
				// 스택이 비어있지 않고 괄호 쌍이 맞다면
				if (!s.isEmpty() && isMatch(s.peek(), c)) { // 괄호열의 쌍이 맞다면, 식을 계산한다.
					if (c == ')') {
						if (bracket.charAt(i - 1) == '(') // 바로 직전이 여는 괄호열이였다면, 바로 숫자로 처리하여 계산
							result += weight;
						s.pop();  // 계산해주고 pop으로 빼줌
						weight /= 2; // 분배법칙이 함께 적용될 괄호가 종료되었음. 그러니 다시 나눠줘야함 ************
					} else {
						if (bracket.charAt(i - 1) == '[') // 바로 직전이 여는 괄호열이였다면, 바로 숫자로 처리하여 계산
							result += weight;
						s.pop();
						weight /= 3; // 분배법칙이 함께 적용될 괄호가 종료되었음.
					}
				} else { // 쌍이 안 맞는 경우 처리
					result = 0;
					break;
				}
			}
		}
		if (!s.isEmpty()) // 괄호열이 남았다. 올바르지 못한 괄호열이다.
			result = 0;

		System.out.println(result);
	}
}