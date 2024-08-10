package week4.사과나무;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import java.io.*;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_19539 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int sum = 0;
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int tree = Integer.parseInt(st.nextToken());
            count += tree/2;
            sum += tree;
        }

        if(sum%3!=0) {
            System.out.print("NO");
            return;
        }

        if(count >= (sum/3)) {
            System.out.print("YES");
            return;
        }

        System.out.print("NO");
    }

}
