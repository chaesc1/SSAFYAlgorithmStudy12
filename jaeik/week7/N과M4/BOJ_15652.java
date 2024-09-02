package week7.N과M4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15652 {
    static boolean[] visited;
    static int[] arr;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        visited = new boolean[n];

        combination(1, 0);

    }

    static void combination(int start, int depth){
        if(depth == m){
            for(int val:arr){
                System.out.print(val+" ");
            }
            System.out.println();
            return;
        }

        for(int i=start; i<=n; i++){
            arr[depth] = i;
            combination(i, depth+1);
        }
    }
}

/**
 * combination함수를 재귀 호출할 때 start 인자로 for문 순회에 사용되는 i변수의 값을 넣어준다
 */