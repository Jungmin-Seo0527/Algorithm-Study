package kakao.internship2020;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 경주로_건설 {
    private int N;
    private int[][] board;
    private int[] vc = {1, -1, 0, 0};
    private int[] vr = {0, 0, 1, -1};

    public int solution(int[][] board) {
        init(board);
        return ans();
    }

    private int ans() {
        PriorityQueue<Position> que = new PriorityQueue<>();
        int[][] visited = new int[N * N][N * N];
        for (int i = 0; i < N * N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        int ret = Integer.MAX_VALUE;
        Position start = new Position(new Point(0, 0), new Point(0, 0), 0);
        que.add(start);
        visited[0][0] = 0;
        while (!que.isEmpty()) {
            Position cur = que.poll();
            if (cur.cur.row == N - 1 && cur.cur.col == N - 1) {
                return cur.weight;
            }
            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.cur.row + vr[i], cur.cur.col + vc[i]);
                if (checkPoint(next)) {
                    int weight = 100;
                    if (isCorner(cur.prev, next)) {
                        weight = 600;
                    }
                    if (visited[next.row * N + next.col][cur.prev.row * N + cur.prev.col] >= weight + cur.weight) {
                        visited[next.row * N + next.col][cur.prev.row * N + cur.prev.col] = weight + cur.weight;
                        que.add(new Position(next, cur.cur, weight + cur.weight));
                    }
                }
            }
        }
        return ret;
    }

    private boolean checkPoint(Point point) {
        return point.row >= 0 && point.row < N && point.col >= 0 && point.col < N && board[point.row][point.col] == 0;
    }

    private boolean isCorner(Point prev, Point next) {
        return prev.row != next.row && prev.col != next.col;
    }

    private void init(int[][] board) {
        this.board = board;
        this.N = board.length;
    }

    private static class Position implements Comparable<Position> {
        Point cur, prev;
        int weight;

        public Position(Point cur, Point prev, int weight) {
            this.cur = cur;
            this.prev = prev;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "cur=" + cur.toString() +
                    ", prev=" + prev.toString() +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Position o) {
            return this.weight - o.weight;
        }
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

}