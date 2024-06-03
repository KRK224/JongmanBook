package ch7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuardTree {
    public static String input = "xxwwwbxwxwbbbwwxxxwwbbbwwwwbb";
    public static String answer = "xxwbxwwxbbwwbwbxwbwwxwwwxbbwb";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        String[] inputStr = new String[tc];

        // input case 입력
        for (int i = 0; i < tc; i++) {
            inputStr[i] = sc.next();
            System.out.println(solution(inputStr[i]));
        }
        System.out.println(solution(input).equals(answer));

        sc.close();
    }

    public static String solution(String inputStr) {
        List<String> chunkList = new ArrayList<>();
        int count = 0;
        String xChunk;
        boolean isXStart = false;

        if (inputStr.length() == 1)
            return inputStr;

        if (inputStr.charAt(0) == 'x') {
            isXStart = true;
            xChunk = inputStr.substring(1);
        } else {
            xChunk = inputStr;
        }

        for (int i = 0; i < xChunk.length(); i++) {
            if (count == 4)
                break;
            if (xChunk.charAt(i) == 'x') {
                String subSwap = solution(xChunk.substring(i));
                i += subSwap.length() - 1;
                chunkList.add(count++, subSwap);
            } else {
                chunkList.add(count++, xChunk.substring(i, i + 1));
            }
        }

        return isXStart ? "x" + swapChunk(chunkList) : swapChunk(chunkList);

    }

    public static String swapChunk(List<String> chunkList) {
        String tmp = chunkList.get(0);
        chunkList.set(0, chunkList.get(2));
        chunkList.set(2, tmp);
        tmp = chunkList.get(1);
        chunkList.set(1, chunkList.get(3));
        chunkList.set(3, tmp);
        StringBuilder sb = new StringBuilder();
        for (String str : chunkList) {
            sb.append(str);
        }
        return sb.toString();
    }

}
