// 백준 2309번 읽곱 난쟁이 브론즈1
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2309 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int totalSave = 0;
		int total = 0;
		int [] dwarf = new int[9];
		
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
//			System.out.println(Integer.parseInt(st.nextToken()));
			dwarf[i] = Integer.parseInt(st.nextToken());
			total += dwarf[i];
			totalSave = total; //세이브본 저장
//			System.out.println(total);
		}
		Arrays.sort(dwarf);
		
		int spy1 = 0;
		int spy2 = 0;
		
		loop:
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(i == j)
					continue;
				
				total -= (dwarf[i] + dwarf[j]);
				if(total == 100) {
//					System.out.println(dwarf[i] + ", " +dwarf[j]);
//					spy1 = dwarf[i];
//					spy2 = dwarf[j];
					spy1 = i;
					spy2 = j;
					break loop;
				}
				total = totalSave;
				
			}
		}
		
		for(int i = 0; i < 9; i++) {
			if(i != spy1 && i != spy2)
				System.out.println(dwarf[i]);
		}
	
	}
}
