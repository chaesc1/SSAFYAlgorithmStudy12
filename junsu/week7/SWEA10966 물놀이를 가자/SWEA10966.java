//10966. 물놀이를 가자 D4
//문제를 풀며 보완한 점
//- 클래스 사용에 적응할 수 있었음
//- Queue에 클래스 타입을 줘서 사용해봄
//- 2차원 char 배열을 String을 chatAt으로 값을 할당
//- bfs & 4방탐색을 통해 노드 간에 길이를 구하는 방법 터득(배열 활용해서)
 
import java.io.*;
import java.util.*;
  
public class SWEA10966 {
    public static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
      
    static int N, M, total;
    static char[][]map;
    static int[][]res;
    static int[] dx = {1, 0, -1, 0}; //우 하 좌 상
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
          
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            res = new int[N][M];
            total = 0;
              
//          System.out.println(Arrays.deepToString(map));
//          System.out.println(Arrays.deepToString(check));
            for(int i = 0; i < N; i++) {
                String temp = br.readLine();
                for(int j = 0; j < M; j++) {
                    map[i][j] = temp.charAt(j);
                }
            }
              
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == 'W') {
                        q.add(new Pair(i, j));
                    }
                }
            }
              
            while(!q.isEmpty()) {
                Pair test = q.poll();
//              System.out.println(test.y +" " + test.x);
                  
                for(int i = 0; i < 4; i++) {
                    int nx = test.x + dx[i];
                    int ny = test.y + dy[i];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M) 
                        continue;
//                  System.out.println("방향성 확인 : "+ny + " " + nx);
                    if(map[nx][ny] == 'L') {
                        if(res[test.x][test.y]+1 < res[nx][ny] || res[nx][ny]==0) {
                            res[nx][ny] = res[test.x][test.y]+1;
                        }else {
                            continue;
                        }
                        q.offer(new Pair(nx, ny));
                    }
                      
                }
                  
            }
              
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    total += res[i][j];
                }
            }
              
            sb.append("#").append(test_case).append(" ").append(total+"\n");
          
        }
        System.out.println(sb);
    }
}