package week7.물놀이를가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10966 {
    static class Node{
        int row;
        int col;
        int minPath;

        public Node(int row, int col, int minPath){
            this.row = row;
            this.col = col;
            this.minPath = minPath;
        }
    }

    static StringBuilder sb;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static Queue<Node> queue;
    static int totalPath;
    static int n, m;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new char[n][m];
            for(int i=0; i<n; i++){
                String line = br.readLine();
                for(int j=0; j<m; j++){
                    map[i][j] = line.charAt(j);
                }
            }

            totalPath = 0;
            queue = new ArrayDeque<>();
            visited = new boolean[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j] == 'W'){
                        queue.add(new Node(i, j, 0));
                        visited[i][j] = true;
                    }
                }
            }

            bfs();

            sb.append("#").append(tc+1).append(" ").append(totalPath).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(){
        while(!queue.isEmpty()){
            Node now = queue.poll();
            int row = now.row;
            int col = now.col;

            for(int i=0; i<4; i++){
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=n || nextCol>=m)continue;
                if(visited[nextRow][nextCol])continue;

                queue.add(new Node(nextRow, nextCol, now.minPath+1));
                visited[nextRow][nextCol] = true;
                totalPath += now.minPath+1;
            }
        }
    }
}

/**
 * 최단 거리를 탐색하는 문제이므로 bfs로 접근하였다
 * 하지만 각 땅에서 물까지의 경로를 탐색할 경우 땅의 갯수가 많을 수록 실행시간이 증가하므로 비료율적이다
 * 그래서 각 물에서 탐색을 시작한다
 */
