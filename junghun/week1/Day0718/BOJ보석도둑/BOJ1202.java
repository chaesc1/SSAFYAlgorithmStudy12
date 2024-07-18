package Day0718;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1202 {
    static class Gem implements Comparable<Gem>{
        int weight;
        int cost;

        public Gem(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Gem o) {
            if (this.weight == o.weight) {
                return this.cost - o.cost;
            }
            return this.weight - o.weight;
        }
    }
    static int N,K;
    static Gem[] gem;
    static int[] bag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gem = new Gem[N];
        bag = new int[K];
        // 보석 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            gem[i] = new Gem(m,v);
        }

        // 가방정보 저장
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);
        Arrays.sort(gem);

        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0, j=0; i<K; i++) {
            while(j < N) {
                // 넣으려는 보석이 가방 할당량 보다 크면
                if (gem[j].weight > bag[i]) {
                    break;
                }
                pq.offer(gem[j++].cost);
            }

            if(!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }
}
