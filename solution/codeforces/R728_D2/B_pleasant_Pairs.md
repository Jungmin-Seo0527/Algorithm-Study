# [pleasant Pairs](https://codeforces.com/contest/1541/problem/B)

### 난이도

***
??
<br><br>

### 문제

***
n 개의 고유 한 정수로 구성된 a1, a2,…, an 배열이 제공됩니다. i <j 및 ai⋅aj = i + j가되는 인덱스 쌍의 수 (i, j)를 센다.

<br><br>

### 입력

***
첫 번째 줄에는 하나의 정수 t (1≤t≤104) (테스트 케이스 수)가 포함됩니다. 그런 다음 t 사례가 따릅니다.

각 테스트 케이스의 첫 번째 줄에는 하나의 정수 n (2≤n≤105) (배열 a의 길이)이 포함됩니다.

각 테스트 케이스의 두 번째 줄에는 공백으로 구분 된 n 개의 정수 a1, a2,…, an (1≤ai≤2⋅n)-배열 a가 포함됩니다. 모든 요소가 구별된다는 것이 보장됩니다.

모든 테스트 케이스에서 n의 합이 2⋅105를 초과하지 않음을 보장합니다.
<br><br>

### 출력

***
각 테스트 케이스에 대해 i <j 및 ai⋅aj = i + j가되는 인덱스 쌍 수 (i, j)를 출력합니다.
<br><br>

#### 예제 입력 1

> 3     
2       
3 1     
3       
6 1 5       
5       
3 1 5 9 2

#### 예제 출력 1

> 1     
1       
3

<br><br>

### Note

***

첫 번째 테스트 케이스의 경우 제약 조건을 충족하는 유일한 쌍은 a1⋅a2 = 1 + 2 = 3과 같이 (1,2)입니다. 두 번째 테스트 케이스의 경우 제약 조건을 충족하는 유일한 쌍은 (2,3)입니다.

세 번째 테스트 케이스의 경우 제약 조건을 충족하는 쌍은 (1,2), (1,5) 및 (2,3)입니다.

<br><br>

### 알고리즘 분류

***

* brute force
* implementation
* math
* number theory

<br><br>

### Solution

***

**이 문제는 `O(nlogn)`으로 풀어내야 하는 문제이다.**

* `ai * aj = i + j`롤 뽑을 때 배열의 크기는 `n`이다.
    * 이는 `i + j <= 2 * n`임을 의미한다.
    * 추가로 `i + j = ai * aj`이므로 `ai * aj = 2 * n`이 성립한다.

* 처음 생각한 반복문은 아래와 같은 형태였다.
    ```
    for (int i = 1; i <= 2 * n; i++)  {
        for (int j = 1; j <= i; j++ ) {
        //...
      }
    }
    ```
    * 이 방법은 당연히 `O(n^2)`이므로 시간초과에 걸린다.
    * `ai * aj <= 2 * n`성질을 이용해서 `O(nlogn)`으로 위 이중 반복문과 동일한 순서쌍을 구할 수 있다.
    * **바로 소수를 구할때와 같은 방법으로 `sqrt`를 이용해서 반복문의 범위를 줄이는 방법이다.**

* `O(nlogn)`
    ```
    for (int aiaj = 1; aiaj <= 2 * n ; aiaj++) {
        for(int ai = 1; ai <= Math.sqrt(aiaj); ai++)  {
        //...
      }
    }
    ```
    * 변수명을 `aiaj`: ai * aj, `ai` = a[i] 이런 식으로 만들었다.
    * 즉 입력으로 주어진 배열의 인덱스과 해당 배열의 값을 바꾼다.
        * `arr[Integer.parseInt(n)] = idx`
    * 소수를 구할때와 마찬가지로 `aiaj`의 제곱근까지만 나누는 수를 확인해서 나머지가 0인 경우의 숫자들에 한해서(즉 `ai * aj = aiaj`가 성립할 수 있는 `ai`에 대해서) 나머지 조건들을
      확인하면 된다.

```java
public class Main {
    static int solve(int[] arr, int N) {
        int cnt = 0;
        for (int aiaj = 3; aiaj <= 2 * N; aiaj++) {
            int sq = (int) Math.sqrt(aiaj);
            for (int ai = 1; ai <= sq; ai++) {
                if (aiaj % ai == 0
                        && aiaj != ai * ai
                        && arr[ai] + arr[aiaj / ai] == aiaj) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
```

* `if (aiaj % ai == 0`)
    * `ai * aj = aiaj`를 만족하는 `aj`가 존재한다.
* `if (aiaj != ai * ai)`
    * 문제에서 배열의 모든 수는 같지 않다고 했으므로 같은 수를 곱해서 `aiaj`가 나오는 경우는 제외해준다.
    * `if(arr[ai] + arr[aiaj / ai] == aiaj)`
        * `i + j = ai * aj`를 성립하는지 확인한다.
        * `aiaj / ai`가 `aj`를 의미한다.

> 처음에는 투포인터를 이용해서 정렬을 한 후에 가능한 쌍을 구하려고 했다. 하지만 위의 솔루션처럼 수학적인 풀이법을 적용하지 않으면 `O(nlogn)`으로 시간 복잡도를 만들 수 없었던 문제였다.      
> 언듯 보면 굉장히 쉬운 문제라고 생각할 수 있으나, 수학적인 문제를 많이 풀어보지 않는 사람들은 쉽게 풀이를 떠올리지 못할 것이다.         
> 위에서도 언급했듯이 어려운 수학적 방법은 아니었다. 소수를 구하는 가장 대중적인 방법을 문제에 적용시키는 문제였다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R728_D2.java)
