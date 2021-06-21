# [Another Problem About Dividing Numbers](https://codeforces.com/contest/1538/problem/D)

### 난이도

***
1700
<br><br>

### 문제

***
n에 대해 숫자 a와 b를 동일하게 만들 수있는 최대 이동 수를 나타냅니다. a = b = 1 일 때 그리고 우리가 a 또는 b를 소수로 나눌 때마다 이동 횟수가 최대라는 것을 이해하기 쉽습니다. 즉, n = a의
소수의 지수의 합 + b의 소수의 지수의 합.

숫자 a와 b를 동일하게 만들 수있는 최소 이동 수를 m으로 표시합시다. 몇 가지 경우를 고려하십시오.

a = b이면 m = 0이고; gcd (a, b) = a 또는 gcd (a, b) = b이면 m = 1; 그렇지 않으면 m = 2입니다. 그러면 다음과 같은 경우에 "예"라고 대답 할 수 있습니다.

m≤k≤n 및 k = 1 및 m = 1, 또는 m≤k≤n 및 k ≠ 1입니다.

<br><br>

### 입력

***
첫 번째 줄에는 하나의 정수 t (1≤t≤104)가 포함됩니다. 그런 다음 t 테스트 케이스가 따릅니다.

각 테스트 케이스에는 세 개의 정수 a, b 및 k (1≤a, b, k≤109)가 포함됩니다.
<br><br>

### 출력

***
각 테스트 케이스 출력에 대해 :

"예", 정확히 k 턴에서 숫자 a와 b를 동일하게 만들 수 있다면; 그렇지 않으면 "아니오"입니다. 문자열 "Yes"및 "No"는 어떤 경우에도 출력 될 수 있습니다.
<br><br>

#### 예제 입력 1

> 8     
36 48 2     
36 48 3     
36 48 4     
2 8 1       
2 8 2       
1000000000 1000000000 1000000000        
1 2 1       
2 2 1

#### 예제 출력 1

> YES       
YES     
YES     
YES     
YES     
NO      
YES     
NO

***

<br><br>

### 알고리즘 분류

***

* constructive algorithms
* math
* number theory

<br><br>

### Solution

***

알고리즘 분류명부터 생소하다. constructive algorithm 직역하면 건설적 알고리즘, 우리나라에서는 주로 구성적 알고리즘이라고 한다. 백준에도 해당 알고리즘 분류가 존재하는 것은 확인했다. 하지만 그
수가 적고 내 최종 목표인 취업을 위한 공부에도 크게 도움이 될것 같지는 않은 문제이다. 단 이 문제에서 사용한 소수 구하기, 최소 공배수 구하기를 복습차원으로 다시 한번 보자.

* 소수 구하기
    ```java
    public class Main {
        static List<Integer> primeList(int max) {
            boolean[] isPrime = new boolean[max + 1];
            List<Integer> ret = new ArrayList<>();
            for (int i = 2; i <= max; i++) {
                if (!isPrime[i]) {
                    ret.add(i);
                    for (int j = i + i; j <= max; j += i) {
                        isPrime[j] = true;
                    }
                }
            }
            return ret;
        }
    }
    ```
    * `max`값 이하의 모든 소수를 구하는 꽤나 빠른 방법이다.

* gcd 구하기
    ```java
    public class Main {
        static int gcd(int a, int b) {
            if (a == 0) {
                return b;
            }
            return gcd(b % a, a);
        }
    }
    ```
    * 유클리드 호제법을 이용한 빠른 시간내에 최대 공약수를 구하는 방법이다.
    * 기업의 코딩 테스트에는 아직 안나왔지만, 최대 공약수 자체가 쉬운 개념이므로 언제든 나올수 있을 것이라 생각한다.

이 문제는 a, b를 같은수로 만들 수 있는 최소 단계, 최대 단계를 구해서 그 사이에 k값이 존재하면 `YES` else `NO`를 반환하면 된다.

* a, b를 같은 수로 만들 수 있는 최대 단계(`N`) - 소수로만 나누기
    * 소수로 계속 나누는 행위는 그 값을 1로 만드는 가장 먼 과정이 된다.
    * 예) 8을 1로 만들때, 바로 8로 나누기 보다는 2로 3번 나누면 단계가 더 길어진다.

```java
public class Main {
    static int cntPrime(int n) {
        int cnt = 0;
        for (Integer prime : primes) {
            while (n % prime == 0) {
                cnt++;
                n /= prime;
            }
            if (n == 1) break;
        }

        return n != 1 ? cnt + 1 : cnt;
    }
}
```

* a, b를 같은 수로 만드는 가장 최소 단계(`M`) - 0 or 1 or 2
    * 가장 최소의 수는 3가지 경우가 존재한다.
    * `a == b` 인 경우: 0
    * `gcd(a, b) == a || gcd(a, b) == b`인 경우: 1
    * 그 외 모든 경우: 2 (a, b가 어떤 값이더라도 공통적으로 1로 만들면 같은 수가 된다.)

* `M<=k<=N`인 경우 (당연히 최소 최대값 사이에 존재해야지...)
    * `k == 1 && M == 1`
    * `k != 1`
    * 위 두 경우에만 "YES" else "NO"

> 이 문제를 푸는 방법을 3가지로 나누어서 설명했지만 마지막 단계를 완벽하게 이해하진 못했다.   
> 최대 최소값을 구하면 그 사이의 단계수로 동일한 값을 만들 수 있다는 명제를 어떻게 증명해야 하는지 모르겠다. 구성적 알고리즘 자체가 수학적 증명을 알고리즘에 적용시킨 방식인데 더 공부가 필요해 보인다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R725_D3/D_Another_Problem_About_Dividing_Numbers.java)
