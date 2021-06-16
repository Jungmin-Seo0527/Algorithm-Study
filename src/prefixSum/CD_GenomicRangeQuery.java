package prefixSum;

public class CD_GenomicRangeQuery {

    int[] A, C, G, ans;
    char[] arr;

    public int[] solution(String S, int[] P, int[] Q) {
        // write your code in Java SE 8
        A = new int[S.length() + 1];
        C = new int[S.length() + 1];
        G = new int[S.length() + 1];
        ans = new int[P.length];
        arr = S.toCharArray();

        for(int i = 0; i < S.length(); i++) {
            A[i + 1] += A[i];
            C[i + 1] += C[i];
            G[i + 1] += G[i];
            char c = S.charAt(i);
            if(c == 'A') {
                A[i + 1]++;
            } else if (c == 'C') {
                C[i + 1]++;
            } else if (c == 'G') {
                G[i + 1]++;
            }
        }

        for(int i = 0; i < P.length; i++) {
            int start = P[i];
            int end = Q[i];
            int cntA = A[end + 1] - A[start];
            int cntC = C[end + 1] - C[start];
            int cntG = G[end + 1] - G[start];
            int cntT = end - start + 1 - cntA - cntC - cntG;
            // System.out.println("A="+cntA+" C="+cntC+" G="+cntG+" T="+cntT);
            ans[i] = getMin(cntA, cntC, cntG, cntT);
        }

        return ans;
    }

    private int getMin(int n1, int n2, int n3, int n4) {
        if (n1 != 0) {
            return 1;
        } else if (n2 != 0) {
            return 2;
        } else if (n3 != 0) {
            return 3;
        } else if (n4 !=0) {
            return 4;
        }
        return -1;
    }
}
