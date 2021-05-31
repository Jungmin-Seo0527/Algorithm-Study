# [Two Dots](https://www.acmicpc.net/problem/16929)

### 난이도

***
G4
<br><br>

### 알고리즘 분류

***

* 그래프
* 깊이 우선 탐색

<br><br>

### Solution

***

이 문제는 각 지점에서 `DFS`를 수행해서 `O(N^2)` 시간 복잡도로도 풀어낼 수 있다.(시간 제한이 2초이다.) 하지만 `O(N)`으로 풀수 있으므로 이 풀이를 소개한다.

* `DFS`를 수행하는 과정에서 이미 방문한 지점을 다시 방문하게 되는 경우 사이클이 형성된다. **시작 지점으로 다시 돌아오지 않아도 된다.**
    * 단 해당 지점에서 시작해서 다시 해당 지점으로 돌아올 때 최소한 점 4개를 지나야 한다. (두점 사이를 왕복해서 다시 돌아오는 경우는 제외해야 한다.)
    * 왕복해서 다시 돌아오는 경우를 제외해야 하기 때문에 `DFS`메소드 파라미터에 이전 지점을 넘겨주어서 현재 지점을 기준으로 이전 지점과 다음 지점이 같은 경우에는 메소드를 진행하지 않도록 하였다.

```java
public class Main {
    boolean doDFS(int start, int prev, int cur) {
        int cr = cur / colSZ;
        int cc = cur % colSZ;
        visited[cr][cc] = true;
        for (int i = 0; i < 4; i++) {
            int nr = cr + vr[i];
            int nc = cc + vc[i];
            int next = nr * colSZ + nc;
            if (!checkBoundary(nr, nc) || prev == next || map[start / colSZ][start % colSZ] != map[nr][nc]) {
                continue;
            }
            if (visited[nr][nc]) {
                return true;
            }
            if (!visited[nr][nc] && doDFS(start, cur, next)) {
                return true;
            }
        }
        return false;
    }
}
```

* 한번의 DFS를 수행하고 방문 이력이 있는 지점은 더이상 방문하지 않음으로써 `O(N)`으로 문제를 풀어낼 수 있다.


<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dfs_bfs/BOJ16929_Two_Dots.java)
