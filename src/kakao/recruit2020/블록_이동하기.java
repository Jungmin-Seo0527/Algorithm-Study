package kakao.recruit2020;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 블록_이동하기 {
    private int[][] board;
    private final Queue<Robot> que = new LinkedList<>();
    private final Set<String> set = new HashSet<>();

    public int solution(int[][] board) {
        init(board);

        return doBFS();
    }

    private int doBFS() {
        Point endPoint = new Point(board.length - 1, board.length - 1);

        que.add(new Robot(new Point(0, 0), new Point(0, 1)));
        assert que.peek() != null;
        set.add(que.peek().toString());
        while (!que.isEmpty()) {
            Robot cur = que.poll();
            if (cur.p1.isEqual(endPoint) || cur.p2.isEqual(endPoint)) {
                return cur.moveCnt;
            }
            horizontalRightUp(cur);
            horizontalRightDown(cur);
            horizontalLeftUp(cur);
            horizontalLeftDown(cur);
            verticalLeftDown(cur);
            verticalLeftUp(cur);
            verticalRightDown(cur);
            verticalRightUp(cur);
            moveStraight(cur);

        }

        return -1;
    }

    private void moveStraight(Robot robot) {
        int[] vr = {1, -1, 0, 0};
        int[] vc = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            Point nextP1 = new Point(robot.p1.row + vr[i], robot.p1.col + vc[i]);
            Point nextP2 = new Point(robot.p2.row + vr[i], robot.p2.col + vc[i]);
            if (boundaryCheck(nextP1) && boundaryCheck(nextP2) && obstacleCheck(nextP1) && obstacleCheck(nextP2)) {
                Robot next = new Robot(nextP1, nextP2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void horizontalRightUp(Robot robot) {
        if (isHorizontalRobot(robot)) {
            Point nextP1 = new Point(robot.p2.row - 1, robot.p2.col);
            if (boundaryCheck(nextP1) && obstacleCheck(nextP1) && board[nextP1.row][robot.p1.col] == 0) {
                Robot next = new Robot(nextP1, robot.p2);
                next.moveCnt = robot.moveCnt + 1;
                if (!set.contains(next.toString())) {
                    set.add(next.toString());
                    que.add(next);
                }
            }
        }
    }

    private void horizontalRightDown(Robot robot) {
        if (isHorizontalRobot(robot)) {
            Point nextP1 = new Point(robot.p2.row + 1, robot.p2.col);
            if (boundaryCheck(nextP1) && obstacleCheck(nextP1) && board[nextP1.row][robot.p1.col] == 0) {
                Robot next = new Robot(nextP1, robot.p2);
                next.moveCnt = robot.moveCnt + 1;
                if (!set.contains(next.toString())) {
                    set.add(next.toString());
                    que.add(next);
                }
            }
        }
    }

    private void horizontalLeftUp(Robot robot) {
        if (isHorizontalRobot(robot)) {
            Point nextP2 = new Point(robot.p1.row - 1, robot.p1.col);
            if (boundaryCheck(nextP2) && obstacleCheck(nextP2) && board[nextP2.row][robot.p2.col] == 0) {
                Robot next = new Robot(robot.p1, nextP2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void horizontalLeftDown(Robot robot) {
        if (isHorizontalRobot(robot)) {
            Point nextP2 = new Point(robot.p1.row + 1, robot.p1.col);
            if (boundaryCheck(nextP2) && obstacleCheck(nextP2) && board[nextP2.row][robot.p2.col] == 0) {
                Robot next = new Robot(robot.p1, nextP2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void verticalRightDown(Robot robot) {
        if (isVerticalRobot(robot)) {
            Point nextP1 = new Point(robot.p2.row, robot.p2.col + 1);
            if (boundaryCheck(nextP1) && obstacleCheck(nextP1) && board[robot.p1.row][nextP1.col] == 0) {
                Robot next = new Robot(nextP1, robot.p2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void verticalRightUp(Robot robot) {
        if (isVerticalRobot(robot)) {
            Point nextP2 = new Point(robot.p1.row, robot.p1.col + 1);
            if (boundaryCheck(nextP2) && obstacleCheck(nextP2) && board[robot.p2.row][nextP2.col] == 0) {
                Robot next = new Robot(robot.p1, nextP2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void verticalLeftDown(Robot robot) {
        if (isVerticalRobot(robot)) {
            Point nextP1 = new Point(robot.p2.row, robot.p2.col - 1);
            if (boundaryCheck(nextP1) && obstacleCheck(nextP1) && board[robot.p1.row][nextP1.col] == 0) {
                Robot next = new Robot(nextP1, robot.p2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void verticalLeftUp(Robot robot) {
        if (isVerticalRobot(robot)) {
            Point nextP2 = new Point(robot.p1.row, robot.p1.col - 1);
            if (boundaryCheck(nextP2) && obstacleCheck(nextP2) && board[robot.p2.row][nextP2.col] == 0) {
                Robot next = new Robot(robot.p1, nextP2);
                next.moveCnt = robot.moveCnt + 1;
                addQueue(next);
            }
        }
    }

    private void addQueue(Robot robot) {
        if (!set.contains(robot.toString())) {
            set.add(robot.toString());
            que.add(robot);
        }
    }

    private boolean isHorizontalRobot(Robot robot) {
        return robot.p1.row == robot.p2.row;
    }

    private boolean isVerticalRobot(Robot robot) {
        return robot.p1.col == robot.p2.col;
    }

    private boolean obstacleCheck(Point p) {
        return board[p.row][p.col] == 0;
    }

    private boolean boundaryCheck(Point p) {
        return p.row >= 0 && p.row < board.length && p.col >= 0 && p.col < board.length;
    }

    private void init(int[][] board) {
        this.board = board;
    }

    private static class Robot {
        Point p1, p2;
        int moveCnt = 0;

        public Robot(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "p1=(" + p1.row + " " + p1.col +
                    "), p2=(" + p2.row + " " + p2.col +
                    ")}";
        }
    }

    private static class Point {
        int row, col;

        public boolean isEqual(Point p1) {
            return this.row == p1.row && this.col == p1.col;
        }

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
