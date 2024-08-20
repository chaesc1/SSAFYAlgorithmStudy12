package week5.줄기세포배양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class SWEA_5653 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static boolean[][] visited;
    static List<Cell> cell;
    static PriorityQueue<Cell> pq;
    static int N, M, K;
    static final int DEAD = 0, ACTIVE = 1, INACTIVE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cell = new ArrayList<>();
            pq = new PriorityQueue<>((p1, p2) -> p2.power-p1.power);
            visited = new boolean[N+2*K+1][N+2*K+1];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<M; j++){
                    int n = Integer.parseInt(st.nextToken());

                    if(n!=0){
                        //인덱스를 map의 중앙에 위치하도록 한다
                        cell.add(new Cell(i + K, j + K, n, n));
                        visited[i+K][j+K] = true;
                    }
                }
            }

            execute();

            System.out.println("#"+(tc+1)+" "+count());
        }
    }

    static void execute(){
        for(int k=1; k<=K; k++){
            //INACTIVE에서 ACTIVE로 변경된 Cell들
            while(!pq.isEmpty()){
                Cell c = pq.poll();
                int row = c.row;
                int col = c.col;

                if(!visited[row][col]){
                    visited[row][col] = true;
                    cell.add(c);
                }
            }

            for(int i=0; i<cell.size(); i++){
                //죽은 cell은 넘어간다
                if(cell.get(i).state == DEAD)continue;

                    //활성화 시간이 된 cell을 활성화 시킨다
                else if(cell.get(i).state==INACTIVE && cell.get(i).time==k){
                    cell.get(i).state=ACTIVE;

                    //cell이 죽는 시간을 업데이트 해준다
                    cell.get(i).time = k + cell.get(i).power;

                    for(int d=0; d<4; d++){
                        int nextRow = cell.get(i).row + dr[d];
                        int nextCol = cell.get(i).col + dc[d];

                        pq.add(new Cell(nextRow, nextCol, k+cell.get(i).power+1, cell.get(i).power));
                    }
                }
                //cell이 죽는 시간이 되었을 경우
                else if(cell.get(i).state==ACTIVE && cell.get(i).time==k){
                    cell.get(i).state = DEAD;
                    cell.get(i).time = 0;
                    cell.get(i).power = 0;
                }
            }
        }
    }

    static int count(){
        int cnt = 0;
        for(int i=0; i<cell.size(); i++){
            if(cell.get(i).state == ACTIVE || cell.get(i).state==INACTIVE)cnt++;
        }
        return cnt;
    }

    static class Cell{
        int row;
        int col;
        int time;
        int state;
        int power;

        Cell(int row, int col, int time, int power){
            this.row = row;
            this.col = col;
            this.time = time;
            this.power = power;
            this.state = INACTIVE;
        }
    }
}


