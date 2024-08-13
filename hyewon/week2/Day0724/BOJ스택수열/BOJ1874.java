import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		int n = Integer.valueOf(br.readLine());
		int addNum;
		boolean check = false;
		int a = 1;

		for(int i = 1; i <= n; i++) {
			addNum = Integer.valueOf(br.readLine());
			for( ; a <= addNum; a++) {
				stack.push(a);
				sb.append("+").append("\n");
			}

			if(stack.peek().equals(addNum)) {
				stack.pop();
				sb.append("-").append("\n");
			}
			else {
				check = true;
			}
		}


		if(!check) {
			System.out.print(sb);
		}
		else {
			System.out.print("NO");
		}
	}
}