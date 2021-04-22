# [N으로 표현](https://programmers.co.kr/learn/courses/30/lessons/42895)

### 난이도

***
Level 3
<br><br>

### 알고리즘 분류

***

* dp
* HashSet

<br><br>

### Solution

***

문제 풀이법을 떠울리는데 쉽지 않았던 문제였다. 핵심은 Set을 이용한 dp 문제 풀이이다.     
처음에는 숫자 1부터 목표 숫자까지의 최소값들을 구하는 전형적인 dp문제로 풀려고 덤벼들었으나 해결법이 떠오르지 않았다.

이 문제는 이용하는 숫자의 갯수가 최대 7개까지 이용가능하다는 것이 중요하다. 1개로 만들수 있는 모든 숫자들을 구한 후에 1개로 만들 수 있는 숫자는 1개로 만들수 있는 숫자들간의 사칙연산으로 만들수 있는
숫자이다. 그리고 3개로 만들 수 있는 숫자들은 1개로 만들수 있는 숫자들과 2개로 만들수 있는 숫자들간의 사칙연산으로 만들 수 있는 숫자이다. 정리하자면

* n개로 만들수 있는 숫자들은 (1, n-1), (2, n-2), (3, n-3), ... (n-1, 1) 쌍의 사칙연산의 결과

```java
public class Solution {
    private void unionSet(int setIdx1, int setIdx2) {
        for (Integer e1 : dp[setIdx1]) {
            for (Integer e2 : dp[setIdx2 - setIdx1]) {
                dp[setIdx2].add(e1 + e2);
                dp[setIdx2].add(e1 - e2);
                dp[setIdx2].add(e1 * e2);
                if (e2 > 0) dp[setIdx2].add(e1 / e2);
            }
        }
    }
}
```

코드상에서는 setIdx2의 set값을 구하기 위한 연산이다.  
그리고 추가로 하나의 숫자가 연속해서 나오는 경우(55555) 같은 경우는 따로 구해서 set에 넣으면 된다.

숫자가 주체가 아닌 숫자가 이루어진 갯수를 주체로 점화식을 만들어 풀어내는 문제였으며, set을 이용해 중복되는 부분을 쉽게 처리하는 문제였다. (집합의 합집합 개념으로 생각하면 쉽게 이해된다.)

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dp/PGM_N으로_표현.java)
