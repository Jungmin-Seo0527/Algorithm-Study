# [Maaaaaaaaaze](https://www.acmicpc.net/problem/16985)

### 난이도

***
G3
<br><br>

### 알고리즘 분류

***

* 너비 우선 탐색
* 깊이 우선 탐색

<br><br>

### Solution

***

DFS와 BFS를 짬뽕시켜서 풀어야 하는 문제이다. 5개의 판을 놓는 순서, 그리고 회전의 경우를 dfs로 풀고, 시작점에서 목표점까지의 최단 거리를 bfs로 풀수 있다.

1. 판을 놓는 순서(DFS)

```java
public class Main {
    static void dfs0(int idx) {
        if (idx == 5) {
            dfs(0);
            return;
        }
        for (int i = 0; i < heiSZ; i++) {
            if (!visitedMap[i]) {
                visitedMap[i] = true;
                copy2Map(inputMap[i], map[idx]);
                dfs0(idx + 1);
                visitedMap[i] = false;
            }
        }
    }
}
```

* 단순한 permutation을 구하면 된다.
* 모든 판을 위치했다면 회전에 대한 모든 조합을 구한다.

2. 판의 회전 (DFS)

```java
public class Main {
    static void dfs(int curHeight) {
        if (curHeight == heiSZ) {
            int d = bfs();
            if (d != -1) {
                ans = Math.min(ans, d);
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            rotateMap(map[curHeight]);
            dfs(curHeight + 1);
        }
    }
}
```

각 판을 90도씩 회전시켜서 모든 경우의 수를 찾는다. 각 경우마다 시작지점 -> 목표지점까지의 최단 거리를 구한다.

3. 시작 지점 -> 목표 지점 (BFS)

```java
public class Main {
    static int bfs() {
        int ret = -1;
        if (map[0][0][0] == 0) {
            return ret;
        }
        boolean[][][] visited = new boolean[heiSZ][rowSZ][colSZ];
        Queue<Point> que = new LinkedList<>();
        visited[0][0][0] = true;
        que.add(new Point(0, 0, 0, 0));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.h == heiSZ - 1 && cur.r == rowSZ - 1 && cur.c == colSZ - 1) {
                ret = cur.d;
                break;

            }
            for (int i = 0; i < 6; i++) {
                int nh = cur.h + vh[i];
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nh, nr, nc) && map[nh][nr][nc] == 1 && !visited[nh][nr][nc]) {
                    visited[nh][nr][nc] = true;
                    que.add(new Point(nh, nr, nc, cur.d + 1));
                }
            }
        }
        return ret;
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dfs_bfs/BOJ16985_Maaaaaaaaaze.java)
