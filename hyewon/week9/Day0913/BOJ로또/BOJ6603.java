package BOJ.Day0913.BOJ로또;
import java.io.*;
import java.util.*;
public class BOJ6603 {
    static int k = 1;
    static int[] nums, lotto = new int[6];
    static boolean[] visited;
    static Set<String> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            nums = new int[k];
            visited = new boolean[k];
            answer = new LinkedHashSet<>();

            for (int i = 0; i < k; i++) nums[i] = Integer.parseInt(st.nextToken());

            comb(0, 0);
            answer.forEach(System.out::println);
            System.out.println();
        }
    }
    public static void comb(int idx, int start) {
        if (idx == 6) {
            StringBuilder sb = new StringBuilder();
            for (int i : lotto) sb.append(i + " ");
            answer.add(sb.toString());
        }
        else {
            for (int i = start; i < k; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                lotto[idx] = nums[i];
                comb(idx + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
