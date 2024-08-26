package week6.Day0826.BOJ단어수학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BOJ단어수학 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character,Integer> alphaMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String num = br.readLine();
            int size = num.length();
            int exponent = (int) Math.pow(10, size - 1);

            for (int j = 0; j < size; j++) {
                char charAtJ = num.charAt(j);
                alphaMap.put(charAtJ, alphaMap.getOrDefault(charAtJ, 0) + exponent);
                exponent /= 10; // 1000 -> 100 -> 10 -> 1
            }
        }

        List<Integer> values = new ArrayList<>(alphaMap.values());
        values.sort(Collections.reverseOrder());

        int ans = 0;
        int digitValue = 9;

        for (int value : values) {
            if (digitValue < 0) break;
            ans += value * digitValue;
            digitValue--;
        }

        System.out.println(ans);
    }
}
