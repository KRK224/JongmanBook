package ch6.boardcover;

import java.util.Scanner;

public class Boardcover2 {
    public static final int[][] xs = { { -1, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
    public static final int[][] ys = { { 1, 1 }, { 1, 1 }, { 0, 1 }, { 0, 1 } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        // testcase 순회
        for (int i = 0; i < tc; i++) {
            int answer = 0;
            int h = sc.nextInt();
            int w = sc.nextInt();
            int cntWhite = 0;
            boolean[][] board = new boolean[h][w];

            // generate init board
            for (int iLine = 0; iLine < h; iLine++) {
                String line = sc.next();
                for (int idx = 0; idx < w; idx++) {
                    board[iLine][idx] = line.charAt(idx) == '#' ? true : false;
                    if (!board[iLine][idx])
                        cntWhite++;
                }
            }
            // whiteboard 개수가 3의 배수가 아니면 0개 반환
            if (cntWhite % 3 != 0) {
                System.out.println(0);
            } else {
                // do Something;
                answer = fillWhiteBoard(board, 0, 0, h, w);
                System.out.println(answer);
            }

        }

        sc.close();
    }

    public static int fillWhiteBoard(boolean[][] board, int currentY, int currentX, int h, int w) {
        boolean finished = true;
        if (currentX >= w) {
            currentX = 0;
            currentY += 1;
        }

        if (currentY == h) {
            return 1;
        } else {
            int tempY = currentY;
            int tempX = currentX;
            for (int height = tempY; height < h; height++) {
                if (height > currentY)
                    tempX = 0;
                for (int width = tempX; width < w; width++) {
                    if (!board[height][width]) {
                        finished = false;
                        break;
                    }
                }
                if (!finished)
                    break;
            }
            if (finished)
                return 1;
        }

        int ret = 0;
        for (int i = currentY; i < h; i++) {
            if (i > currentY)
                currentX = 0;
            for (int j = currentX; j < w; j++) {
                if (board[i][j])
                    continue;

                for (int b = 0; b < 4; b++) {
                    if (i + ys[b][0] < 0 || i + ys[b][0] >= h || i + ys[b][1] >= h || i + ys[b][1] < 0)
                        continue;
                    if (j + xs[b][0] < 0 || j + xs[b][0] >= w || j + xs[b][1] < 0 || j + xs[b][1] >= w)
                        continue;

                    if (!board[i + ys[b][0]][j + xs[b][0]] && !board[i + ys[b][1]][j + xs[b][1]]) {
                        board[i][j] = true;
                        board[i + ys[b][0]][j + xs[b][0]] = true;
                        board[i + ys[b][1]][j + xs[b][1]] = true;

                        ret += fillWhiteBoard(board, i, j + 1, h, w);
                        board[i][j] = false;
                        board[i + ys[b][0]][j + xs[b][0]] = false;
                        board[i + ys[b][1]][j + xs[b][1]] = false;
                    }
                }
                // 찾지 못한 경우 지금까지 값 리턴
                if (!board[i][j])
                    return ret;
            }
        }
        return ret;
    }

}