package BOJ.달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20207 {
    static int[] calender;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        calender = new int[366];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            updateCalender(start, end);
        }

        int area = 0;
        int height = 0;
        int width = 0;
        for(int i=0; i<=365; i++){
            if(calender[i] == 0){
                area += height*width;
                height = 0;
                width = 0;
                continue;
            }

            width++;
            height = Math.max(height, calender[i]);
        }

        area += width*height;
        System.out.println(area);
    }

    static void updateCalender(int start, int end){
        for(int i=start; i<=end; i++){
            calender[i]++;
        }
    }
}
