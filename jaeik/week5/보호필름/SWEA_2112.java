//package week5.보호필름;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class SWEA_2112 {
//    static int min;
//    static boolean[] isSelected;
//    static int[][]map;
//    static int D, W, K;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//
//        for(int tc=0; tc<T; tc++){
//            StringTokenizer st = new StringTokenizer(br.readLine());
//
//            D = Integer.parseInt(st.nextToken());
//            W = Integer.parseInt(st.nextToken());
//            K = Integer.parseInt(st.nextToken());
//
//            map = new int[D][W];
//            for(int i=0; i<D; i++){
//                st = new StringTokenizer(br.readLine());
//
//                for(int j=0; j<W; j++){
//                    map[i][j] = Integer.parseInt(st.nextToken());
//                }
//            }
//
//            isSelected = new boolean[D];
//            min = Integer.MAX_VALUE;
//
//        }
//    }
//
//    static void subset(int depth){
//        if(depth==D){
//            dfs(isSelected, 0, 0);
//            return;
//        }
//
//        isSelected[depth]=true;
//        subset(depth+1);
//
//        isSelected[depth]=false;
//        subset(depth+1);
//    }
//
//    static void dfs(boolean[] isSelected, int depth, int index){
//        if(depth>=)
//
//        if(index == D){
//            if(test()){
//
//            }
//        }
//
//        if(isSelected[index]){
//            Arrays.fill(map[index], 0);
//            dfs(isSelected, depth+1, index+1);
//
//            Arrays.fill(map[index], 1);
//            dfs(isSelected, depth+1, index+1);
//        }else {
//            dfs(isSelected, depth, index+1);
//        }
//    }
//
//    static boolean test(){
//        for(int i=0; i<W; i++){
//            int cnt = 1;
//            int start = map[0][i];
//            boolean pass = false;
//
//            for(int j=1; j<D; j++){
//                if(start == map[j][i]){
//                    cnt++;
//                }
//                else{
//                    start = map[i][j];
//                    cnt=1;
//                }
//
//                if(cnt==K){
//                    pass = true;
//                    break;
//                }
//            }
//            if(!pass)return false;
//        }
//
//        return true;
//    }
//}
