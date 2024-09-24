package BOJ.Day0924.BOJ나무_재테크;

import java.io.*;
import java.util.*;

public class BOJ16235 {
    static int N, M, K;
    static int[][] grid, A; // 땅, 추가양분
    static ArrayList<Tree> plant = new ArrayList<>();
    static Queue<Integer> deadTree = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1, 1}; // 상 하 좌 우 우상 좌상 우하 좌하
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                grid[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            plant.add(new Tree(x, y, z));
        }
        Collections.sort(plant, (o1, o2) -> o1.age - o2.age);

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
            Collections.sort(plant, (o1, o2) -> o1.age - o2.age);
        }
        System.out.println(plant.size());
    }
    public static void spring() {
        for (int i = 0; i < plant.size(); i++) {
            Tree tree = plant.get(i);
            if (grid[tree.x][tree.y] < tree.age) {
                deadTree.add(i);
            }
            else {
                grid[tree.x][tree.y] -= tree.age;
                tree.age++;
            }
        }
    }
    public static void summer() {
        while (!deadTree.isEmpty()) {
            Tree tree = plant.get(deadTree.poll());
            grid[tree.x][tree.y] += (tree.age / 2);
            tree.deadTree = true;
        }
    }
    public static void fall() {
        for (int i = 0; i < plant.size(); i++){
            Tree tree = plant.get(i);
            if (tree.deadTree) continue;
            if (tree.age % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = tree.x + dr[d];
                    int nc = tree.y + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                    plant.add(new Tree(nr, nc, 1));
                }
            }
        }
        ArrayList<Tree> newTree = new ArrayList<>();
        for (Tree tree : plant) {
            if (tree.deadTree) continue;
            newTree.add(tree);
        }
        plant = newTree;
    }
    public static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] += A[i][j];
            }
        }
    }
}

class Tree {
    int x, y, age;
    boolean deadTree;
    Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }
}
