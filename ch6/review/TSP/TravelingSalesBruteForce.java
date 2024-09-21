package ch6.review.TSP;

import java.util.List;

public class TravelingSalesBruteForce {
    static final int MAX = 10;
    static int n = 10; // 도시의 수
    static double[][] dist = new double[MAX][MAX]; // 최대 도시 사이의 거리

    public static void main(String[] args) {

    }

    double shortestPath(List<Integer> path, List<Boolean> visited, double currentLength) {
        // base case, 모든 도시 방문 후 다시 처음 도시로 귀환
        if (path.size()==n) {
            return currentLength + dist[path.get(0)][path.get(n)];
        }

        double result = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n; ++i) {
            // 방문한 도시는 건너뛰기
            if (visited.get(i))
                continue;

            int last = path.get(path.size());
            path.add(i);
            visited.set(i, true);

            double cand = shortestPath(path, visited, currentLength + dist[i][last]);
            result = Math.min(result, cand);

            last = path.remove(path.size());
            visited.set(last, false);
        }
        return result;
    }
}
