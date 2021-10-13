package implementation;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BOJ17470_배열_돌리기_5 {

    static int rowSZ, colSZ, opCnt, rotateCnt;
    static int[] op;
    static int[][] arr, ans;
    static List<int[][]> compArrList = new ArrayList<>(4);

    static void solve() {
        initCompressionArrList();
        for (int i = 0; i < opCnt; i++) {
            if (op[i] == 1) operator1();
            else if (op[i] == 2) operator2();
            else if (op[i] == 3) operator3();
            else if (op[i] == 4) operator4();
            else if (op[i] == 5) operator5();
            else if (op[i] == 6) operator6();
        }

        ans = getAnsArr();
        for (int i = 0; i < 4; i++) {
            int arrNum = getArrNum(compArrList.get(i));
            int[][] extArr = bfs(arrNum, compArrList.get(i));
            pushAnsArr(i, extArr);
        }
        showAns();
    }

    static void pushAnsArr(int arrNum, int[][] extArr) {
        int r = 0;
        int c = 0;
        if (arrNum == 1) {
            c = ans[0].length / 2;
        } else if (arrNum == 2) {
            r = ans.length / 2;
        } else if (arrNum == 3) {
            r = ans.length / 2;
            c = ans[0].length / 2;
        }
        for (int i = 0; i < extArr.length; i++) {
            for (int j = 0; j < extArr[0].length; j++) {
                ans[i + r][j + c] = extArr[i][j];
            }
        }
    }

    static int[][] getAnsArr() {
        if (rotateCnt % 2 == 1) return new int[colSZ][rowSZ];
        else return new int[rowSZ][colSZ];
    }

    static int getArrNum(int[][] paramArr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < paramArr.length; i++) {
            for (int j = 0; j < paramArr[0].length; j++) {
                min = Math.min(min, paramArr[i][j]);
            }
        }
        return min / 4;
    }

    static int[][] bfs(int arrNum, int[][] compArr) {
        Queue<State> que = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        int[][] extractArr = getExtractArr(arrNum);
        int[][] startCompArr = new int[2][2];
        int[][] ret = null;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                startCompArr[i][j] = i * 2 + j;
                compArr[i][j] %= 4;
            }
        }
        State start = new State(startCompArr, extractArr);
        que.add(start);
        visited.add(start);

        while (!que.isEmpty()) {
            State cur = que.poll();

            if (Arrays.deepEquals(cur.compArr, compArr)) {
                ret = cur.arr;
                break;
            }
            for (int i = 0; i < 4; i++) {
                State flipUpDownNext = cur.flipUpDown();
                State flipRightLeftNext = cur.flipRightLeft();
                State rotateNext = cur.rotateRight();
                nextStepForBfs(flipRightLeftNext, que, visited);
                nextStepForBfs(flipUpDownNext, que, visited);
                nextStepForBfs(rotateNext, que, visited);
                cur = rotateNext;
            }
        }

        return ret;
    }

    static void nextStepForBfs(State next, Queue<State> que, Set<State> visited) {
        if (!visited.contains(next)) {
            visited.add(next);
            que.add(next);
        }
    }

    static int[][] getExtractArr(int arrNum) {
        int[][] ret = new int[rowSZ / 2][colSZ / 2];
        int r = 0;
        int c = 0;
        if (arrNum == 1) {
            c = colSZ / 2;
        } else if (arrNum == 2) {
            r = rowSZ / 2;
        } else if (arrNum == 3) {
            r = rowSZ / 2;
            c = colSZ / 2;
        }
        for (int i = 0; i < rowSZ / 2; i++) {
            for (int j = 0; j < colSZ / 2; j++) {
                ret[i][j] = arr[i + r][j + c];
            }
        }
        return ret;
    }

    /**
     * 상하 반전
     */
    static void operator1() {
        for (int i = 0; i < 4; i++) {
            compArrList.set(i, flipUpDown(compArrList.get(i)));
        }
        swapCompressionArr(0, 2);
        swapCompressionArr(1, 3);
    }

    /**
     * 좌우 반전
     */
    static void operator2() {
        for (int i = 0; i < 4; i++) {
            compArrList.set(i, flipRightLeft(compArrList.get(i)));
        }
        swapCompressionArr(0, 1);
        swapCompressionArr(2, 3);
    }

    /**
     * 오른쪽 90도 회전
     */
    static void operator3() {
        rotateCnt++;
        for (int i = 0; i < 4; i++) {
            compArrList.set(i, rotateRight(compArrList.get(i)));
        }
        groupMoveRight();
    }

    /**
     * 왼쪽 90도 회전
     */
    static void operator4() {
        rotateCnt++;
        for (int i = 0; i < 4; i++) {
            compArrList.set(i, rotateLeft(compArrList.get(i)));
        }
        groupMoveLeft();
    }

    /**
     * 오른쪽 그룹 이동
     */
    static void operator5() {
        groupMoveRight();
    }

    /**
     * 왼쪽 그룹 이동
     */
    static void operator6() {
        groupMoveLeft();
    }

    static void swapCompressionArr(int idx1, int idx2) {
        int[][] temp = compArrList.get(idx1);
        compArrList.set(idx1, compArrList.get(idx2));
        compArrList.set(idx2, temp);
    }

    private static void initCompressionArrList() {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            int[][] temp = new int[2][2];
            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 2; c++) {
                    temp[r][c] = num++;
                }
            }
            compArrList.add(temp);
        }
    }

    static int[][] flipUpDown(int[][] paramArr) {
        int rs = paramArr.length;
        int cs = paramArr[0].length;
        int[][] ret = new int[rs][cs];
        for (int i = 0; i <= rs / 2; i++) {
            for (int j = 0; j < cs; j++) {
                ret[i][j] = paramArr[rs - 1 - i][j];
                ret[rs - 1 - i][j] = paramArr[i][j];
            }
        }
        return ret;
    }

    static int[][] flipRightLeft(int[][] paramArr) {
        int rs = paramArr.length;
        int cs = paramArr[0].length;
        int[][] ret = new int[rs][cs];
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j <= cs / 2; j++) {
                ret[i][j] = paramArr[i][cs - 1 - j];
                ret[i][cs - 1 - j] = paramArr[i][j];
            }
        }
        return ret;
    }

    static int[][] rotateRight(int[][] paramArr) {
        int cs = paramArr.length;
        int rs = paramArr[0].length;
        int[][] ret = new int[rs][cs];
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                ret[i][j] = paramArr[cs - 1 - j][i];
            }
        }
        return ret;
    }

    static int[][] rotateLeft(int[][] paramArr) {
        int cs = paramArr.length;
        int rs = paramArr[0].length;
        int[][] ret = new int[rs][cs];
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                ret[i][j] = paramArr[j][rs - 1 - i];
            }
        }
        return ret;
    }

    static void groupMoveRight() {
        int[][] temp = compArrList.get(0);
        compArrList.set(0, compArrList.get(2));
        compArrList.set(2, compArrList.get(3));
        compArrList.set(3, compArrList.get(1));
        compArrList.set(1, temp);
    }

    static void groupMoveLeft() {
        int[][] temp = compArrList.get(0);
        compArrList.set(0, compArrList.get(1));
        compArrList.set(1, compArrList.get(3));
        compArrList.set(3, compArrList.get(2));
        compArrList.set(2, temp);
    }

    static void showArr(int[][] arr) {
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
        System.out.println();
    }

    static void showAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static class State {
        int[][] compArr;
        int[][] arr;

        public State(int[][] compArr, int[][] arr) {
            this.compArr = compArr;
            this.arr = arr;
        }

        public State flipRightLeft() {
            int[][] ca = BOJ17470_배열_돌리기_5.flipRightLeft(compArr);
            int[][] a = BOJ17470_배열_돌리기_5.flipRightLeft(arr);
            return new State(ca, a);
        }

        public State flipUpDown() {
            int[][] ca = BOJ17470_배열_돌리기_5.flipUpDown(compArr);
            int[][] a = BOJ17470_배열_돌리기_5.flipUpDown(arr);
            return new State(ca, a);
        }

        public State rotateRight() {
            int[][] ca = BOJ17470_배열_돌리기_5.rotateRight(compArr);
            int[][] a = BOJ17470_배열_돌리기_5.rotateRight(arr);
            return new State(ca, a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.deepEquals(this.compArr, state.compArr);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(this.compArr);
        }
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        // FastReader fr = new FastReader("input/input1.txt");
        FastReader fr = new FastReader();
        rowSZ = fr.nextInt();
        colSZ = fr.nextInt();
        opCnt = fr.nextInt();
        arr = new int[rowSZ][colSZ];
        op = new int[opCnt];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        for (int i = 0; i < opCnt; i++) {
            op[i] = fr.nextInt();
        }
        solve();
    }

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}
