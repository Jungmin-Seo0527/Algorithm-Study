package TwoPointer;

import java.io.*;
import java.util.*;

// BOJ1450_냅색문제
// 조건에서 주어진 무게보다 적게 물건을 담을 수 있는 모든 방법의 수를 구하는 문제
// 물건의 갯수(N) <= 30, 가방이 버틸수 있는 무게 (C) <=1e9 (정수) , 물건의 무게 <= 1e9 (자연수)
// 처음부터 모든 물건들의 부분수열의 합을 구하려고 하면 시간초과 혹은 메모리 초과
// 주어진 물건들을 2개의 집합으로 나누어서 따로따로 부분집합의 합들을 구해준다.
// 단 아무것도 넣지 않는 경우도 1번으로 치기때문에 각각의 부분집합의 합의 list에 0도 넣어준다
// 각 list를 오름차순으로 정렬후 두포인터를 이용해 두 list의 값의 합이 <= C 가 되는 경우의 갯수를 세준다.
// 각 인덱스를 listLeft의 시작점, listRight의 끝점에 두고 시작하여 그때의 두 list값의 합이 <=C 가 되면
// right+1 값이 left 때의 listleft+listRight <= C 인 모든 경우의 갯수이다 (둘다 오름차순으로 정렬되어 있기 때문에)
public class BOJ1450_냅색문제 {
    static int N, C;
    static int[] arr;

    static List<Integer> listLeft = new ArrayList<>();
    static List<Integer> listRight = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        listLeft.add(0);
        listRight.add(0);

        for (int i = 0; i < N / 2; i++) {
            doDFS(i, arr[i], N / 2);
        }
        for (int i = N / 2; i < N; i++) {
            doDFS(i, arr[i], N);
        }
        Collections.sort(listLeft);
        Collections.sort(listRight);
        System.out.println(countAns());
    }

    static void doDFS(int idx, int sum, int end) {
        if (end == N / 2) listLeft.add(sum);
        else listRight.add(sum);
        if (sum == C) return;

        for (int i = idx + 1; i < end; i++) {
            if (sum + arr[i] <= C) doDFS(i, sum + arr[i], end);
        }
    }

    static int countAns() {
        int cnt = 0;
        int left = 0;
        int right = listRight.size() - 1;
        int leftSZ = listLeft.size();

        while (left < leftSZ && right >= 0) {
            int sum = listLeft.get(left) + listRight.get(right);
            if (sum <= C) {
                cnt += (right + 1);
                left++;
            } else right--;
        }
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}

