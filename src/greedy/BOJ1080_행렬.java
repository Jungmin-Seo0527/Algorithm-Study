package greedy;

import java.io.*;
import java.util.*;

// BOJ1080_행렬
// 0과 1로만 이루어진 두 행렬을 동일하게 만들기 위해 3*3 사이즈로 뒤집기를 수행할때 수행 최소값 구하기
// 0, 0부터 동일하지 않는 부분이면 3x3 뒤집기를 해준다. 그리고 뒤를 돌아보지 말아야 한다.
// 뒤집었던 부분을 다시 뒤집어야 한다는것은 결국 동일한 행렬로 만들수 없다는 의미이다.
// 단 예외처리를 해야하는 것이 있는데 사이즈가 3보다 작은 경우이다.
// 그리고 그 경우에도 처음부터 같은 행렬이라면 0을 다른 행렬이라면 1를 반환해 주어야 한다(3보다 작은 행렬이면 뒤집기 자체가 불가능)

public class BOJ1080_행렬 {
    static int N, M;
    static char[][] arr1, arr2;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        System.out.println(solve());
    }

    static int solve() {
        if (N < 3 || M < 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr1[i][j] != arr2[i][j]) return -1;
                }
            }
            return 0;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i + 2 >= N || j + 2 >= M) continue;
                if (arr1[i][j] != arr2[i][j]) {
                    flip(arr1, i, j);
                    cnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] != arr2[i][j]) return -1;
            }
        }

        return cnt;
    }

    static void flip(char[][] arr, int r, int c) {
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (arr[i][j] == '1') arr[i][j] = '0';
                else arr[i][j] = '1';
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new char[N][M];
        arr2 = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = input.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr2[i][j] = input.charAt(j);
            }
        }
    }
}

