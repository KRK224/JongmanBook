package ch8;

public class AsymTiling {

    static final int MOD = 1_000_000_007;
    static int[] cache = new int[101];

    public static void main(String[] args) {
        System.out.println(asymTiling(92));
    }

    public static int asymTiling(int n) {
        if (n % 2 == 1) {
            return (tiling(n) - tiling((n - 1) / 2) + MOD) % MOD;
        }
        int ret = tiling(n);
        ret = (ret - tiling(n / 2) + MOD) % MOD; // 음수 방지를 위해 MOD를 더함, 결과는 어차피 나머지
        ret = (ret - tiling(n / 2 - 1) + MOD) % MOD;
        return ret;
    }

    public static int tiling(int n) {
        if (n <= 1) {
            return 1;
        }
        int ret = cache[n];
//        System.out.printf("n = %d, ret = %d%n", n, ret);
        if (ret != 0) {
            return ret % MOD;
        } else {
            ret = tiling(n - 1) + tiling(n - 2);
            cache[n] = ret;
            return ret % MOD;
        }
    }
}
