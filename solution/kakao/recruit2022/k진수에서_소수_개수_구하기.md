# [K진수에서 소수 갯수 구하기](https://school.programmers.co.kr/learn/courses/30/lessons/92335)

### 난이도

*** 

Level2

<br><br>

### 알고리즘 분류

***

* 구현
* 진수 변환
* 소수 판별

<br><br>

### Solution

***

#### 진수 변환

##### 자릿수 구하기

* 진수 변환 값을 int 배열로 반환하기 위한 배열의 사이즈 구하기
* 배열로 반환하는 이유는 진수 변환 값을 왼쪽부터 읽으면서 소수를 구해야 하기 때문
* n의 값 이하의 가장 큰 k^sz의 sz +1을 반환하면 된다.
* 예:  n = 9, k = 3일때 n이하의 k^sz의 sz = 2 , n의 3진수는 100이므로 3(2 + 1)자리

```java
public class Solution {
    /**
     * 진수 변환 자릿수 구하기
     * @param n 10진수 n
     * @param k k 진수로 변환
     * @return 자릿수
     */
    private int getConArrSZ(int n, int k) {
        int temp = 1;
        int sz = 1;
        while (temp * k <= n) {
            temp *= k;
            sz++;
        }
        return sz;
    }
}

```

##### 진수 변환하기

* int 배열로 반환
* n을 k로 계속 나눠주면서 배열의 끝부터 그 나머지를 채워준다.
* 마지막(배열의 인덱스 0)은 마지막으로 나눈 몫을 채워준다.

```java
public class Solution {
    /**
     * 진수 변환하기
     * @param n 10진수
     * @param k k진수로 변환
     * @return int 배열로 반환
     */
    public int[] conversion(int n, int k) {
        int sz = getConArrSZ(n, k);
        int[] ret = new int[sz];
        for (int i = sz - 1; i >= 0 && n >= 0; i--) {
            ret[i] = n % k;
            n /= k;
        }
        return ret;
    }

}
```

#### 소수 판별하기

* 소수 구하기 3가지 방법 설명 추가 필요

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/kakao/recruit2022/k진수에서_소수_개수_구하기.java)
