package week7.벌꿀채취;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2115 {
    static class Honey{
        int row;
        int col;
        int amount;
        boolean visited;

        public Honey(int row, int col, int amount, boolean visited){
            this.row = row;
            this.col = col;
            this.amount = amount;
            this.visited = visited;
        }
    }

    static int price;
    static int price_b;
    static int subset_sum;
    static int max_price;
    static int n, m, c;
    static Honey[][] map;
    static boolean[][] visited;
    static List<Honey> a_honey;
    static List<Honey> b_honey;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map = new Honey[n][n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = new Honey(i, j, Integer.parseInt(st.nextToken()), false);
                }
            }

            visited = new boolean[n][n];
            a_honey = new ArrayList<>();
            b_honey = new ArrayList<>();
            max_price = 0;

            combination(0, 0);

            System.out.println(max_price);
        }
    }

    static int getSum(List<Honey> honeys, boolean[] selected){
        int sum = 0;
        for(int i=0; i<honeys.size(); i++){
            if(selected[i]){
                sum+=honeys.get(i).amount;
            }
        }
        return sum;
    }
    
    static void subset(List<Honey> honey, int depth, boolean[] selected){
        if(getSum(honey, selected) > c)return;

        if(depth == m){
            price = Math.max(subset_sum, getSum(honey, selected));
            return;
        }

        selected[depth] = true;
        subset(honey, depth+1, selected);

        selected[depth] = false;
        subset(honey, depth+1, selected);
    }

    static void updateMaxPrice(List<List<Honey>> selected){
        //System.out.println(selected.size());
        subset_sum = 0;
        for(List<Honey> honeys : selected){
            price = 0;
            subset(honeys, 0, new boolean[m]);
            subset_sum += price;
        }
        max_price = Math.max(subset_sum, max_price);
    }

    static void combination(int startRow, int depth){
        if(depth==2){
//            System.out.println("==================");
//            System.out.println();
//            System.out.println("[a의 꿀통]");
//            for(Honey a : a_honey){
//                System.out.println(a.row+" "+a.col);
//            }
//            System.out.println();
//
//            System.out.println("[b의 꿀통]");
//            for(Honey b : b_honey){
//                System.out.println(b.row+" "+b.col);
//            }
//            System.out.println();

            //updateMaxPrice();

            List<List<Honey>> selected = new ArrayList<>();
            selected.add(a_honey);
            selected.add(b_honey);

            updateMaxPrice(selected);

            return;
        }

        for(int i=startRow; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j].visited)continue;

                int nextCol = j;
                boolean flag = true;
                int size = m;

                //m개의 연속된 꿀통 고르기
                while(size-- > 1){
                    nextCol++;
                    if((nextCol>=n && size>0) || map[i][nextCol].visited){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    if(a_honey.size()==0){
                        addHoney(a_honey, i, j, nextCol);
                        combination(i, depth+1);
                        rollBack(a_honey);
                    }
                    else{
                        addHoney(b_honey, i, j, nextCol);
                        combination(i, depth+1);
                        rollBack(b_honey);
                    }

                }
            }
        }
    }

    static void addHoney(List<Honey> honeys, int row, int startCol, int endCol){
        for(int i=startCol; i<=endCol; i++){
            map[row][i].visited = true;
            honeys.add(map[row][i]);
        }
    }

    static void rollBack(List<Honey> honeys){
        for(Honey h : honeys){
            int row = h.row;
            int col = h.col;
            map[row][col].visited = false;
        }
        honeys.clear();
    }
}
