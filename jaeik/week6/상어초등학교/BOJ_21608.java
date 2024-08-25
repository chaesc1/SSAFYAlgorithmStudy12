package week6.상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_21608 {
    static class Student{
        int no;
        int[] like;

        public Student(int no, int[] friend){
            this.no = no;
            this.like = friend;
        }
    }

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int n, studentNum;
    static int[][] map;
    static Student[] students;
    static HashMap<Integer, Student> hashMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        studentNum = n*n;
        map = new int[n][n];
        students = new Student[studentNum];
        hashMap = new HashMap<>();

        for(int i=0; i<studentNum; i++) {
            st = new StringTokenizer(br.readLine());

            int studentNo = Integer.parseInt(st.nextToken());

            int[] like = new int[4];
            for (int j = 0; j < 4; j++) {
                like[j] = Integer.parseInt(st.nextToken());
            }

            students[i] = new Student(studentNo, like);
            hashMap.put(studentNo, students[i]);
        }

        for(Student student : students){
            assignSeat(student);
        }

        int totalPreference = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                totalPreference += getPreference(i, j);
            }
        }

        System.out.println(totalPreference);
    }

    static int getPreference(int row, int col){
        int[] like = hashMap.get(map[row][col]).like;
        int likeCount = 0;
        int preference = 0;

        for(int d=0; d<4; d++){
            int nextRow = row+dr[d];
            int nextCol = col+dc[d];

            if(nextRow<0 || nextCol<0 || nextRow>=n || nextCol>=n)continue;

            for(int i=0; i<4; i++){
                if(like[i]==map[nextRow][nextCol])likeCount++;
            }
        }

        switch (likeCount){
            case 0:
                preference=0;
                break;
            case 1:
                preference=1;
                break;
            case 2:
                preference=10;
                break;
            case 3:
                preference=100;
                break;
            case 4:
                preference=1000;
                break;
        }

        return preference;
    }

    static void assignSeat(Student student){
        int[] like = student.like;
        int row = 0;
        int col = 0;
        int maxLike = -1;
        int maxEmpty = -1;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j]!=0)continue;

                int likeCount = 0;
                int emptyCount = 0;

                for(int d=0; d<4; d++){
                    int nextRow = i + dr[d];
                    int nextCol = j + dc[d];

                    if(nextRow<0 || nextCol<0 || nextRow>=n || nextCol>=n)continue;

                    for(int no : like){
                        if(map[nextRow][nextCol] == no)likeCount++;
                    }

                    if(map[nextRow][nextCol]==0)emptyCount++;
                }

                if(likeCount>maxLike || (likeCount==maxLike && emptyCount>maxEmpty)){
                    row = i;
                    col = j;
                    maxLike = likeCount;
                    maxEmpty = emptyCount;
                }
            }
        }

        map[row][col] = student.no;
    }
}
