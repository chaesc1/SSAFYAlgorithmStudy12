import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();

		System.out.println(solve(s));
	}

	public static int solve(String s) {

		Stack<Character> stack = new Stack<>();
		int cnt = 0;

		for(int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if(c == '(') stack.push(c);
			else if(c == ')') stack.pop();

			if(c == ')' && s.charAt(i - 1) == '(') {
				cnt += stack.size();
			} 
			else if(c == ')' && s.charAt(i - 1) == ')') cnt++;

		}

		return cnt;
	}

}