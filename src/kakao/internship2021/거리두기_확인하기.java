package kakao.internship2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class 거리두기_확인하기 {
    public int[] solution(String[][] places) {
        return Arrays.stream(places)
                .map(this::solve)
                .collect(Collectors.toCollection(() -> new ArrayList<>(places.length)))
                .stream()
                .mapToInt(an -> an)
                .toArray();
    }

    private int solve(String[] place) {
        int mapSize = 5;
        char[][] map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            map[i] = place[i].toCharArray();
        }
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == 'P') {
                    boolean ret = bfs(map, mapSize, new Point(i, j));
                    if (!ret) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    private boolean bfs(char[][] map, int size, Point start) {
        boolean[][] visited = new boolean[size][size];
        Queue<Point> que = new LinkedList<>();
        int[] vr = {1, -1, 0, 0};
        int[] vc = {0, 0, 1, -1};

        que.add(start);
        visited[start.r][start.c] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc] && cur.d + 1 <= 2) {
                    if (map[nr][nc] == 'P') {
                        return false;
                    } else if (map[nr][nc] == 'X') {
                        continue;
                    }
                    Point next = new Point(nr, nc, cur.d + 1);
                    que.add(next);
                    visited[next.r][next.c] = true;
                }
            }
        }
        return true;
    }

    private boolean checkBoundary(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    private static class Point {
        int r, c, d;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}