package kakao.internship2020;

public class 키패드_누르기 {
    private int[] numbers;
    private Point[] numbersPoint;
    private char[][] board;
    private String hand;
    private Point right, left;

    public String solution(int[] numbers, String hand) {
        StringBuilder ans = new StringBuilder();
        init(numbers, hand);
        for (int i = 0; i < numbers.length; i++) {
            int curNum = numbers[i];
            // System.out.println(curNum);
            if (curNum == 1 || curNum == 4 || curNum == 7) {
                ans.append('L');
                left = numbersPoint[curNum];
            } else if (curNum == 3 || curNum == 6 || curNum == 9) {
                ans.append('R');
                right = numbersPoint[curNum];
            } else {
                Point cur = numbersPoint[curNum];
                int rightDist = Math.abs(cur.row - right.row) + Math.abs(cur.col - right.col);
                int leftDist = Math.abs(cur.row - left.row) + Math.abs(cur.col - left.col);
                if (rightDist > leftDist) {
                    ans.append('L');
                    left = numbersPoint[curNum];
                } else if (rightDist < leftDist) {
                    ans.append('R');
                    right = numbersPoint[curNum];
                } else {
                    if (hand.equals("right")) {
                        ans.append('R');
                        right = numbersPoint[curNum];
                    } else {
                        ans.append('L');
                        left = numbersPoint[curNum];
                    }
                }
            }
        }
        return ans.toString();
    }

    private void init(int[] numbers, String hand) {
        this.numbers = numbers;
        this.hand = hand;
        this.board = new char[4][3];
        this.numbersPoint = new Point[10];

        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = (char) (num + '0');
                this.numbersPoint[num++] = new Point(i, j);
            }
        }
        board[3][0] = '*';
        board[3][1] = '0';
        board[3][2] = '#';
        this.numbersPoint[0] = new Point(3, 1);
        right = new Point(3, 0);
        left = new Point(3, 2);

    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}