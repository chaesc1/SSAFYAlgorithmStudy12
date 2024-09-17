package week8.괄호추가하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class BOJ_16637 {
    static int N;
    static int max;
    static List<Integer> nums;
    static List<Character> ops;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String line = br.readLine();
        nums = new ArrayList<>();
        ops = new ArrayList<>();
        for(int i=0; i<line.length(); i++){
            char ch = line.charAt(i);
            if(ch=='+'||ch=='-'||ch=='*'){
                ops.add(ch);
            }else {
                nums.add(Character.getNumericValue(ch));
            }
        }

        max = Integer.MIN_VALUE;
        dfs(nums.get(0), 0);

        System.out.println(max);
    }

    static int calc(int number1, char op, int number2){
        int result = 0;

        switch (op){
            case '+':
                result = number1+number2;
                break;
            case '-':
                result = number1-number2;
                break;
            case '*':
                result = number1*number2;
                break;
        }

        return result;
    }

    static void dfs(int result, int opsIdx){
        if(opsIdx == ops.size()){
            max = Math.max(max, result);
            return;
        }

        //괄호가 없는 경우
        dfs(calc(result, ops.get(opsIdx), nums.get(opsIdx+1)), opsIdx+1);

        //괄호가 있는 경우
        if(opsIdx+1<ops.size()){
            int res = calc(nums.get(opsIdx+1), ops.get(opsIdx+1), nums.get(opsIdx+2));
            dfs(calc(result, ops.get(opsIdx), res), opsIdx+2);
        }
    }
}
/**
 * 백트래킹을 사용하여 괄호 없는 연산 수행 후 수식의 뒤에서 부터 괄호를 추가하여 연산을 한다
 * max 값을 0으로 설정해두면 max값이 음수일 때 답이 틀린다
 */