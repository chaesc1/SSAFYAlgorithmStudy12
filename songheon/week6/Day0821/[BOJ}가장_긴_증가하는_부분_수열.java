import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[][] arr;
	static int answer = 0;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		
		arr = new int[2][n+1];
		arr[0][0] = 0; 
		for(int i = 1; i <= n; i++) {
			arr[0][i] = scanner.nextInt();
		}
		arr[1][0] = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = i - 1; j >= 0; j--) {
				int tmp = 0;
				if(arr[0][i] == arr[0][j]) {
					 tmp = arr[1][j];
				}
				else if(arr[0][i] > arr[0][j]) {
					tmp = arr[1][j] + 1;
				}
				arr[1][i] = Math.max(arr[1][i], tmp);
			}
			answer = Math.max(answer, arr[1][i]);
		}
		
		System.out.println(answer);
	}
}