package BOJ.Day0821.SWEA추억의_2048_게임;

import java.io.*;
import java.util.*;

public class SWEA6109 {
    static int N;
    static int[][] Board, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            Board = new int[N][N];
            answer = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++) Board[i][j] = Integer.parseInt(st.nextToken());
            }

            if(direction.equals("left")) left();
            else if(direction.equals("right")) right();
            else if(direction.equals("up")) up();
            else if(direction.equals("down")) down();

            System.out.println("#" + tc);
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) System.out.print(answer[i][j] + " ");
                System.out.println();
            }
        }
    }

    public static void left() {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> newQ = new LinkedList<>();
        for(int i = 0; i < N; i++) { // 줄 별 계산
            int cnt = 0;
            for(int j = 0; j < N; j++) { // 각 줄의 숫자 q에 추가
                if(Board[i][j] != 0) q.add(Board[i][j]);
//				System.out.println(q);
            }
            while(!q.isEmpty()) { // q가 빌 때까지 반복
                if(q.size() == 1) {
                    newQ.add(q.poll());
                    break;
                }
                int n = q.poll();
                if(n == q.peek()) { // 같은 숫자를 만나면
                    int sum = n + q.poll();
                    newQ.add(sum); // 합치기
                }
                else {
                    newQ.add(n);
                }
            }
//			System.out.println("test : " + newQ);
            int k = 0;
            while(!newQ.isEmpty()) {
                answer[i][k++] = newQ.poll();
            }
        }
    }
    public static void right() {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> newS = new Stack<>();
        for(int i = 0; i < N; i++) { // 줄 별 계산
            int cnt = 0;
            for(int j = 0; j < N; j++) { // 각 줄의 숫자 q에 추가
                if(Board[i][j] != 0) s.add(Board[i][j]);
//				System.out.println(s);
            }
            while(!s.isEmpty()) { // s가 빌 때까지 반복
                if(s.size() == 1) {
                    newS.add(s.pop());
                    break;
                }
                int n = s.pop();
                if(n == s.peek()) { // 같은 숫자를 만나면
                    int sum = n + s.pop();
                    newS.add(sum); // 합치기
                }
                else {
                    newS.add(n);
                }
            }
//			System.out.println("test : " + newS);
            int k = 0;
            while(!newS.isEmpty()) {
                answer[i][N - newS.size()] = newS.pop();
            }
        }
    }
    public static void up() {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> newQ = new LinkedList<>();
        for(int i = 0; i < N; i++) { // 줄 별 계산
            int cnt = 0;
            for(int j = 0; j < N; j++) { // 각 줄의 숫자 q에 추가
                if(Board[j][i] != 0) q.add(Board[j][i]);
//				System.out.println(q);
            }
            while(!q.isEmpty()) { // q가 빌 때까지 반복
                if(q.size() == 1) {
                    newQ.add(q.poll());
                    break;
                }
                int n = q.poll();
                if(n == q.peek()) { // 같은 숫자를 만나면
                    int sum = n + q.poll();
                    newQ.add(sum); // 합치기
                }
                else {
                    newQ.add(n);
                }
            }
//			System.out.println("test : " + newQ);
            int k = 0;
            while(!newQ.isEmpty()) {
                answer[k++][i] = newQ.poll();
            }
        }
    }
    public static void down() {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> newS = new Stack<>();
        for(int i = 0; i < N; i++) { // 줄 별 계산
            int cnt = 0;
            for(int j = 0; j < N; j++) { // 각 줄의 숫자 q에 추가
                if(Board[j][i] != 0) s.add(Board[j][i]);
//				System.out.println(s);
            }
            while(!s.isEmpty()) { // s가 빌 때까지 반복
                if(s.size() == 1) {
                    newS.add(s.pop());
                    break;
                }
                int n = s.pop();
                if(n == s.peek()) { // 같은 숫자를 만나면
                    int sum = n + s.pop();
                    newS.add(sum); // 합치기
                }
                else {
                    newS.add(n);
                }
            }
//			System.out.println("test : " + newS);
            int k = 0;
            while(!newS.isEmpty()) {
                answer[N - newS.size()][i] = newS.pop();
            }
        }
    }
}
