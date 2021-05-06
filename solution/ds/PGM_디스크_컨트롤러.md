# [디스크 컨트롤러](https://programmers.co.kr/learn/courses/30/lessons/42627)

### 난이도

***
Level 3
<br><br>

### 알고리즘 분류

***

* 우선순위 큐
* 정렬
* 그리디

<br><br>

### Solution

***

시작시간과 처리시간이 주어지는 작업들에 대해 모든 작업을 처리하기 위해 필요한 최소 시간을 구하는 문제이다.    
시작시간 순으로 작업들을 정렬한 후에 처리시간이 짧은 작업들 순으로 처리하면 처리시간을 최소로 줄일 수 있다.

* 시작시간에 대해 정렬한다.
* 현재 시간에 처리 가능한 작업들을 큐에 저장한다.
    * 큐는 우선순위 큐이며 처리시간이 짧은 작업을 우선적으로 poll 한다.
* 만약 큐가 비어있다면, 즉 현재 시간에 어떠한 작업도 처리할수 없는 경우에는 조건에 주어진대로 가장 먼저 들어온 작업을 수행해야 한다. 따라서 현재 시간을 바로 다음순번의 작업의 시작시간으로 설정하고 다시 위의
  작업을 수행하면 그 다움순번의 작업만 큐에 저장되어서 작업을 수행할 수 있다.
* 큐에 있는 작업들중 우선순위가 가장 높은 작업을 poll 시킨다. 이 작업은 큐에 있는 작업들중 처리시간이 가장 짧은 작업이다. 이 작업을 기준으로 누적 작업시간과 현재 시간을 갱신해준다.

```java
public class Solution {
    private int ans(int[][] jobs) {
        int ret = 0;
        int curTime = 0;
        int jobIdx = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        while (jobIdx < jobs.length || !que.isEmpty()) {
            while (jobIdx < jobs.length && curTime >= jobs[jobIdx][0]) {
                que.add(jobs[jobIdx++]);
            }
            if (que.isEmpty()) {
                curTime = jobs[jobIdx][0];
            } else {
                int[] cur = que.poll();
                curTime += cur[1];
                ret += (curTime - cur[0]);
            }
        }
        return ret / jobs.length;
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/ds/PGM_디스크_컨트롤러.java)
