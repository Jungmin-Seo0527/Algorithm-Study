/*
    BOJ2422_한윤정이_이탈리아에_가서_아이스크림을_사먹는데
    --------------------------------------------------------------------------------------------------------------------
    문제링크

    https://www.acmicpc.net/problem/2422

    난이도 : S5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    조합을 브루트포스 식으로 풀어내려면 DFS가 BFS 보다 적합하다.
    단 금지된 조합을 체크하는 것에서 처음에 실수가 있었다.

    처음 아이디어는 만약 조합에 현재의 아이스크림을 넣을때 이 아이스크림에 대해 금지된 조합들이 존재하면 visited 에 미리 다
    체크를 해버리는 것이다.
    금지된 목록들은 ArrayList에 저장을 하여 각 단계가 끝나서 금지된 조합들의 visited 를 체크해제 하는 것도 포함시켰다.
    하지만 문제가 발생했다.

    예시를 보자
    5 3
    1 5
    2 5
    3 4

    이 예시에서 내 방식대로 하면 1을 넣은 순간 5를 visited 체크를 한다. 금지된 조합이기 때문에 미리 체크하는 것이다. 다음 단계에서
    2가 나올수 있고, 2의 금지된 조합인 5를 다시 체크한다. 그리고 다음단계인 3 혹은 4가 나올수 있을것이다. 그리고 재귀에서 다시 2로
    돌아왔고, 2로 할수 있는 조합, 즉 1 -> 2 -> x 의 조합들을 모두 만들어서 2와 금지되는 조합들을 체크를 해제시켰다.
    그리고 다시 1 로 돌아와서 조합을 만들어갔다. 여기서 문제가 생기는데 2에서 금지된 조합인 5를 해지하는 순간 5는 1과도 조합을 만들어
    낼수 없는데 금지에서 해제가 되어 버린다. 여기에서 조금 해매었다. (이건 구현의 문제가 아닌가...)

    문제를 잘 읽어보면 조합은 3개 까지만 만들면 된다. 즉 단순하게 boolean 2차원 배열로 금지된 조합들을 저장하고 각 조합에서 이전의
    조합원들과 현재 올 예정인 아이스크림의 번호와의 금지 조합 목록을 모두 확인하면 되는 문제였다.
    --------------------------------------------------------------------------------------------------------------------
 */
package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2422_한윤정이_이탈리아에_가서_아이스크림을_사먹는데 {
    static int N, M, ans;
    static int[] ret = new int[3];
    static boolean[] visited;
    static boolean[][] impossible;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            doDFS(i, 0);
        }
        System.out.println(ans);
    }

    static void doDFS(int ice, int cnt) {
        ret[cnt] = ice;
        if (cnt == 2) {
            ans++;
            return;
        }
        for (int i = ice + 1; i <= N; i++) {

            boolean flag = true;
            for (int j = 0; j <= cnt; j++) {
                if (impossible[ret[j]][i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) doDFS(i, cnt + 1);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[201];
        impossible = new boolean[201][201];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int ice1 = Integer.parseInt(st.nextToken());
            int ice2 = Integer.parseInt(st.nextToken());
            impossible[ice1][ice2] = true;
            impossible[ice2][ice1] = true;
        }
    }
}
