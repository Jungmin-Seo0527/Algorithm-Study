# [IQ Test](https://www.acmicpc.net/problem/1111)

### 난이도

***
G2
<br><br>

### 알고리즘 분류

***

* 수학
* 구현

<br><br>

### Solution

***

조건 분기를 잘 확인해야 하는 문제이다. case work 태그를 못받은 것을 보니 이정도의 조건 분기는 어림없어 보이긴 하지만 그래도 길이에 따라, 그리고 배열 요소에 따라 조건이 많이 생기는 것은 사실이다.

```java
public class Main {
    static String solve() {
        // 길이가 1
        if (N == 1) {
            return "A";
        }

        // 길이가 2
        if (N == 2) {
            if (arr[0] != arr[1]) { // 두 숫자가 다름
                return "A";
            } else { // 두 숫자가 같음
                return arr[0] + "";
            }
        }

        int a, b;
        if (arr[0] == arr[1]) { // 숫자가 같은 경우일 때
            a = 1;
            b = 0;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - arr[0] * a;
        }

        // 처음 세운 a, b값이 끝까지 성립하는지 확인
        for (int i = 1; i < N; i++) {
            if (arr[i] != arr[i - 1] * a + b) {
                return "B";
            }
        }
        return (arr[N - 1] * a + b) + "";
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/implementation/BOJ1111_IQ_Test.java)

