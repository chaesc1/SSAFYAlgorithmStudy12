package week3.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class BOJ_1068 {
    static int n;
    static int d;
    static int[] parent;
    static int answer;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());;
        parent = new int[n];
        for(int i=0; i<n; i++){
            //i번 노드의 부모 업데이트
            int p = Integer.parseInt(st.nextToken());
            parent[i] = p;

            if(p == -1)root = i;
        }

        d = Integer.parseInt(br.readLine());

        delete(d);

        answer = 0;
        visited = new boolean[n];
        dfs(root);

        System.out.println(answer);
    }

    static void delete(int d){
        parent[d] = -2;

        for(int i=0; i<n; i++){
            if(parent[i] == d){
                delete(i);
            }
        }
    }

    static void dfs(int start){
        visited[start] = true;
        boolean isLeaf = true;

        if(parent[start]!=-2) {
            for(int i=0; i<n; i++){
                if(parent[i] == start && !visited[i]){
                    dfs(i);
                    isLeaf = false;
                }
            }

            if(isLeaf)answer++;
        }
    }
}

/**
 * [try]
 *  List<List<Integer>>를 사용해 트리를 업데이트 하고
 *  0번 노드 부터 트리를 순회하며 삭제한 노드는 건너뛰고 나머지 노드에 해당하는 리스트의 크기 즉 자식의 수가 0이면 answer를 업데이트 해주었다
 *  하지만 루트가 무조건 0번인 조건으로 코딩해서 틀렸다
 */

/**
 * [answer]
 *  for문으로 i의 parent를 input으로 업데이트하였다
 *  그리고 d의 부모부터 d의 자식을 모두 -2로 업데이트 했다
 *  1차원 배열로 해결할 수 있어서 좋은 방법인 것 같다
 */