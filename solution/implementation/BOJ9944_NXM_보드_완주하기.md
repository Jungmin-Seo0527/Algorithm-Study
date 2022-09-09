# [NXM 보드 완주하기](https://www.acmicpc.net/problem/9944)

### 난이도

***
G3
<br><br>

### 알고리즘 분류

***

* Implementation
* DFS
* Back tracking
* recursive

<br><br>

### Solution

***

#### 1. Back tracking

* 시작점에서 4방향에 대한 DFS를 돌리며 Back tracking을 수행

```java
public class Main {
    static int dfs(int r, int c, int cnt) {
        
        // 이동한 칸 수가 지도에서 비어있는 칸수와 같다면 0을 반환한다.
        // 즉 이동이 끝남
        if (cnt == blankCount) {
            return 0;
        }
        int ret = MAX;
        for (int v = 0; v < 4; v++) {
            int nr = r;
            int nc = c;
            int moveCnt = 0;

            // v방향으로 이동이 가능할 때 까지 이동해본다.
            while (isPossibleMove(nr + vr[v], nc + vc[v])) {
                nr += vr[v];
                nc += vc[v];
                visited[nr][nc] = true;
                moveCnt++;
            }

            // 만약 한번 이상 칸을 이동을 했다면
            if (moveCnt > 0) {
                ret = Math.min(ret, dfs(nr, nc, cnt + moveCnt) + 1);
                /*
                        +1은 이전의 DFS에서 이동 가능할 때 가지 이동후
                        다음 DFS에서는 방향을 바꾸었기 때문에 이동 횟수를 +1 해주면 된다.
                        (메소드 가장 처음에 모든 칸을 방문했을 시 0을 반환한다.
                        v의 뱡향으로 한칸이라도 움직였으니 이동횟수를 +1 해주는 것!! - recursive 함수 이해 필)
                        
                        이동한 칸 수가 아니다. 한 방향으로 이동 가능할 때 까지 이동하면 한번 이동했다고 카운트 해야 한다.
                 */
            }

            // 방문 기록 초기화
            for (int m = 0; m < moveCnt; m++) {
                visited[nr][nc] = false;
                nr -= vr[v];
                nc -= vc[v];
            }
        }
        return ret;
    }

}
```

#### 2. 주의

* 시작점에서 이동할 수 없는 경우에는 이동 횟수는 0이 된다.
* 예)
  1 1 
  .

* 시작점에서 이동은 없으며 시작하자마자 모든 칸을 방문했으니 답은 0이 되어야 한다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/implementation/BOJ9944_NXM_보드_완주하기.java)
