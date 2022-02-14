# [KCM Travel](https://www.acmicpc.net/problem/10217)

### 난이도

***
G1
<br><br>

### 알고리즘 분류

***

* 다익스트라
* 다이나믹 프로그래밍

<br><br>

### Solution

***

최단거리를 구하는 문제에서 비용이 추가된 문제이다. 가능한 비용 내에서 최단 거리를 구해야 하는 문제

비용이 존재하지 않는 다익스트라 문제에서는 `dp[v]`(보통 거리를 의미하는 `dist`로 배열명을 정했다.)배열을 이용해서 시작 지점으로 부터의 최단 거리를 갱신하는 방법을 사용했다.

이 문제에서는 **비용**이 존재하기 때문에 `dp[v][c]`(v: vertex, c:
cost)배열을 이용한다. 이 배열을 해석하자면 시작 지점에서 부터 `v`지점까지 `c`비용이 걸렸을 때의 최단 거리이다.

```java
public class Main {
    static String solve() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        que.add(new Node(1, 0, 0));
        dp[1][0] = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v][cur.c]) continue;
            visited[cur.v][cur.c] = true;

            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);
                int nextCost = cur.c + next.c;
                int nextTime = cur.t + next.t;
                if (nextCost > M) continue;

                if (!visited[next.v][nextCost] && dp[next.v][nextCost] > nextTime) {
                    que.add(new Node(next.v, nextCost, nextTime));
                    dp[next.v][nextCost] = nextTime;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= M; i++) {
            ans = Math.min(ans, dp[N][i]);
        }
        if (ans == Integer.MAX_VALUE) {
            return "Poor KCM";
        } else {
            return String.valueOf(ans);
        }
    }
}
```

`Cost`개념이 추가된것 외에 모든 로직이 기존의 다익스트라 로직과 동일하다. (사실 다익스트라 알고리즘을 완전히 이해했다면 `Cost`를 추가한 이차원 배열로 각 지점까지 최단거리를 구한다는 설명으로 이 문제는 끝난다.)       

다익스트라에서 거리 + 비용 개념이 들어간 웰노운 문제이며 비슷한 문제로는 [BOJ1884_고속도로](https://www.acmicpc.net/problem/1884) 문제가 있다. 완전히 동일한 문제이다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dijkstra/BOJ10217_KCM_Travel.java)
