package ch6.clocksync;

public class Clocksync2 {
    private final static int[] clock = { 12, 9, 3, 9, 3, 3, 9, 3, 12, 6, 12, 9, 12, 9, 6, 6 };
    // private final static int[] clock = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12,
    // 12, 12, 12, 12, 12};
    private static int[][] switches = { { 0, 1, 2 }, { 3, 7, 9, 11 }, { 4, 10, 14, 15 }, { 0, 4, 5, 6, 7 },
            { 6, 7, 8, 10, 12 }, { 0, 2, 14, 15 }, { 3, 14, 15 }, { 4, 5, 7, 14, 15 }, { 1, 2, 3, 4, 5 },
            { 3, 4, 5, 9, 13 } };
    private final static int MAX = 4 ^ 10;

    public static void main(String[] args) {
        // 각 스위치를 4번씩 누르면 모든 경우의 수 누른거임 : 총 경우의 수 4^10
        for (int i = 0; i < MAX; i++) {
            int[] currentClock = clock;
            int[] pushCnt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            // n번 기회에 대해 1 ~ n까지를 다시 카운트한다.
            work(currentClock, pushCnt, i); // 여기서 중복 발생
        }
        System.out.println(-1);
    }

    private static void work(int[] currentClock, int[] pushCnt, int rest) {
        if (rest == 0) {
            int cnt = 0;
            if (checkClock(currentClock)) {
                for (int j = 0; j < 10; j++) {
                    cnt += pushCnt[j];
                    System.out.print(pushCnt[j]);
                }
                System.out.println();
                System.out.println(cnt);
                System.exit(0);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (pushCnt[i] == 3)
                continue;
            else {
                pushCnt[i] += 1;
                currentClock = push(currentClock, i, 1);
                work(currentClock, pushCnt, rest - 1);
                pushCnt[i] -= 1;
                currentClock = push(currentClock, i, -1);
            }
        }
        return;
    }

    private static boolean checkClock(int[] currentClock) {
        for (int i = 0; i < 16; i++) {
            if (currentClock[i] != 12)
                return false;
        }
        return true;
    }

    private static int[] push(int[] currentClock, int switchNum, int reverse) {
        for (int i = 0; i < switches[switchNum].length; i++) {
            currentClock[switches[switchNum][i]] += 3 * reverse;
            if (currentClock[switches[switchNum][i]] == 15)
                currentClock[switches[switchNum][i]] = 3;
            else if (currentClock[switches[switchNum][i]] == 0)
                currentClock[switches[switchNum][i]] = 12;
        }
        return currentClock;
    }
}
