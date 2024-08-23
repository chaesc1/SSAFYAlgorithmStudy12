package week6.Day0823.DSLR;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
//S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
//L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
//R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
public class BOJ9019 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            // a -> b 로 만드는 최소한의 명령
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 최소한의 명령? -> BFS ?
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[10001];
            String[] dslr = new String[10001];
            Arrays.fill(dslr,"");
            q.offer(a);
            visited[a] = true;

            while (!q.isEmpty() && !visited[b]) {
                int now = q.poll();

                int d = (now * 2) % 10000;
                int s = now == 0 ? 9999 : now - 1;
                int l = (now % 1000) * 10 + (now / 1000);
                int r = (now % 10) * 1000 + (now / 10);

                if (!visited[d]) {
                    q.offer(d);
                    visited[d] = true;
                    dslr[d] = dslr[now] + "D";
                }

                if (!visited[s]) {
                    q.offer(s);
                    visited[s] = true;
                    dslr[s] = dslr[now] + "S";
                }

                if (!visited[l]) {
                    q.offer(l);
                    visited[l] = true;
                    dslr[l] = dslr[now] + "L";
                }

                if (!visited[r]) {
                    q.offer(r);
                    visited[r] = true;
                    dslr[r] = dslr[now] + "R";
                }
            }
            System.out.println(dslr[b]);
        }
    }
}
