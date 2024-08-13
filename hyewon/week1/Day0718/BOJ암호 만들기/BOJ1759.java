import java.io.*;
import java.util.*;

public class Main {
	static int L,C;
	static String[] pwd,alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str1 = br.readLine().split(" ");
		L = Integer.parseInt(str1[0]);
		C = Integer.parseInt(str1[1]);

		pwd= new String[L];
		alpha = new String[C];

		alpha = br.readLine().split(" ");
		Arrays.sort(alpha);
		combin(0,0);

	}

	static void combin(int len, int start) {

		if(len>=L) {
			if(check(pwd)) {
				for(String p : pwd) {
					System.out.print(p);
				}
				System.out.println();
			}
			return;
		}

		for(int i=start; i<C;i++) {
			pwd[len]=alpha[i];
			combin(len+1,i+1);
		}
	}

	static boolean check (String[] pw) {
		int a = 0;
		int b = 0;
		for (int i = 0; i < pwd.length; i++) {
			if(pwd[i].equals("a")||pwd[i].equals("e")||pwd[i].equals("i")||pwd[i].equals("o")||pwd[i].equals("u"))
				b++;
			else
				a++;
		}
		if(a >= 2 && b >= 1) return true; 
		else return false; 
	}
}