package ch9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Packing {
    static int tc, n, capacity;
    static int[][] cache = new int[1001][101];
    static String[] itemName = new String[101];
    static int[] volume = new int[101];
    static int[] need = new int[101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tc = Integer.parseInt(sc.next());

        while (tc > 0) {
            n = sc.nextInt();
            capacity = sc.nextInt();
            // 캐시 초기화
            for (int i = 0; i < 1001; i++) {
                Arrays.fill(cache[i], -1);
            }

            List<String> picked = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                String s = sc.next();
//                System.out.println("item name = " + s);
                itemName[i] = s;
                s = sc.next();
//                System.out.println("volume = " + s);
                volume[i] = Integer.parseInt(s);
                s = sc.next();
//                System.out.println("need = " + s);
                need[i] = Integer.parseInt(s);
            }

            reconstruct(capacity, 1, picked);
            System.out.println(pack(capacity, 1) + " " + picked.size());
            picked.forEach(System.out::println);
            tc--;
        }

    }

    public static int pack(int availableCapacity, int lastItems) {
        if (lastItems==n)
            return 0;
        int ret = cache[availableCapacity][lastItems];
        if (ret!=-1)
            return ret;
        // 담지 않는 경우
        ret = pack(availableCapacity, lastItems + 1);
        // 담을 수 있는 경우
        if (availableCapacity >= volume[lastItems])
            // 비교하여 큰 값 반환
            ret = Math.max(ret, pack(availableCapacity - volume[lastItems], lastItems + 1) + need[lastItems]);

        return cache[availableCapacity][lastItems] = ret;
    }

    public static void reconstruct(int availableCapacity, int lastItem, List<String> picked) {
        if (lastItem==n)
            return;

        // 안 담은 경우
        if (pack(availableCapacity, lastItem)==pack(availableCapacity, lastItem + 1)) {
            reconstruct(availableCapacity, lastItem + 1, picked);
        } else {
            picked.add(itemName[lastItem]);
            reconstruct(availableCapacity - volume[lastItem], lastItem + 1, picked);
        }
    }
}
