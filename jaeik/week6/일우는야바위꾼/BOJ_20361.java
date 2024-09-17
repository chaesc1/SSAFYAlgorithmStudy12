package week6.일우는야바위꾼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20361 {
    static int num;
    static int snack;
    static int n;
    static int[] cups;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        snack = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        //�� �ʱ�ȭ
        cups = new int[num+1];
        for(int i=1; i<=num; i++) {
            cups[i] = i;
        }

        //�߹���
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            change(c1, c2);
        }

        int result = 0;
        for(int i=1; i<=num; i++) {
            if(cups[i]==snack) {
                result = i;
                break;
            }
        }

        System.out.println(result);

    }

    static void change(int idx1, int idx2) {
        int temp = cups[idx1];
        cups[idx1] = cups[idx2];
        cups[idx2] = temp;
    }

}
