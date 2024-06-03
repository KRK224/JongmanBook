package ch8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 종만북 - ch8 문제: 와일드카드 (문제 ID: WILDCARD, 난이도: 중)
 */
public class Wildcard {

    static String W, S;
    static int[][] cache = new int[101][101];
    static List<String> answer = new ArrayList<>();

    public static void main(String[] args) {
        List<String> answer = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            // 각 테스트 케이스에 대한 pattern 입력
            String pattern = sc.next();
            System.out.println("pattern = " + pattern);
            int fileCnt = sc.nextInt();
            String[] fileList = new String[fileCnt];

            // 파일명 입력
            for (int i = 0; i < fileCnt; i++) {
                fileList[i] = sc.next();
                System.out.println("i = " + i);
                System.out.println("fileList[i] = " + fileList[i]);
            }
        }
    }

    // solution 함수
    public static void solution(String pattern, String[] fileList) {
        // 현재 pattern과 파일 리스트에 대해서 판별 후 answer List에 추가하는 함수

        // 배열 초기화
        initCache(cache);
        // static W 초기화
        W = pattern;
        for (int i = 0; i < fileList.length; i++) {
            // 각 test case의 파일명 초기화
            S = fileList[i];

            // 정답일 경우 answer에 추가
            if (match(W, S)) {
                answer.add(S);
            }
        }


    }

    public static void initCache(int[][] cache) {
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
    }

    /**
     * 메모이제이션 없이 subString으로 작업
     *
     * @return
     */
    public static boolean match(String w, String s) {

        int pos = 0;
        // 한 위치씩 증가하며 위치 비교
        while (pos < w.length() && pos < s.length() && (w.charAt(pos) == '?')
            || w.charAt(pos) == s.charAt(pos)) {
            pos++;
        }

        // 현재 패턴의 마지막까지 도달한 경우 => * 패턴이 없는 경우
        if(pos == w.length()){
            // 그러면서, 파일명의 끝까지 도달한 경우 일치한다.
            return pos == s.length();
        }

        // 도중에 *를 만난 경우,
        if(w.charAt(pos) == '*') {


        }

        return false;
    }

    
}
