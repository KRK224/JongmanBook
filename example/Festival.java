package example;

import java.util.Scanner;

public class Festival {
    static int n = 0;
    static int l = 0;
    static double[][] cache;

    public static void main(String[] args) {
        // 현재는 하나의  테스트 케이스만 가능하도록 구현
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            l = sc.nextInt();
            // 크기가 n인 2차원 배열 설정
            cache = new double[n + 1][n + 1];
            int[] costPerDays = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                costPerDays[i] = sc.nextInt();
            }
            double ans = 1000000L;
            for (int j = l; j <= n; j++) {
                for (int c = 1; c <= n - j; c++) {
                    double temp = calcAverage(j, c, costPerDays);
                    ans = Math.min(ans, temp);
                }
            }
            System.out.printf("%10.7f\n", ans);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double calcAverage(int remainDays, int currentDay, int[] costPerDays) {
        if (currentDay==n || remainDays==0)
            return 0;
        if (cache[remainDays][currentDay]!=0L) {
            return cache[remainDays][currentDay];
        }

        double costAverage = (costPerDays[currentDay] + (calcAverage(remainDays - 1, currentDay + 1, costPerDays) * (remainDays - 1))) / remainDays;
        cache[remainDays][currentDay] = costAverage;
        return costAverage;
    }

}
