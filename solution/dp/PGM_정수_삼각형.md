# [정수 삼각형](https://programmers.co.kr/learn/courses/30/lessons/43105)

### 난이도

***
Level 3
<br><br>

### 알고리즘 분류

***

* 동적 프로그래밍

<br><br>

### Solution

***

피라미터 형태로 주어진 숫자의 배열에서 밑으로 내려갈때마다 더하는 경우의 가장 최대값을 구하는 문제. 점화식은 나를 기준으로 위쪽에 위치한 숫자들중 최대값과 자신을 더하면 된다.     
`dp[r][c] += max(dp[r-1][c], dp[r-1][c+1])`

* 양 끝쪽을 고려할때 존재하지 않는 경우를 처리해야 한다.

```java
public class Solution {
    private int getMax(int row, int col1, int col2) {
        int n1, n2;
        if (col1 < 0 || col1 >= triangle[row].length) {
            n1 = Integer.MIN_VALUE;
        } else {
            n1 = dp[row][col1];
        }
        if (col2 < 0 || col2 >= triangle[row].length) {
            n2 = Integer.MIN_VALUE;
        } else {
            n2 = dp[row][col2];
        }
        return Math.max(n1, n2);
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/dp/PGM_정수_삼각형.md)
