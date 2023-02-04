package implementation;

import java.io.*;
import java.util.*;

public class BOJ1917_정육면체_전개도 {

    static List<PlanarFigure> planarFigureList;
    static int[][] arr;
    static final int PLANARFIGURELIST_SIZE = 11;
    static final int ARR_SIZE = 6;
    static final int TEST_CASES = 3;

    static String solve() {
        PlanarFigure pf = new PlanarFigure(arr);
        for (int r = 0; r < 4; r++) {
            PlanarFigure rotate = pf.rotate(r);
            for (int f = 0; f < 3; f++) {
                PlanarFigure flip = rotate.flip(f);
                for (int i = 0; i < PLANARFIGURELIST_SIZE; i++) {
                    if (flip.equals(planarFigureList.get(i))) {
                        return "yes";
                    }
                }
            }
        }
        return "no";
    }

    static PlanarFigure getPlanarFigure(int[][] arr) {
        int[] r = new int[ARR_SIZE];
        int[] c = new int[ARR_SIZE];
        for (int i = 0; i < ARR_SIZE; i++) {
            int cntR = 0;
            int cntC = 0;
            for (int j = 0; j < ARR_SIZE; j++) {
                if (arr[i][j] == 1) {
                    cntR++;
                }
                if (arr[j][i] == 1) {
                    cntC++;
                }
            }
            r[i] = cntR;
            c[i] = cntC;
        }
        return new PlanarFigure(r, c);
    }

    static void addPlanarFigureList() {
        planarFigureList = new ArrayList<>(PLANARFIGURELIST_SIZE);
        planarFigureList.add(new PlanarFigure(new int[]{3, 1, 1, 1}, new int[]{1, 4, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{2, 2, 1, 1}, new int[]{1, 4, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{2, 1, 2, 1}, new int[]{1, 4, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{2, 1, 1, 2}, new int[]{1, 4, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{1, 3, 1, 1}, new int[]{1, 4, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{1, 2, 2, 1}, new int[]{1, 4, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{1, 1, 2, 1, 1}, new int[]{3, 3}));
        planarFigureList.add(new PlanarFigure(new int[]{1, 2, 2, 1}, new int[]{2, 2, 2}));
        planarFigureList.add(new PlanarFigure(new int[]{2, 1, 2, 1}, new int[]{2, 3, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{1, 3, 1, 1}, new int[]{2, 3, 1}));
        planarFigureList.add(new PlanarFigure(new int[]{1, 2, 2, 1}, new int[]{1, 3, 2}));
    }

    static void swap(List<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }

    static class PlanarFigure {
        List<Integer> row;
        List<Integer> col;

        public PlanarFigure() {
            row = new ArrayList<>();
            col = new ArrayList<>();
        }

        public PlanarFigure(int[] r, int[] c) {
            row = new ArrayList<>(r.length);
            col = new ArrayList<>(c.length);
            for (int i = 0; i < r.length; i++) {
                if (r[i] != 0) {
                    row.add(r[i]);
                }
            }
            for (int i = 0; i < c.length; i++) {
                if (c[i] != 0) {
                    col.add(c[i]);
                }
            }
        }

        public PlanarFigure(int[][] arr) {
            row = new ArrayList<>(ARR_SIZE);
            col = new ArrayList<>(ARR_SIZE);

            for (int i = 0; i < ARR_SIZE; i++) {

                int cntR = 0;
                int cntC = 0;
                for (int j = 0; j < ARR_SIZE; j++) {
                    if (arr[i][j] == 1) {
                        cntR++;
                    }
                    if (arr[j][i] == 1) {
                        cntC++;
                    }
                }
                if (cntR != 0) {
                    row.add(cntR);
                }
                if (cntC != 0) {
                    col.add(cntC);
                }
            }
        }

        public PlanarFigure rotate(int r) {
            PlanarFigure pf = new PlanarFigure();
            if (r == 0) {
                pf.row.addAll(this.row);
                pf.col.addAll(this.col);
            } else if (r == 1) {
                pf.row.addAll(this.col);
                pf.col.addAll(this.row);
            } else if (r == 2) {
                pf.row.addAll(this.row);
                pf.col.addAll(this.col);
                for (int i = 0; i < pf.row.size() / 2; i++) {
                    swap(pf.row, i, pf.row.size() - 1 - i);
                }
                for (int i = 0; i < pf.col.size() / 2; i++) {
                    swap(pf.col, i, pf.col.size() - 1 - i);
                }
            } else if (r == 3) {
                pf.row.addAll(this.col);
                pf.col.addAll(this.row);
                for (int i = 0; i < pf.row.size() / 2; i++) {
                    swap(pf.row, i, pf.row.size() - 1 - i);
                }
                for (int i = 0; i < pf.col.size() / 2; i++) {
                    swap(pf.col, i, pf.col.size() - 1 - i);
                }
            }

            return pf;
        }

        public PlanarFigure flip(int f) {
            PlanarFigure pf = new PlanarFigure();
            if (f == 0) {
                return this;
            } else if (f == 1) {
                pf.row.addAll(this.row);
                pf.col.addAll(this.col);
                for (int i = 0; i < pf.row.size() / 2; i++) {
                    swap(pf.row, i, pf.row.size() - 1 - i);
                }
            } else if (f == 2) {
                pf.row.addAll(this.row);
                pf.col.addAll(this.col);
                for (int i = 0; i < pf.col.size() / 2; i++) {
                    swap(pf.col, i, pf.col.size() - 1 - i);
                }
            }
            return pf;
        }

        @Override
        public String toString() {
            return "PlanarFigure{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

        public boolean equals(PlanarFigure pf) {
            if (this.row.size() != pf.row.size() || this.col.size() != pf.col.size()) return false;
            for (int i = 0; i < this.row.size(); i++) {
                if (this.row.get(i) != pf.row.get(i)) {
                    return false;
                }
            }
            for (int i = 0; i < this.col.size(); i++) {
                if (this.col.get(i) != pf.col.get(i)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        addPlanarFigureList();
        for (int t = 0; t < TEST_CASES; t++) {
            arr = new int[ARR_SIZE][ARR_SIZE];
            for (int i = 0; i < ARR_SIZE; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < ARR_SIZE; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(solve()).append("\n");
        }
        System.out.print(sb);
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
