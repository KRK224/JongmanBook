package ch6.review.boggle;

import java.util.Arrays;

/**
 * 1차 시도.
 */
public class Boggle1 {
    // 12시부터 시계 방향 이동
    static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    static final int max_size = 5;
    static final char[][] board = {
            {'U', 'R', 'L', 'P', 'M'},
            {'X', 'P', 'R', 'E', 'T'},
            {'G', 'I', 'A', 'E', 'T'},
            {'X', 'T', 'N', 'Z', 'Y'},
            {'X', 'O', 'Q', 'R', 'S'}
    };

    public static void main(String[] args) {
        char[] word = "PRETTY".toCharArray();
        char[] word2 = "GIRL".toCharArray();
        char[] word3 = "REPEAT".toCharArray();
        char[] word4 = "ZETTY".toCharArray();
        boolean result = hasWord(1, 1, word);
        System.out.println(result);
        result = hasWord(2, 0, word2);
        System.out.println(result);
        result = hasWord(1, 2, word3);
        System.out.println(result);
        result = hasWord(3, 3, word4);
        System.out.println(result);
    }

    public static boolean hasWord(int y, int x, char[] word) {
        if (word.length < 0 || x < 0 || y < 0 || x >= 5 || y >= 5 || board[y][x]!=word[0])
            return false;

//        System.out.println("현재 단어: " + word[0]);
        if (word.length==1) {
//            System.out.println("마지막 단어임으로 종료합니다.");
            return true;
        } else {
            boolean result = false;
            char[] word2 = Arrays.copyOfRange(word, 1, word.length);
//            System.out.println(word2);
            for (int i = 0; i < dx.length; i++) {
                result = hasWord(y + dy[i], x + dx[i], word2);
                if (result) {
                    break;
                }
            }
            return result;
        }
    }
}
