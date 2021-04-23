# [H-Index](https://programmers.co.kr/learn/courses/30/lessons/42747)

### 난이도

***
Level 2
<br><br>

### 알고리즘 분류

***

* 정렬
* 이분탐색

<br><br>

### Solution

***

가장 핵심인 조건만 구현해내면 되는 문제였다.

* `n`편의 논문중 `h`번 이상 인용된 논문이 `h`편 이상이고, 나머지 논문이 `h`편 이하 인용되었을때 `h`의 최대값

이 조건을 이분탐색을 통해서 구했다. 단 h값은 논문의 인용횟수가 아닌 임의의 숫자임을 조심하자.

```java
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i <= citations[citations.length - 1]; i++) {
            int idx = bs(citations, i);
            if (idx <= i && citations.length - idx >= i) {
                answer = i;
            }
        }
    }
}
```

* bs 메소드는 이분탐색을 실행한다.
    * `target`의 값이 들어갈 수 있는 인덱스를 반환한다.
    * `idx`의 의미는 `i`번 (문제에서는 `h`로 표현)이하로 인용된 논문의 갯수를 의미하게 된다.
* 조건대로 `idx`가 `i` 이하 (나머지 논문이 `h`편 이하 인용) && `citations.length - idx >= i` (`h`번 이상 인용된 논문이 `h`편 이상)일때의 최대값을 구하면 된다.

다른 사람들의 풀이를 보니 이분탐색이 아닌 다른 방법으로 구해낸 사람들이 많았다.

* 원소값은 점점 감소하고, 원소 값 이상인 것의 갯수는 점점 감소하므로, 이 두 값의 최소값의 변화가 증가하다가 감소하는 지점을 찾으면 그것이 답이 된다.

위의 규칙을 찾아내어 그대로 구현해내면 쉽게 답을 찾을 수 있다.

```java
public class Solution {
    public int solution(int[] citations) {
        for (int i = citations.length - 1; i > -1; i--) {
            int min = Math.min(citations[i], citations.length - i);
            if (max < min) max = min;
        }
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/binarySearch/PGM_H_Index.java)
