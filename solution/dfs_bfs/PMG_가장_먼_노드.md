# [가장 먼 노드](https://programmers.co.kr/learn/courses/30/lessons/49189)

### 난이도

***
Level 3
<br><br>

### 알고리즘 분류

***

* 그래프
* BFS

<br><br>

### Solution

***

이 문제가 L3 문제라는 것에는 동의할수 없다. 너무 쉽기 때문이다. 굳이 주의할점을 꼽자면 이 문제는 트리가 아니라는 것이다. 사실 그림으로 예시를 보여주어 이러한 실수를 할 가능성은 없지만 이 기회에 트리에 대한
정확한 정의를 알고 가는것도 나쁘지 않을 것 같다.

* 트리구조
    * 그래프의 일종
    * 여러 노드가 한 노드를 가리킬 수 없는 구조
    * 회로가 없다.
    * 서로 다른 두 노드를 잇는 길이 하나뿐인 그래프

가끔씩 문제에서 트리라고 말해주지 않고 위의 정의를 조건으로 말해주는 경우가 있다. 이때 이 문제는 트리 문제로 풀어야 한다는 것을 깨달아야 한다.

다시 이 문제의 풀이 방법으로 돌아가 보자.

* 같은 깊이의 노드들을 구하기 위해 한 시점에서의 큐의 사이즈를 기록하고 그 큐의 사이즈만큼 poll을 한다. 이때 poll한 노드들이 같은 깊이의 노드들이다.
* 큐가 비워질때까지 가장 마지막의 큐의 사이즈를 기록한 값이 정답이 된다.

```java
public class Solution {
    private int ans() {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        que.add(1);
        int queSZ = que.size();
        int ret = que.size();
        while (!que.isEmpty()) {
            ret = que.size();
            while (queSZ != 0) {
                int cur = que.poll();
                queSZ--;
                for (int i = 0; i < adjList.get(cur).size(); i++) {
                    int next = adjList.get(cur).get(i);
                    if (!visited[next]) {
                        visited[next] = true;
                        que.add(next);
                    }
                }
            }
            queSZ = que.size();
        }
        return ret;
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dfs_bfs/PGM_가장_먼_노드.java)
