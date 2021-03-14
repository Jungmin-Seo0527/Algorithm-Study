/*
    BOJ13397_구간_나누기_2
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/status?user_id=soato1405&problem_id=13397&from_mine=1

    난이도 : G4
    --------------------------------------------------------------------------------------------------------------------
    풀이

    매개 변수 탐색 (parametric search)을 이용해야 하는 문제
    이분 탐색 문제중에 많이 등장하는 유형인데 특징이 처음 문제를 접할때 풀이법이 전혀 생각나지 않는다는 것이다.
    쉬운 문제로 입국심사 문제가 있다. 아마 이 문제가 가장 대표적일 것이다.

    이 문제의 관건은 어떤것을 탐색의 대상으로 두어야 하는가 이다. 대부분의 매개 변수 탐색 문제의 키포인트이다.
    우선 이분탐색은 오름차순으로 정렬되어 있는 숫자들중 특정 숫자를 찾는 시간을 비약적으로 줄이는 기법이다. 오름차순 정렬이 전제되어야
    하는데, 이 문제는 어떤것을 오름차순으로 정렬할지, 어떤것을 탐색 대상으로 삼아야 하는지 정해야 한다.
    결론적으로 구간 점수의 최대값의 최솟값을 탐색 대상으로 해야 한다.

    주어진 배열에서 나올수 있는 구간의 점수의 최대값의 최대값을 end 값으로 설정한다. (start = 0)
    특정 구간의 점수의 최댓값의 최소값을 임의로 정했을때 (mid = start + end >>>1 ) 이때 배열이 몇개의 그룹으로 나누어 지는지
    알아본다. 그리고 그 그룹의 갯수와 M 값을 비교하여 start, end 값을 재설정 한다. (이분 탐색)

    매개 변수 탐색은 두가지를 조심하자. 어떠한 것을 탐색의 대상으로 삼을 것인가, start, end 값의 조정 기준은 무엇인가?
    입국심사 문제처럼 처음에는 어렵게 느껴졌던 문제지만 풀이법을 보면 별거 아니었던 문제였다.
    --------------------------------------------------------------------------------------------------------------------
 */
package BinarySearch;

import java.io.*;
import java.util.*;

public class BOJ13397_구간_나누기_2 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(int n) {
        int start = 0;
        int end = n;
        while (start < end) {
            int mid = (start + end) >>> 1;
            int cnt = grouping(mid);
            if (cnt <= M) end = mid;
            else start = mid + 1;
        }
        System.out.println(end);
    }

    static int grouping(int n) {
        int max = arr[0];
        int min = arr[0];
        int ret = 1;
        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if (max - min > n) {
                ret++;
                max = arr[i];
                min = arr[i];
            }
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        solve(max - min);
    }
}
