package week7.벌꿀채취;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author onyoo
 * @performance
 * @category
 * @note
 * 가로로 연속되도록 M개의 벌통을 선택 (겹치면 안됨)
 * 두 일꾼이 채취할 수 있는 꿀의 최대 양은 C를 넘지 않아야 한다
 * 꿀의 가치는 꿀의 양의 제곱만큼이다
 * 여기에서 "수익의 합이 최대" 가 되는 경우를 찾아야 한다
 * 이런 문제가 나오면 어떤걸 구해야하는지에 대해 집중해야한다.
 * 다른거 다 필요없고 최대값을 구해야하기 때문에,가장 최대가 되는 값이 나오도록 값을 필터링 하면 된다
 * 합계가 C보다 작으면서 제곱의 합이 최대가 되도록 한다. 즉, 최대 값을 갱신하면서 최대 값 보다 작다면 애초에 계산할 필요가 없다
 * 이러한 경우 작은 경우의 이후 계산과정을 진행할 수 없도록 하는 것이 좋다 -> 불필요한 계산을 줄이기 위하여
 * 여기서 또 중요한 아이디어는 2차원 배열을 1차원 배열처럼 펼쳐놓고 생각하는 것이다
 * 2차원 배열에서 M개의 연속된 벌통을 선택하는 경우의 수를 구하기에는 복잡하기 때문에
 * 2차원 배열을 1차원 배열처럼 생각한 뒤, 그걸 좌표계로 변경하는 아이디어를 생각해내야한다 이건,자주 사용되니까 알아두자
 * @see
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 * @since 2023-10-11
 **/
public class SWEA_2115_ANSWER {
    static int T; // 테스트 케이스의 개수
    static int N,M,C; // 벌통의 크기 , 선택할 수 있는 벌통의 개수 , 채취할 수 있는 꿀의 최대 양
    static int[][] map; // 벌통
    static int[] selected; // 선택한 벌통 저장배열
    static int sumA,sumB; // A
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine()," ");
            N = Integer.parseInt(st.nextToken()); // 벌통의 크기
            M = Integer.parseInt(st.nextToken()); // 하나의 일꾼이 선택할 수 있는 벌통의 개수
            C = Integer.parseInt(st.nextToken()); // 채취할 수 있는 꿀의 최대 양

            map = new int[N][N]; // 초기화
            selected = new int[2]; // 두명의 일꾼의 시작 지점
            answer = 0;

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine()," ");
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0,0);
            System.out.printf("#%d %d \n",t,answer);
        }
    }
    static void dfs(int depth,int idx){
        // 두개의 벌통만 선택하고 그 뒤에 연속으로 M-1 개를 자동으로 고르게 하자
        if(depth == 2){
            // 두개의 벌통을 모두 선택했다
            if(selected[0] >= selected[1] - (M-1)) return;
            // 벌통 선택은 어짜피 일꾼의 앞뒤가 없기 때문에 뒤 부분만 확인해도 된다
            if((selected[0] + M - 1) / N != selected[0] / N ||  (selected[1] + M - 1) / N  != selected[1] / N) return;
            // 같은 열에 있는지 확인해야한다
            // 채취하는 벌통에서 가장 최대로 나올 수 있는 값을 구해보자
            int honey = getHoney(selected[0],selected[1]); //
            answer = Math.max(answer,honey);
            return;
        }

        for(int i=idx;i<N*N; i++){
            selected[depth] = i;
            dfs(depth+1,i+1);
        }
    }
    static int getHoney(int a,int b){
        //최대 벌꿀양을 구하자
        sumA = 0;
        for(int r=1;r<=M;r++){
            // i 만큼 뽑아보자
            combination(a,a+M,r,0,0,0,1);
        }
        sumB = 0;
        for(int r=1;r<=M;r++){
            combination(b,b+M,r,0,0,0,2);
        }

        return sumA + sumB;
    }
    static void combination(int start,int end,int r,int depth,int sum,int rev,int type){
        if(depth == r){
            if(sum > C) return;
            if(type == 1) sumA = Math.max(sumA,rev);
            else sumB = Math.max(sumB,rev);
            return;
        }
        for(int i=start;i<end;i++){ // 연속으로 꿀을 채취해야하기 때문에 visited 배열이 필요 없다
            int h = map[i/N][i%N];
            combination(i+1,end,r,depth+1,sum + h,rev + (int) Math.pow(h,2),type);
        }
    }


}