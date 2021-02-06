package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1783_병든_나이트
/*
    체스판의 사이즈인 N과 M의 최대값을 보면 자연스럽게 그리디로 접근해야 한다는 생각이 든다.
    움직이는 조건이
    1. 2칸 위, 1칸 오
    2. 1칸 위, 2칸 오
    3. 1칸 아, 2칸 오
    4. 2칸 아, 1칸 오

    단 나이트는 이동방법을 모두 한번씩은 사용해야 한다.
    그러지 못하는 경우에는 제약없이 이동할수 있다.

    이 문제는 우선 모든 경우의 수를 따지면서 풀어내야 한다.
    우선 N의 값에 따라 조건을 나누어 준다.

    N이 3 보다 크지 않으면 2칸위가 불가능 하다.
    이 조건에서 N이 1인 경우에는 무조건 1이다. 움직일 수가 없다. 이 경우를 빼먹었었다.
    그 외에는 min(4, M) 이 된다. M이 4부터는 4칸 방문이 가능하고 그 미만에는 M의 사이즈만큼 방문이 가능하다

    N이 3 이상인 경우에는
    모든 이동 조건을 수행할수 있는지 여부를 조사한다. 모든 이동 조건을 수행하려면 M이 6 이상이어야 한다.
    M이 6 이하인 경우에는 min(M, 4) 이다.
    그것이 아니라면 모든 이동 조건을 수행한 후 M 끝까지 갈때 방문 칸수인 M-6+4 를 출력해 준다.
    -6 은 모든 이동 조건을 만족할때 오른쪽으로 이동하는 칸수, 4 는 모든 이동 조건을 수행할 때 방문하는 칸수이다.

     이 문제는 직접 그림을 그려가면서 푸는것을 추천한다.
     모든 조건을 찾아내는것이 쉬운듯 하면서 한가지를 생각하지 못하면 늪에 빠져버리는 문제
     이렇게 모든 조건을 나누는 문제를 case work 라고 하는듯 하다.

     그리고 문제 지문이 문제를 푸는 사람에게 많은 혼동을 주는것 같다.
     질문 게시판을 보니 문제 자체를 이해를 못한 사람들이 다수 있었다.
 */
public class BOJ1783_병든_나이트 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        if (N < 3) {
            if (N == 1) System.out.println(1);
            else if (M > 6) System.out.println(4);
            else System.out.println((M + 1) / 2);
        } else {
            if (M <= 6) {
                if (M >= 4) System.out.println(4);
                else System.out.println(M);
            } else {
                System.out.println(M - 2);
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
}

