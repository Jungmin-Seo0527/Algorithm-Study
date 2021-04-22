package greedy;

import java.io.*;
import java.util.*;

// BOJ13305_주유소
// 도시간 이동거리(기름은 1KM당 1L를 소모), 리터당 기름값이 주어졌을때 최소비용으로 제일 왼쪽 도시에서 오른쪽 도시로 이동할때의 비용 구하기
// 현재 위치한 도시에서 나보다 기름값이 낮은 도시로 갈때까지 소요되는 기름을 현재 도시에서 채운후 그 도시까지 이동
// 자료형은 long
public class BOJ13305_주유소 {
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    // i: 현재 위치한 도시 j: 나보다 기름값이 적은 도시
    static void solve() {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            long len = 0;
            long oil = (long) arr[i][1];
            int j = i + 1;
            for (; j < N; j++) {
                len += (long) arr[j][0];
                if (oil > arr[j][1]) {
                    break;
                }
            }
            sum += oil * len;
            i = j - 1;
        }
        System.out.println(sum);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}

