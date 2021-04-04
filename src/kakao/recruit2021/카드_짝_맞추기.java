package kakao.recruit2021;

import java.util.*;
import java.util.List;

public class 카드_짝_맞추기 {
    private int startRow, startCol, cardCnt, ans = Integer.MAX_VALUE;
    private int[][] board;
    private boolean[] isExist;
    private List<List<Point>> cardPointList;

    public int solution(int[][] board, int r, int c) {
        init(board, r, c);

        for (int i = 1; i <= 6; i++) {
            if (isExist[i]) permutaion(new ArrayList<>(), new boolean[7], i);
        }
        return ans;
    }

    private void ansFunc(List<Integer> list, int listIdx, int dist, Point cur) {
        if (listIdx == list.size()) {
            ans = Math.min(ans, dist);
            return;
        }
        int[][] curBoard = new int[4][4];
        for (int i = listIdx; i < list.size(); i++) {
            Point p1 = cardPointList.get(list.get(i)).get(0);
            Point p2 = cardPointList.get(list.get(i)).get(1);
            curBoard[p1.row][p1.col] = list.get(i);
            curBoard[p2.row][p2.col] = list.get(i);
        }

        Point dest1 = cardPointList.get(list.get(listIdx)).get(0);
        Point dest2 = cardPointList.get(list.get(listIdx)).get(1);
        int dist1 = doBFS(cur, dest1, curBoard);
        int dist2 = doBFS(cur, dest2, curBoard);
        int dist12 = doBFS(dest1, dest2, curBoard);
        int dist21 = doBFS(dest2, dest1, curBoard);
        ansFunc(list, listIdx + 1, dist + dist1 + dist12 + 2, dest2);
        ansFunc(list, listIdx + 1, dist + dist2 + dist21 + 2, dest1);
    }

    private void permutaion(List<Integer> list, boolean[] visited, int cur) {
        list.add(cur);
        if (list.size() == cardCnt) {
            Point dest1 = cardPointList.get(list.get(0)).get(0);
            Point dest2 = cardPointList.get(list.get(0)).get(1);
            int dist1 = doBFS(new Point(startRow, startCol), dest1, this.board);
            int dist2 = doBFS(new Point(startRow, startCol), dest2, this.board);
            int dist12 = doBFS(dest1, dest2, this.board);
            int dist21 = doBFS(dest2, dest1, this.board);
            ansFunc(list, 1, dist1 + dist12 + 2, dest2);
            ansFunc(list, 1, dist2 + dist21 + 2, dest1);
            return;
        }
        visited[cur] = true;
        for (int i = 1; i <= 6; i++) {
            if (isExist[i] && !visited[i]) {
                permutaion(new ArrayList<>(list), visited, i);
            }
        }
        visited[cur] = false;
    }

    private int doBFS(Point start, Point target, int[][] curBoard) {
        boolean[][] visited = new boolean[4][4];
        int[] vr = {1, -1, 0, 0};
        int[] vc = {0, 0, 1, -1};
        Queue<Point> que = new LinkedList<>();
        visited[start.row][start.col] = true;
        que.add(start);

        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.isEqual(target)) {
                return cur.cnt;
            }
            // move
            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.row + vr[i], cur.col + vc[i], cur.cnt + 1);
                if (next.isInBoundary() && !visited[next.row][next.col]) {
                    visited[next.row][next.col] = true;
                    que.add(next);
                }
            }

            // ctrl move
            for (int i = 0; i < 4; i++) {
                int temp = 1;
                while (true) {
                    Point next = new Point(cur.row + vr[i] * temp,
                            cur.col + vc[i] * temp, cur.cnt + 1);
                    if (next.isInBoundary() && curBoard[next.row][next.col] > 0) {
                        if (!visited[next.row][next.col]) {
                            visited[next.row][next.col] = true;
                            que.add(next);
                        }
                        break;
                    }
                    if (!next.isInBoundary()) {
                        Point prev = new Point(next.row - vr[i],
                                next.col - vc[i], next.cnt);
                        if (!visited[prev.row][prev.col]) {
                            visited[prev.row][prev.col] = true;
                            que.add(prev);
                        }
                        break;
                    }
                    temp++;
                }
            }

        }

        return -1;
    }

    private void init(int[][] board, int r, int c) {
        this.board = board;
        this.startRow = r;
        this.startCol = c;
        this.isExist = new boolean[7];
        this.cardPointList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            cardPointList.add(new ArrayList<>());
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    if (isExist[board[i][j]]) cardCnt++;
                    isExist[board[i][j]] = true;
                    cardPointList.get(board[i][j]).add(new Point(i, j));
                }
            }
        }
    }

    private static class Point {
        int row, col, cnt;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
            this.cnt = 0;
        }

        public Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", cnt=" + cnt +
                    '}';
        }

        public boolean isInBoundary() {
            return this.row >= 0 && this.row < 4 && this.col >= 0 && this.col < 4;
        }

        public boolean isEqual(Point p) {
            return this.row == p.row && this.col == p.col;
        }
    }

}
