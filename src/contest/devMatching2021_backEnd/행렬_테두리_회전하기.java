package contest.devMatching2021_backEnd;

public class 행렬_테두리_회전하기 {
    private int rowSZ, colSZ;
    private int[][] map, queries;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        init(rows, columns, queries);
        for (int i = 0; i < queries.length; i++) {
            answer[i] = findMinChanged(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
        }
        return answer;
    }

    private int findMinChanged(int r1, int c1, int r2, int c2) {
        int ret = map[r1][c1];
        int[] vr = {0, 1, 0, -1};
        int[] vc = {1, 0, -1, 0};
        int cr = r1, cc = c1;
        int[][] copy = new int[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, colSZ);
        }
        for (int i = 0; i < 4; i++) {
            while (true) {
                int nr = cr + vr[i];
                int nc = cc + vc[i];
                copy[nr][nc] = map[cr][cc];
                if (map[nr][nc] != map[cr][cc]) {
                    ret = Math.min(ret, map[nr][nc]);
                }
                cr = nr;
                cc = nc;
                if ((cr == r1 && cc == c2) || (cr == r1 && cc == c1) || (cr == r2 && cc == c1) || (cr == r2 && cc == c2)) {
                    break;
                }
            }
        }

        for (int i = 0; i < rowSZ; i++) {
            System.arraycopy(copy[i], 0, map[i], 0, colSZ);
        }

        return ret;
    }

    private void init(int row, int col, int[][] queries) {
        this.rowSZ = row;
        this.colSZ = col;
        this.queries = queries;

        map = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = i * colSZ + j + 1;
            }
        }
    }
}