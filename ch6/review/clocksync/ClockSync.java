package ch6.review.clocksync;

public class ClockSync {
    public static final int INF = 9_999_999;
    public static final int SWITCHES = 10;
    public static final int CLOCK = 16;

    public static int[][] linked = new int[][]{
            {0, 1, 2 - 1, -1},
            {3, 7, 9, 11, -1},
            {4, 10, 14, 15, -1},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15, -1},
            {3, 14, 15, -1, -1},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    public static boolean isAligned(int[] clocks) {
        for (int clock : clocks) {
            if (clock!=12)
                return false;
        }
        return true;
    }

    public static void push(int[] clocks, int sw) {
        int[] target = linked[sw];
        for (int clock : target) {
            if (clock==-1)
                break;

            clocks[clock] += 3;
            // 한바퀴 돌아서 다시 3시
            if (clocks[clock]==15)
                clocks[clock] = 3;
        }
    }

    // 0 ~9까지의 sw를 0~3 번 사이로 눌러 최소 값을 찾는다.
    public static int solve(int[] clocks, int sw) {
        // 모두 누른 상태에서 aligned 되었는지 확인 후 안되었으면 최대값을 반환하여, 최소값이 없음을 안다.
        // aligned 되었으면, 더이상 누를 필요 없이 나를 호출한 함수안의 cnt가 최종 값.
        if (sw==SWITCHES)
            return isAligned(clocks) ? 0:INF;

        int result = INF;
        for (int cnt = 0; cnt < 4; ++cnt) {
            // 0인 경우 안누르고 다음 스위치, 1~3까지는 전에 상태에서 누른 걸로 호출
            result = Math.min(result, cnt + solve(clocks, sw + 1));
            push(clocks, sw);
        }
        return result;
    }


    public static void main(String[] args) {

    }
}
