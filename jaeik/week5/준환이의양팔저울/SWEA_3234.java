package week5.준환이의양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3234 {
    static int result;
    static int[] permutation;
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            n = Integer.parseInt(br.readLine());

            arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            permutation = new int[n];
            boolean[] per_selected = new boolean[n];

            result = 0;

            execute(0, per_selected);

            System.out.println("#"+(tc+1)+" "+result);
        }
    }

    static void subset(int depth, int right, int left){
        if(right>left)return;

        if(depth == n){
            result++;
            return;
        }

        subset(depth+1, right, left+permutation[depth]);
        subset(depth+1, right+permutation[depth], left);
    }

    static void execute(int depth, boolean[] per_selected){
        if(depth == n){
            //좌 우 배치 시작
            //System.out.println(Arrays.toString(permutation));
            boolean[] sub_selected = new boolean[n];
            subset(0, 0, 0);
            return;
        }

        for(int i=0; i<n; i++){
            if(!per_selected[i]){
                per_selected[i] = true;
                permutation[depth] = arr[i];

                execute(depth+1, per_selected);

                per_selected[i] = false;
            }
        }
    }
}

/**
 * [try]
 *  순열과 조합을 이용한 아이디어는 떠올렸다
 *  하지만, 조합하는 과정에서 3개 수가 있으면 3개에 대한 조합을 전부 이루고 반복문을 통해 right와 left변수에 수를 더해주면서 양 저울의 무개를 비교하였다
 *  그래서 시간초과가 발생했다
 *
 * [solve]
 *  현재 조합 함수와 같이 rigth와 left 변수를 인자로 사용하여 함수 시작 부분에서 양 저울을 비교해 조건에 맞지 않으면 return하는 형식으로 가지치기를 해주었다
 *  가지치기가 해결방법인것을 알아냈지만, 구현하는데 생각이 필요했다
 *  지금 생각해보니까 재귀를 통해서 조합을 이루고 다시 반복문으로 비교하는게 재귀에 대한 이해가 부족한 탓인 것 같다
 */
