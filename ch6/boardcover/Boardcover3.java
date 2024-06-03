package ch6.boardcover;

// 찬욱님 코드
public class Boardcover3 {
    private static int H = 3;
    private static int W = 7;
    private static int[][] board = { { 1, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 0, 0, 1, 1, 1 } };
    private static int ret = 0;

    public static void main(String[] args) {
        int[][] visited = board;

        printVisited(visited, 0);
        fill(visited, 0);

        System.out.println("result : " + ret);
    }

    private static void fill(int[][] visited, int depth) {
        int[] first = findFirst(visited);
        System.out.println("************************");
        System.out.println(first[0] + " " + first[1]);
        printVisited(visited, depth);
        if (first[0] == -1) {
            ret++;
            return;
        } else {
            int[] checkResult = check(visited, first);

            if (checkResult[0] == 1) {
                visited[first[0]][first[1]] = 1;
                visited[first[0]][first[1] + 1] = 1;
                visited[first[0] + 1][first[1] + 1] = 1;
                depth++;
                fill(visited, depth);
                depth--;
                visited[first[0]][first[1]] = 0;
                visited[first[0]][first[1] + 1] = 0;
                visited[first[0] + 1][first[1] + 1] = 0;
            }
            if (checkResult[1] == 1) {
                visited[first[0]][first[1]] = 1;
                visited[first[0]][first[1] + 1] = 1;
                visited[first[0] + 1][first[1]] = 1;
                depth++;
                fill(visited, depth);
                depth--;
                visited[first[0]][first[1]] = 0;
                visited[first[0]][first[1] + 1] = 0;
                visited[first[0] + 1][first[1]] = 0;
            }
            if (checkResult[2] == 1) {
                visited[first[0]][first[1]] = 1;
                visited[first[0] + 1][first[1]] = 1;
                visited[first[0] + 1][first[1] + 1] = 1;
                depth++;
                fill(visited, depth);
                depth--;
                visited[first[0]][first[1]] = 0;
                visited[first[0] + 1][first[1]] = 0;
                visited[first[0] + 1][first[1] + 1] = 0;
            }
            if (checkResult[3] == 1) {
                visited[first[0]][first[1]] = 1;
                visited[first[0] + 1][first[1]] = 1;
                visited[first[0] + 1][first[1] - 1] = 1;
                depth++;
                fill(visited, depth);
                depth--;
                visited[first[0]][first[1]] = 0;
                visited[first[0] + 1][first[1]] = 0;
                visited[first[0] + 1][first[1] - 1] = 0;
            }
        }
        return;
    }

    private static int[] check(int[][] visited, int[] first) {
        int[] result = { 0, 0, 0, 0 };

        if (first[0] < H - 1 && first[1] < W - 1) {
            if (visited[first[0]][first[1]] == 0 && visited[first[0]][first[1] + 1] == 0
                    && visited[first[0] + 1][first[1] + 1] == 0)
                result[0] = 1;
            if (visited[first[0]][first[1]] == 0 && visited[first[0]][first[1] + 1] == 0
                    && visited[first[0] + 1][first[1]] == 0)
                result[1] = 1;
            if (visited[first[0]][first[1]] == 0 && visited[first[0] + 1][first[1]] == 0
                    && visited[first[0] + 1][first[1] + 1] == 0)
                result[2] = 1;
        }
        if (first[0] < H - 1 && first[1] > 0) {
            if (visited[first[0]][first[1]] == 0 && visited[first[0] + 1][first[1]] == 0
                    && visited[first[0] + 1][first[1] - 1] == 0)
                result[3] = 1;
        }

        return result;
    }

    private static int[] findFirst(int[][] visited) {
        int[] result = { -1, -1 };

        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++) {
                if (visited[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        return result;
    }

    private static void printVisited(int[][] visited, int depth) {
        for (int i = 0; i < H; i++) {
            for (int k = 0; k < depth; k++)
                System.out.print("\t");
            for (int j = 0; j < W; j++)
                System.out.print(visited[i][j]);
            System.out.println();
        }
    }
}