package kakao.internship2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 크레인_인형뽑기_게임 {
    private int N;
    private int[] moves;
    private final List<Stack<Integer>> game = new ArrayList<>();

    public int solution(int[][] board, int[] moves) {
        init(board, moves);
        return ans();
    }

    private int ans() {
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < moves.length; i++) {
            int gameIdx = moves[i];
            Stack<Integer> g = game.get(gameIdx);
            if (g.isEmpty()) continue;
            int doll = g.pop();
            if (!stack.isEmpty() && stack.peek() == doll) {
                ret += 2;
                stack.pop();
            } else {
                stack.push(doll);
            }
        }
        return ret;
    }

    private void init(int[][] board, int[] moves) {
        N = board.length;
        for (int i = 0; i <= N; i++) {
            game.add(new Stack<>());
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                game.get(j + 1).push(board[i][j]);
            }
        }
        this.moves = moves;
    }
}
