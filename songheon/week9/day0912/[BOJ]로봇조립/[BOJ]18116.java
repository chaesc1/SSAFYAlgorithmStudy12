import java.io.*;
import java.util.*;

public class Main {

	final static int max = 1000001;
	static int n;
	static int robot[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		n = sc.nextInt();
		robot = new int[max];

		Arrays.fill(robot, -1);

		for (int i = 0; i < n; i++) {
			char order = sc.next().charAt(0);

			if (order == 'I') {
				int a = sc.nextInt();
				int b = sc.nextInt();
				union(a, b);
			} else if (order == 'Q') {
				int a = sc.nextInt();
				sb.append(-robot[find(a)]).append('\n');
			}

		}
		System.out.println(sb);

	}

	static int find(int a) {
		if (robot[a] < 0)
			return a;
		return robot[a] = find(robot[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;
		
		robot[aRoot] += robot[bRoot];
		robot[bRoot] = aRoot;
		
//		if(bRoot < aRoot) {
//			robot[aRoot] += robot[bRoot];
//			robot[bRoot] = aRoot;
//		}
//		else {
//			robot[bRoot] += robot[aRoot];
//			robot[aRoot] = bRoot;
//		}
		return true;
	}

}