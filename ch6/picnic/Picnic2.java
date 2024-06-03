package ch6.picnic;

public class Picnic2 {
    private static int result;
    private static int caseNum = 3;

    // int n = 4;
    // int m = 6;
    // int[][] array = {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {0, 2}, {1, 3}};

    private static int n = 6;
    private static int m = 10;
    private static int[][] array = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 3, 4 },
            { 3, 5 }, { 4, 5 } };

    public static void main(String[] args) {
        boolean[] visited = new boolean[m];

        result = 0;
        comb(array, visited, 0, n / 2);
        System.out.println(result);
    }

    static void comb(int[][] arr, boolean[] visited, int start, int r) {
        if (r == 0) {
            if (validate(arr, visited))
                result++;
            return;
        } else {
            for (int i = start; i < arr.length; i++) {
                visited[i] = true;
                comb(arr, visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }

    static boolean validate(int[][] arr, boolean[] visited) {
        boolean[] check = new boolean[n];
        for (int i = 0; i < arr.length; i++) {
            if (visited[i] == true) {
                if (check[arr[i][0]] == false && check[arr[i][1]] == false) {
                    check[arr[i][0]] = true;
                    check[arr[i][1]] = true;
                } else
                    return false;
            }
        }
        return true;
    }
}