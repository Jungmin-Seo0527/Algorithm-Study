# [Pretty Permutation](https://codeforces.com/contest/1541/problem/A)

### 난이도

***
??
<br><br>

### 문제

***
한 줄에 1에서 n까지 레이블이 지정된 n 개의 고양이가 있으며 i 번째 고양이는 i 위치에 있습니다. 하루 종일 같은 자리에서 빙글 빙글 돌아 다니는 게 지겨워서 고양이가 예전과 같은 자리에 있지 않도록 순서를
바꾸고 싶어한다. 또한 게으 르기 때문에 이동하는 총 거리를 최소화하려고합니다. 재주문 후 각 위치에 어떤 고양이가 있어야할지 결정하도록 도와주세요.

예를 들어 고양이 3 마리가있는 경우 유효한 순서 변경 : [3,1,2]. 원래 위치에는 고양이가 없습니다. 고양이 1이 오른쪽으로 한 단계 이동하고, 고양이 2가 오른쪽으로 한 단계 이동하고, 고양이 3이 왼쪽으로
두 단계 이동하므로 고양이가 이동하는 총 거리는 1 + 1 + 2 = 4입니다.

<br><br>

### 입력

***
첫 번째 줄에는 테스트 사례 수인 단일 정수 t (1≤t≤100)가 포함됩니다. 그런 다음 t 테스트 케이스가 따릅니다.

각 테스트 케이스의 첫 번째 유일한 줄에는 하나의 정수 n (2≤n≤100), 즉 고양이 수가 포함됩니다.

문제의 제약 하에서 항상 답이 존재한다는 것을 증명할 수 있습니다.
<br><br>

### 출력

***
각 테스트 케이스에 대해 하나씩 t 답변을 출력합니다. 각 답은 n 개의 정수 (최소 총 거리가있는 순열)로 구성됩니다. 답변이 여러 개인 경우 인쇄하십시오.
<br><br>

#### 예제 입력 1

> 2     
2       
3

#### 예제 출력 1

> 2 1       
3 1 2

<br><br>

### 알고리즘 분류

***

* constructive algorithms
* greedy
* implementation
* permutation

<br><br>

### Solution

***

permutation 구현 문제. 단 permuation을 구현하지 않아도 풀수 있는 문제이다.

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int j = 0; j < t; j++) {
            int n = input.nextInt();
            for (int i = 1; i <= n - 3; i += 2) {
                System.out.print((i + 1) + " " + i + " ");
            }

            if (n % 2 == 0) {
                System.out.println(n + " " + (n - 1));
            } else {
                System.out.println(n + " " + (n - 2) + " " + (n - 1));
            }
        }
    }
}
```

나는 permutation을 구현했지만 많은 사람들이 `arr[i] != i`라는 조건을 이용해서 문제를 풀었다.

> 게시판을 보니 너무 Div.2 인데 너무 쉬운 문제를 출제한 것이 아니냐머 불평하는 사람들이 보였다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R728_D2/A_Pretty_Permutation.java)
