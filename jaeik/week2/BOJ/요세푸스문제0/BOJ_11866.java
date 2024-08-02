package BOJ.요세푸스문제0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> list = new LinkedList<>();
        for(int i=1; i<=n; i++){
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        int idx = k-1;
        while(list.size() > 0){
            if(list.size() == 1){
                sb.append(list.remove(idx));
                break;
            }
            sb.append(list.remove(idx)).append(", ");
            idx = (idx+k-1) % list.size();
        }

        sb.append(">");

        System.out.println(sb);
    }
}
