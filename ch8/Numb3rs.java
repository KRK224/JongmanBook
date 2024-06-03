package ch8;

import java.util.Scanner;

public class Numb3rs {
    public static float[][] cache = new float[50][101];
    public static int n = 8;
    public static int d = 2;
    public static int initP = 3;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int testCase = scanner.nextInt();
//        for (int tc = 0; tc < testCase; tc++) {
//            // testCase마다 초기화
//            cache = new float[50][101];
//            n = scanner.nextInt();
//            d = scanner.nextInt();
//            initP = scanner.nextInt();
//            System.out.printf("n=%d, d=%d, initP=%d%n", n, d, initP);
//            int[][] pToP = new int[n + 1][n + 1];
//            // 가로행 입력
//            for (int i = 0; i < n; i++) {
//                int total = 0;
//                // 세로 행 입력
//                for (int j = 0; j < n; j++) {
//                    pToP[i][j] = scanner.nextInt();
//                    total += pToP[i][j];
//                }
//                pToP[i][n] = total;
//            }
//            int posibleT = scanner.nextInt();
//            for (int posT = 0; posT < posibleT; posT++) {
//                int pos = scanner.nextInt();
//                System.out.println(solution(pos, d, pToP));
//            }
//        }
//        int[][] pToP = {
//                {0, 1, 1, 1, 0, 3},
//                {1, 0, 0, 0, 1, 2},
//                {1, 0, 0, 0, 0, 1},
//                {1, 0, 0, 0, 0, 1},
//                {0, 1, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 0}
//        };
//        System.out.println(solution(0, d, pToP));
//        System.out.println(solution(2, d, pToP));
//        System.out.println(solution(4, d, pToP));
        int[][] pToP = {
                {0, 1, 1, 1, 0, 0, 0, 0, 3},
                {1, 0, 0, 1, 0, 0, 0, 0, 2},
                {1, 0, 0, 1, 0, 0, 0, 0, 2},
                {1, 1, 1, 0, 1, 1, 0, 0, 5},
                {0, 0, 0, 1, 0, 0, 1, 1, 3},
                {0, 0, 0, 1, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        System.out.println(solution(3, d, pToP));
        System.out.println(solution(1, d, pToP));
        System.out.println(solution(2, d, pToP));
        System.out.println(solution(6, d, pToP));

    }

    public static float solution(int pos, int day, int[][] pToP) {
        float ret = 0f;
        // 기저 사례.
        if (day==1) {
            // 시작점에서부터의 확률
            ret = (float) pToP[initP][pos] / pToP[initP][n];
            return ret;
        }
        if (cache[pos][day]!=0)
            return cache[pos][day];

        // 직전의 마을(lastPos)에서부터 현재 pos까지의 확률 계산
        for (int lastPos = 0; lastPos < n; lastPos++) {
            float debug = solution(lastPos, day - 1, pToP);
            float posible = (float) pToP[lastPos][pos] / pToP[lastPos][n];
            ret += debug * posible;
        }
        cache[pos][day] = ret;
        return ret;
    }
}
