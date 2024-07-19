import java.util.Scanner;
import java.util.Stack;

public class baekjoon_0715_10773 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();
		Stack<Integer> money = new Stack<>();
		
		long result = 0;
		
		for(int i = 0; i < K; i++) {
			int a = sc.nextInt();
			
			if(a != 0) money.push(a);
			else money.pop();
		}
		
		for(int i : money) {
			result += (long)i;
		}
		System.out.println(result);
	}
}
