
//나무 높이 D2 SWEA 14510
import java.io.*;
import java.util.*;

public class SWEA14510 {
	public static void main(String[] args) throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter	bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer	st;
		int	testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int		count = Integer.parseInt(br.readLine());
			int[]	tree = new int[count];
			int		max = 0;
			int		odd = 0;
			int		even = 0;
			int		ans;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < count; i++) {
				int temp = Integer.parseInt(st.nextToken());

				max = Math.max(max, temp);
				tree[i] = temp;
			}
			for (int i = 0; i < count; i++) {
				// 가장 큰 나무의 길이를 max로 두었을 때, max - tree[i]는 그 나무가 자라야하는 길이가 됩니다.
				// 나무가 자라야 하는 길이를 2로 나눈 몫은 짝수 날짜의 갯수가 됩니다.
				// 나무가 자라야 하는 길이를 2로 나눈 나머지는 홀수 날짜의 갯수가 됩니다.
				odd		+= (max - tree[i]) % 2;
				even	+= (max - tree[i]) / 2;
			}
			// 짝수 날짜를 적절히 조정하여 균형을 맞추어주어야 최소 날짜가 나오게 됩니다. 이를 계산해봅시다.
			int temp = Math.max(even - odd, 0) / 3;
			odd += temp * 2;
			even -= temp;
			int oddEvenMin = Math.min(odd, even);
			// 만약 1과 2의 쌍으로 3의 배수를 만들 수 있다면 2일 연속 성장을 만들 수 있으므로, oddEvenMin을 이용하여 odd와 even의 모든 쌍을 2일로 치환합니다.
			// 물 = (1일 : A, 2일 : A, ... , (oddEvenMin * 2 - 1)일 : Z, (oddEvenMin * 2)일 : Z) 
			ans	=	oddEvenMin * 2
					// 그 뒤에 odd가 남아있다면, odd는 홀수 일자에만 더해주어야 하므로 아래와 같은 식으로 표현할 수 있습니다. odd가 0일 때를 주의합니다.
					// 물 = (1일 : A, 2일 : 물을 주지 않음, 3일 : B ... ((odd - oddEvenMin) * 2 - 1)일 : Z)
					+ Math.max((odd - oddEvenMin) * 2 - 1, 0)
					// 그 이후 만약 even이 두 개 남아있다면 1 2 1로 나눌 수 있습니다. 따라서 3일이 소요됩니다.
					// 물 = (1일 : A, 2일 : B, 3일 : A)
					+ (even - oddEvenMin) / 2 * 3
					// 만약 even이 하나 남아있다면 2를 기다려야 하므로 하루를 쉬고 물을 주면 됩니다. 따라서 2일이 소요됩니다.
					// 물 = (1일 : 물을 주지 않음, 2일 : A)
					+ (even - oddEvenMin) % 2 * 2;
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
