# [기능개발](https://programmers.co.kr/learn/courses/30/lessons/42586)

### 난이도

***
Level 2
<br><br>

### 알고리즘 분류

***

* 자료구조
* 스택, 큐

<br><br>

### Solution

***

자료구조만 알고 있으면 쉽게 풀수 있는 문제였다. 우선 각 기능마다 필요한 기간들을 구해준다. 여기서 많은 사람들이 소수점으로 계산하고 반올림을 통해 필요한 기간을 구한다. 하지만 많은 알고리즘 문제를 풀어본 결과
float나 double을 그대로 연산에 이용하는 것은 위험하다. 그래서 나는 처음부터 나머지가 있는 경우 +1 을 하는 방법으로 남은 기간을 구했다. (실제로 질문하기 게시판에 많은 사람들이 질문한 요소이다.)

나머지는 쉽다. stack에 남은 기간을 넣어두는데 만약 top보다 현재 넣으려는 기능의 남은 기간이 더 짧은 경우에는 ans의 top에 +1을 하는 방법으로 구현했다

```java
public class Solution {
    private Stack<Integer> stack = new Stack<>();
    private Deque<Integer> ans = new LinkedList<>();

    public int[] solution(int[] progresses, int[] speeds) {
        for (int i = 0; i < day.length; i++) {
            if (stack.isEmpty() || stack.peek() < day[i]) {
                stack.push(day[i]);
                ans.addLast(1);
            } else {
                int lastAns = ans.pollLast();
                ans.addLast(lastAns + 1);
            }
        }
    }
}
```

`ans`는 `Deque`를 선택했다. 해당 배포일에 기능 갯수를 계속 추가하려면 마지막 인덱스에서 추가를, 그리고 마지막에 답을 출력할때는 처음 인덱스부터 추가를 해야 하기 때문에 양쪽 모두 컨트롤이
가능한 `Deque`를 선택했다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/ds/PGM_기능개발.java)
