package ch6.clocksync;

import java.util.Scanner;

public class Clocksync {
    public static int[][] switchChain = {
            { 0, 1, 2, -1, -1 },
            { 3, 7, 9, 11, -1 },
            { 4, 10, 14, 15 - 1 },
            { 0, 4, 5, 6, 7 },
            { 6, 7, 8, 10, 12 },
            { 0, 2, 14, 15, -1 },
            { 3, 14, 15, -1, -1 },
            { 4, 5, 7, 14, 15 },
            { 1, 2, 3, 4, 5 },
            { 3, 4, 5, 9, 13 }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        int[] clock = new int[16];

        while (tc != 0) {
            // 입력 처리
            for (int i = 0; i < 16; i++) {
                int eachTime = sc.nextInt();
                clock[i] = (eachTime / 3) % 4;
            }
            int answer = minimumPush(clock, 0, 0);
            if (answer == 987_654_321) {
                System.out.println(-1 + "");
            } else
                System.out.println(answer + "");
            tc--;
        }
        sc.close();
    }

    public static void push(int[] chain, int[] clock) {
        for (int i : chain) {
            if (i == -1)
                break;
            clock[i] = (clock[i] + 1) % 4;
        }
    }

    public static int minimumPush(int[] clock, int currentSwitch, int currentCnt) {

        int ret = 987_654_321;
        // 기저사례: 모든 시계가 12시일 때 종료

        boolean isFinished = true;
        for (int i : clock) {
            if (i != 0) {
                isFinished = false;
                break;
            }
        }

        if (isFinished)
            return currentCnt;
        else {
            if (currentSwitch == 10)
                return ret;
        }

        for (int times = 0; times < 4; times++) {
            if (times == 0)
                ret = Math.min(minimumPush(clock, currentSwitch + 1, currentCnt + times), ret);
            else {
                push(switchChain[currentSwitch], clock);
                ret = Math.min(minimumPush(clock, currentSwitch + 1, currentCnt + times), ret);
            }
        }
        // 초기화
        push(switchChain[currentSwitch], clock);
        return ret;
    }
}
