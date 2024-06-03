package ch8;

import java.util.Scanner;

public class Poly {
    public static int[][] cache = new int[101][101];
    public static final int MOD = 10_000_000;

    public static void main(String[] args) {
        int testCase;
        Scanner scanner = new Scanner(System.in);
        testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            int n = scanner.nextInt();
            int result = 0;
            for (int first = 1; first <= n; first++) {
                result += poly(n, first); // poly(n) = poly(n,1) + ... + poly(n,n)
            }
            System.out.println(result % MOD);
        }
        scanner.close();
    }

    public static int poly(int n, int first) {
        // 기저 사례
        if (n==first)
            return 1;
        // 이미 존재하는 경우
        if (cache[n][first]!=0)
            return cache[n][first];

        int ret = 0;
        for (int second = 1; second <= n - first; second++) {
            int add = first + second - 1;
            add *= poly(n - first, second);
            add %= MOD;
            ret += add;
            ret %= MOD;
        }
        cache[n][first] = ret;
        return ret;
    }

}
