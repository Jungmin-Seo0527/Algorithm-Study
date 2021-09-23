# [Gaaaaaaaaaarden](https://www.acmicpc.net/problem/18809)

### 난이도

***
G1
<br><br>

### 알고리즘 분류

***

* 구현
* 너비 우선 탐색
* 깊이 우선 탐색

<br><br>

### Solution

***

카카오 스러운 문제였다. 테케도 많이 주어졌고 생각지 못한 함정을 숨겨두기 보다는 알고리즘 기본 구현 능력을 묻는 문제였다. 특히 DFS, BFS를 혼합해서 푸는 문제이다. 단지 BFS에서 최단거리가 아닌 영역
확장인 점을 주의해야
한다. [참고 문제 - 확장 게임](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/solution/dfs_bfs/BOJ16920_확장_게임.md)

우선 이 문제는 2가지 로직이 필요하다.

1. 배양액을 뿌릴 수 있는 영역들 중 초록과 빨강색 배양액을 갯수에 맞게 분배 - DFS
2. 초록색과 빨간색 배양액 영역 확장 - BFS

#### 1. 배양액 분배

간단한 조합 문제이다. (순서가 의미가 없음) DFS를 이용해서 가능한 모든 경우를 확인한다. 여기서 경우는 초록색 배양액 투척, 빨간색 배양액 투척, 배양액 투척 안함 총 3가지가 존재한다. (배양액을 뿌릴 수
있는 영토가 초록색, 빨간색 배양액의 갯수 이상이다.)

```java
public class Main {
    static void dfs(List<Point> green, List<Point> red, int curIdx) {
        if (green.size() == gCnt && red.size() == rCnt) {
            int[][] tempMap = makeMap(green, red);
            ans = Math.max(ans, bfs(tempMap, green, red));
            return;
        }
        if (curIdx == brown.size()) {
            return;
        }

        List<Point> nextGreen = new ArrayList<>(green);
        List<Point> nextRed = new ArrayList<>(red);
        nextGreen.add(brown.get(curIdx));
        nextRed.add(brown.get(curIdx));
        
        if (nextGreen.size() <= gCnt) dfs(nextGreen, red, curIdx + 1);
        if (nextRed.size() <= rCnt) dfs(green, nextRed, curIdx + 1);
        dfs(green, red, curIdx + 1);
    }
}
```

참고로 사전 작업으로 배양액을 뿌릴 수 있는 영토에 대한 좌표는 따로 뽑아 두었다.(`list`) 그리고 각 배양액 색의 갯수를 주의해서 DFS를 수행해야 한다.

#### 2. 배양액 확장

확장에 대한 개념은 링크를 걸어둔 확장 게임 문제에서 확실하게 다졌기에 이 문제는 어렵지 않게 풀었다. 또한 같은 시간에 대해 같은 영역으로 확장하는 경우 꽃을 발화하고 꽃은 확장을 하지 않음이 조건에 있기에 꽃이
발화한 위치, 발화한 시간의 배열을 만들어서 체크했다.

확장의 경우에는 3가지 경우의 수가 존재한다.(현재 배양액 = `cur`, 확장할 영역의 배양액 = `next`)

1. `next`의 색이 빨간색인데 해당 위치에 초록색의 배양액이 존재하고 `next`의 시간과 같은 시간에 확장된 영역이며, 꽃이 아직 발화하지 않은 경우 -> 꽃 발화
2. `next`의 색이 초록색인대 해당 위치에 빨간색의 배양액이 존재하고 `next`의 사간과 같은 시간에 확장된 영역이며, 꽃이 아직 발화하지 않은 경우 -> 꽃 발화 (1번 경우와 색만 바뀐 경우이다.)
3. `next`의 영역이 호수가 아닌 토양(배양액이 존재하지 않음)

3가지 경우의 수를 순서대로 조건문으로 표현한 코드이다.

```java
public class Main {
    public int bfs() {
        // ...
        if (checkBoundary(nr, nc) && map[nr][nc] != 0) {
            if (map[nr][nc] == GREEN && cur.color == RED
                    && time[nr][nc] == cur.time + 1 && !flower[nr][nc]) {
                flower[nr][nc] = true;
                cnt++;
            } else if (map[nr][nc] == RED && cur.color == GREEN
                    && time[nr][nc] == cur.time + 1 && !flower[nr][nc]) {
                flower[nr][nc] = true;
                cnt++;
            } else if (map[nr][nc] == 1 || map[nr][nc] == 2) {
                time[nr][nc] = cur.time + 1;
                Point next = new Point(nr, nc, cur.time + 1, cur.color);
                map[nr][nc] = cur.color;
                que.add(next);
            }
        }
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dfs_bfs/BOJ18809_Gaaaaaaaaaarden.java)
