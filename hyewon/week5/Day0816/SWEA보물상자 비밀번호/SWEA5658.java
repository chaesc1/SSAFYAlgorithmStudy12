import java.io.*;
import java.util.*;
 
public class SWEA5658{
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        StringTokenizer st;
        
        List<Character> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken())/4;
            int K = Integer.parseInt(st.nextToken());
            
            String num = br.readLine();
            for(int i=0;i<num.length();i++) list.add(num.charAt(i));
            
            for(int i=0;i<N;i++) {
                int count=0;
                for(int j=0;j<num.length();j++) {
                    sb.append(list.get(j));
                    count++;
                    if(count==N) {
                        if(!result.contains(sb.toString())) {
                            result.add(sb.toString());
                        }
                        sb.setLength(0);
                        count=0;
                    }
                }
                char c = list.remove(list.size()-1);
                list.add(0,c);
            }
            
            Collections.sort(result, Collections.reverseOrder());
            
            for(int i=0;i<result.size();i++) {
                if(i==K-1) {
                    answer.append("#"+tc+" "+Integer.parseInt(result.get(i),16)+"\n");
                    break;
                }
            }
            list.clear();
            result.clear();
            
        }
        System.out.println(answer.toString());
 
    }
 
}
