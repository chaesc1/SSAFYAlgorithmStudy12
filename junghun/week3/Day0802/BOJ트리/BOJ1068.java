package BOJ.DFSBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1068 {
    static int N,deleteNum,count;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //부모 인덱스를 입력받고, 노드를 삭제후 리프 노드를 카운트 하는 깊이 우선 탐색 방법을 적용
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        st = new StringTokenizer(br.readLine());

        // root 노드의 번호를 찾아야해
        int root = 0;
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (parent[i] == -1) root = i;
        }

        deleteNum = Integer.parseInt(br.readLine());
        visited = new boolean[N];

        // deleteNum 을 기반으로 삭제 표시
        delete(deleteNum);

        // 리프노드 갯수 카운트
        solve(root);

        System.out.println(count);
    }

    private static void solve(int num) {
        visited[num] = true;
        // parent[num] 이 -2 즉 삭제된 노드가 아닐 경우에만

        boolean isLeaf = true;
        if (parent[num] != -2) {
            for (int i = 0; i < N; i++) {
                if (!visited[i] && parent[i] == num) {
                    isLeaf = false;
                    solve(i);
                }
            }
            if (isLeaf) count++;
        }
    }
    private static void delete(int deleteNum) {
        parent[deleteNum] = -2;
        for(int i = 0; i<N; i++) {
            if(parent[i] == deleteNum) {
                delete(i);
            }
        }

    }
}
