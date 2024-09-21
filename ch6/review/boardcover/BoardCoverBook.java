package ch6.review.boardcover;

import java.util.Scanner;

public class BoardCoverBook {
    public static final int[][][] coverType = new int[][][]{
            {{0, 0}, {1, 0}, {0, 1}}, //
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}},
    };

    public static boolean set(int[][] board, int y, int x, int type, int delta) {
        boolean ok = true;

        for (int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];

            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length) {
                ok = false;
            } else if ((board[ny][nx] += delta) > 1) {
                // 우선 덮여있던지 간에 현재 타입에 대해 1을 더하고 본다.
                // 그래서 하나라도 겹치더라도 일단은 다 +1 하고, 나중에 빼줌.
                ok = false;
            }
        }
        return ok;
    }

    // 재귀호출 -> 나는 현재 덮은 위치를 반환해주느라 고생했는데, 책에서는 그냥 처음부터 순회해서 안채워진 index를 찾는다.
    public static int cover(int[][] board) {
        int result = 0;

        int y = -1, x = -1;
        // 윗 줄부터 안채워진 index 찾기
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] < 1) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y!=-1)
                break;
        }

        // base case : 모두 채운 경우
        if (y==-1)
            return 1;

        for (int t = 0; t < 4; ++t) {
            if (set(board, y, x, t, 1)) {
                // return true인 경우만 재귀호출 진행
                result += cover(board);
            }
            // 채운거 다시 복구
            set(board, y, x, t, -1);
        }

        return result;
    }


    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int tc = sc.nextInt();

            while (tc > 0) {
                int y = sc.nextInt();
                int x = sc.nextInt();

                int[][] board = new int[y][x];

                for (int r = 0; r < y; ++r) {
                    String l = sc.next();
                    for (int c = 0; c < x; ++c) {
                        char v = l.charAt(c);
                        if (v=='#')
                            board[r][c] = 1;
                        else
                            board[r][c] = 0;
                    }
                }
                System.out.println(cover(board));
                --tc;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
