package ch6.review.boardcover;

import java.util.Scanner;

public class BoardCover {
    // 올 수 있는 4가지 경우의 수
    static int[][] dx = {{1, 0}, {1, 1}, {0, -1}, {0, 1}};
    static int[][] dy = {{0, 1}, {0, 1}, {1, 1}, {1, 1}};

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int tc = sc.nextInt();

            while (tc > 0) {
                // init board
                int H = sc.nextInt();
                int W = sc.nextInt();

                int[][] cb = new int[H][W];
                for (int i = 0; i < H; i++) {
                    String s = sc.next();
                    for (int j = 0; j < W; j++) {
                        if (s.charAt(j)=='#') {
                            cb[i][j] = 1;
                        } else {
                            cb[i][j] = 0;
                        }
                    }
                }
                System.out.println(cb);

                System.out.println(solution(0, 0, cb));
                tc--;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int solution(int r, int c, int[][] b) {
        int answer = 0;
        // 기저 사례: row 범위를 넘어섰을 때
        if (r >= b.length) {
            return 1;
        }

        if (c >= b[0].length) {
            answer += solution(r + 1, 0, b);
        }

        boolean isFound = false;
        int i = r; // 시작 행 위치
        int j; // 시작 열 위치


        for (j = c; j < b[0].length; j++) {
            if (b[i][j]==0) {
                isFound = true;
                break;
            }
        }

        if (isFound) {
            for (int idx = 0; idx < dx.length; idx++) {
                if (fillBoard(i, j, b, idx)) {
                    answer += solution(i, j + 1, b);
                    unfillBoard(i, j, b, idx);
                }
            }
        } else {
            // 현재 열을 다 채웠을 경우
            answer += solution(i + 1, 0, b);
        }

        return answer;
    }

    // 현재 idx로 채우는 지 여부 반환 및 채움
    public static boolean fillBoard(int r, int c, int[][] board, int idx) {
        int[] dxs = dx[idx];
        int[] dys = dy[idx];
        // 우선 범위 체크
        if (!(c + dxs[0] >= 0 && r + dys[0] >= 0 &&
                c + dxs[1] >= 0 && r + dys[1] >= 0 &&
                c + dxs[0] < board[0].length && r + dys[0] < board.length &&
                c + dxs[1] < board[0].length && r + dys[1] < board.length)) {
            System.out.println(c + dxs[0]);
            System.out.println(r + dys[0]);
            System.out.println(c + dxs[1]);
            System.out.println(r + dys[1]);
            return false;
        }

        if (board[r + dys[0]][c + dxs[0]]==0 && board[r + dys[1]][c + dxs[1]]==0) {
            board[r][c] = 1;
            board[r + dys[0]][c + dxs[0]] = 1;
            board[r + dys[1]][c + dxs[1]] = 1;
            return true;
        } else {
            return false;
        }
    }

    // 원복
    public static void unfillBoard(int r, int c, int[][] board, int idx) {
        int[] dxs = dx[idx];
        int[] dys = dy[idx];

        board[r][c] = 0;
        board[r + dys[0]][c + dxs[0]] = 0;
        board[r + dys[1]][c + dxs[1]] = 0;

    }
}
