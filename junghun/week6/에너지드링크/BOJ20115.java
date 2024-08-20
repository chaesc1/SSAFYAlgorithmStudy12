package week6.에너지드링크;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20115 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        double sum = 0;
        int max = arr[n - 1];
        for (int i = 0; i < n - 1; i++) {
            sum += (double) arr[i] / 2;
        }

        sum += max;

        // 정수 여부를 확인 후 출력
        if (sum == (int) sum) {
            System.out.println((int) sum);
        } else {
            System.out.println(sum);
        }
    }
}