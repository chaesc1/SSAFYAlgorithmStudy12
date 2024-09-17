package week6.단어수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BOJ_1339 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> alphabetMap = new HashMap<>();

        for(int i=0; i<N; i++){
            String line = br.readLine();
            int size = line.length();
            int exponent = (int) Math.pow(10, size-1);

            for(int j=0; j<size; j++){
                char charAtJ = line.charAt(j);
                alphabetMap.put(charAtJ, alphabetMap.getOrDefault(charAtJ,0)+exponent);
                exponent /= 10;
            }
        }

        List<Integer> values = new ArrayList<>(alphabetMap.values());
        values.sort(Collections.reverseOrder());

        int answer = 0;
        int digitValue = 9;

        for(int value : values){
            answer += value*digitValue;
            digitValue--;
        }

        System.out.println(answer);
    }
}