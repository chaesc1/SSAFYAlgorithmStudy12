import java.util.Arrays;
import java.util.Scanner;

public class Day0730_BOJ20207 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] page = new int[1000];
		int max = 0;
		
		for(int i = 0; i < N; i++) { // 캘린더에 일정 추가
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			if(max < y) max = y;
			
			for(int j = x; j <= y; j++) {
				page[j]++;
			}
		}
		
		page = Arrays.copyOf(page, max + 2); // 필요없는 배열 삭제 (마지막 날짜까지 카운팅 하기 위해 +2)
		
		int height = 0;
		int width = 0;
		int result = 0;
		for(int i = 0; i < page.length; i++) {
//			System.out.print(page[i] + " ");
			if(page[i] == 0) { // 연속된 일정 끝날 때마다 코팅지 면적 구하기
//				System.out.println("height : " + height + "width : " + width);
				result += height * width;
				height = 0;
				width = 0;
				continue;
			}
			else {
				if(height < page[i]) height = page[i]; // 연속된 일정 중 하루에 가장 긴 일정 height에 저장
				width++; // 연속된 일정일 경우 width + 1
			}
		}
		System.out.println(result);
	}
}