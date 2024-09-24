//package BOJ.Day0912.BOJ집합의_표현;
//
//import java.io.*;
//import java.util.*;
//
//public class BOJ1717 {
//    static int[] parent;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        parent = new int[N + 1];
//        for (int i = 1; i <= N; i++) {
//            parent[i] = i;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            int command = Integer.parseInt(st.nextToken());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//
//            if (command == 0) union(a, b);
//            else if (command == 1) sb.append(check(a, b) ? "YES" : "NO" + "\n");
//            else continue;
//        }
//        System.out.println(sb);
//    }
//    public static int find(int x) {
//        if (x == parent[x]) return x;
//        return parent[x] = find(parent[x]);
//    }
//    public static void union(int x, int y) {
//        int rootX = find(x);
//        int rootY = find(y);
//
//        if (rootX != rootY) {
//            if (rootX > rootY) parent[rootX] = rootY;
//            else parent[rootY] = rootX;
//        }
//    }
//    public static boolean check(int x, int y) {
//        int rootX = find(x);
//        int rootY = find(y);
//
//        if (rootX == rootY) return true;
//        return false;
//    }
//}

package BOJ.Day0912.BOJ집합의_표현;

import java.io.*;
import java.util.*;

public class BOJ1717 {
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) union(a, b);
            else if (command == 1) sb.append(check(a, b) ? "YES" : "NO").append("\n");
            else continue;
        }
        System.out.println(sb);
    }

    // Find 함수: 경로 압축 기법 적용
    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 부모 노드를 루트로 변경
        }
        return parent[x];
    }

    // Union 함수: 랭크를 사용하여 두 집합을 합침
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // 랭크를 기준으로 합치기
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Check 함수: 두 원소가 같은 집합에 속하는지 확인
    public static boolean check(int x, int y) {
        return find(x) == find(y);
    }
}
