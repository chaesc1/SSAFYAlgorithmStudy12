package coding;
import java.io.*;
import java.util.*;

public class BOJ20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //숫자 두개 주어지면, schedule[해당숫자 두개의 사잇값]++;
        //schedule의 값이 0이 아니면 가로가 존재하고, 해당 값 자체가 높이를 의미
        int N = Integer.parseInt(br.readLine());


        int[] schedule = new int[367]; //1~365
        //schedule 배열을 이해한다면, 문제의 80퍼센트는 이해한것이다.
        //아래는 1일부터 5일까지의 일정을 나타낸것 (1이면 일정 있음, 0이면 일정 없음)
        //주어진 숫자는 12, 45, 24
        //1 2 3 4 5
        //1 1 0 0 0 (12)
        //0 0 0 1 1 (45)
        //0 1 1 1 0 (24)
        //바로 위의 내용을, schedule 배열으로 만들면,
        //1 2 1 2 1 이 된다.
        //해당 스케줄으로 직사각형을 만들기 위해서는,
        //가로길이는 5, 세로길이는 schedule[1]~schedule[5]의 최댓값 2가 된다.
        //즉 5 * 2 = 10이 된다.



		//각각 최대 최소는, 주어지는 모든 숫자들을
        //문제를 편하게 풀기 위해 최소와 최대를 기록.
        int min = 1000;
        int max = -1;

        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            min = Math.min(S, min);
            max = Math.max(E, max);
//            System.out.println(min+" "+max);

            for(int j = S ; j <= E ; j++) {
                schedule[j]++;
            }

        }
        //여기까지 오면 내가 for 문을 돌기 위한 최솟값 min 과 max 가 저장.
        //schedule[i] 값이 0이 아니면 가로 있는것, 해당 값이 높이로 초깃값 완료.

        //schedule 배열에서 값이 0인 친구들을 어레이리스트 zero에 저장.
        //zero 의 연속된 값 두개를 꺼낸다고 하자.
        //예를 들어 5, 10 이 나왔으면, 5와 10이 직사각형을 이루고(schedule[5], schedule[10]이 0이고, 그 사이는 0이 아니니까),
        //schedule[5] 부터 schedule[10] 까지를 순회하며 최댓값 높이를 구한다.

        ArrayList<Integer> zero = new ArrayList<>(); //값이 0인 index 들 기록
        for(int i = min-1 ; i <= max+1 ; i++) {
            if(schedule[i] == 0) zero.add(i);
        }

        int answer = 0;

        for(int i = 0 ; i < zero.size()-1 ; i++) {
            int s = zero.get(i);
            int e = zero.get(i+1);

            int width = e-s-1;
            int heigth = 0;
            for(int j = s+1 ; j <= e-1 ; j++) {
                if(heigth < schedule[j]) heigth = schedule[j]; // 최대 높이값 설정
            }

            answer += width * heigth;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }


}
