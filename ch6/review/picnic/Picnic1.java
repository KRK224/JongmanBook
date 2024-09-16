package ch6.review.picnic;

import java.util.Scanner;

public class Picnic1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int tc = sc.nextInt();

            while (tc > 0) {
                int n = sc.nextInt();
                int pair = sc.nextInt();

                // init value;
                boolean[] taken = new boolean[n];
                boolean[][] isFriends = new boolean[n][n];

                while (pair > 0) {
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    isFriends[i][j] = isFriends[j][i] = true;
                    pair--;
                }
                System.out.println(solution(taken, isFriends));
                tc--;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int solution(boolean[] taken, boolean[][] isFriends) {
        int target = -1;
        int answer = 0;
        for (int i = 0; i < taken.length; i++) {
            if (!taken[i]) {
                // 중복 제거를 위해 순서 강제
                // 가장 빠른 false를 찾으면 break;
                target = i;
                break;
            }
        }

        // 기저 사례: 현재 상황에서 모든 학생이 짝을 찾았으면 return 1
        if (target==-1)
            return 1;

        for (int j = target + 1; j < taken.length; j++) {
            // 짝이 없으면서 둘이 친구 인 경우
            if (!taken[j] && isFriends[target][j]) {
                taken[target] = taken[j] = true;
                answer += solution(taken, isFriends);
                taken[target] = taken[j] = false;
            }
        }
        return answer;
    }


}
