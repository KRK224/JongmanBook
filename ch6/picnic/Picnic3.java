package ch6.picnic;

public class Picnic3 {
    private static int n = 4;
    private static int m = 6;
    private static int[][] pairs = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 0, 2 }, { 1, 3 } };
    private static boolean[][] areFriends = new boolean[10][10];
    private static boolean[] taken = new boolean[10];

    public static void main(String[] args) {

        for (int[] pair : pairs) {
            int i = pair[0];
            int j = pair[1];
            areFriends[i][j] = true;
            areFriends[j][i] = true;
        }

        System.out.println(countPairing(taken));
    }

    public static int countPairing(boolean[] taken) {
        boolean finished = true;
        for (int i = 0; i < n; ++i)
            if (!taken[i])
                finished = false;

        if (finished)
            return 1;
        int ret = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!taken[i] && !taken[j] && areFriends[i][j]) {
                    taken[i] = taken[j] = true;
                    ret += countPairing(taken); // 현재 노드 기준으로 한 번 더 탐색
                    taken[i] = taken[j] = false; // 현재 노드 외에 다른 노드도 체크하기 위해
                }
            }
        }
        return ret;
    }
}
