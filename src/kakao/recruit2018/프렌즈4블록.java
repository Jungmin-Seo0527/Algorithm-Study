package kakao.recruit2018;

import java.util.Arrays;

public class 프렌즈4블록 {
    private int rowSZ, colSZ;
    char[][] board;

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        init(m, n, board);

        while (true) {
            int removeBlockCnt = removeBlock();
            if (removeBlockCnt == 0) break;
            answer += removeBlockCnt;
            downBlock();
        }
        return answer;
    }

    private int removeBlock() {
        int ret = 0;
        boolean[][] removedBlock = new boolean[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (this.board[i][j] == '@') continue;
                eraseBlocks(i, j, removedBlock);
            }
        }

        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (removedBlock[i][j]) {
                    ret++;
                    this.board[i][j] = '@';
                }
            }
        }
        return ret;
    }

    private void eraseBlocks(int r, int c, boolean[][] removedBlock) {
        char b = this.board[r][c];
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (r + i >= rowSZ || c + j >= colSZ || this.board[r + i][c + j] != b) {
                    return;
                }
            }
        }
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                removedBlock[r + i][c + j] = true;
            }
        }
    }

    private void downBlock() {
        for (int i = rowSZ - 1; i >= 0; i--) {
            for (int j = 0; j < colSZ; j++) {
                if (this.board[i][j] == '@') continue;
                char curBlock = this.board[i][j];
                int row = i;
                while (row + 1 < rowSZ && this.board[row + 1][j] == '@') {
                    row++;
                }
                this.board[i][j] = '@';
                this.board[row][j] = curBlock;
            }
        }
    }

    private void init(int m, int n, String[] board) {
        this.rowSZ = m;
        this.colSZ = n;
        this.board = new char[rowSZ][colSZ];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = board[i].toCharArray();
        }
    }

    private void showCharArr(char[][] arr) {
        for (char[] chars : arr) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println();
    }
}