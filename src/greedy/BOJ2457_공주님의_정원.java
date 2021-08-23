package greedy;

import java.io.*;
import java.util.*;

public class BOJ2457_공주님의_정원 {

    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(System.in);

        int N = in.nextInt();
        Flower[] flowers = new Flower[N];

        for (int i = 0; i < N; i++) {
            int start = in.nextInt() * 100 + in.nextInt();
            int end = in.nextInt() * 100 + in.nextInt();
            flowers[i] = new Flower(start, end);
        }
        Arrays.sort(flowers);

        int last = 301, max = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int start = flowers[i].start;
            int end = flowers[i].end;
            if (last >= start) {
                max = Math.max(max, end);
            } else {
                if (max == 0) {
                    answer = 0;
                    break;
                } else {
                    last = max;
                    max = 0;
                    answer++;
                    if (last > 1130) {
                        break;
                    }
                    i--;
                }
            }
        }
        System.out.println(max != 0 ? max > 1130 ? answer + 1 : 0 : answer);
    }

    private static final class Flower implements Comparable<Flower> {
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            return Integer.compare(start, o.start);
        }
    }

    private static class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;

        public InputReader(InputStream st) {
            this.stream = st;
        }

        public int read() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

}