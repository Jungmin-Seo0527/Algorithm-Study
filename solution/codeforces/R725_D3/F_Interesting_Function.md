# [Interesting Function](https://codeforces.com/contest/1538/problem/F)

### 난이도

***
1500
<br><br>

### 문제

***
두 개의 정수 l과 r이 주어집니다. 여기서 l <r입니다. 결과가 r과 같을 때까지 l에 1을 더합니다. 따라서 정확히 RL 추가가 수행됩니다. 이러한 각 추가에 대해 이후 변경 될 자릿수를 살펴 보겠습니다.

예를 들면 :

l = 909이면 하나를 추가하면 910이되고 2 자리 숫자가 변경됩니다. l = 9에 1을 더하면 결과는 10이되고 2 자리도 변경됩니다. l = 489999에 1을 더하면 결과는 490000이되고 5 자리가
변경됩니다. 변경된 숫자는 항상 10 진수로 작성된 결과의 접미사를 형성합니다.

l에서 r을 얻으려면 매번 1을 더하여 변경된 총 자릿수를 출력합니다.

<br><br>

### 입력

***
첫 번째 줄에는 정수 t (1≤t≤10^4)가 포함됩니다. 그런 다음 t 테스트 케이스가 따릅니다.

각 테스트 케이스는 두 개의 정수 l과 r (1≤l <r≤10^9)로 특성화됩니다.
<br><br>

### 출력

***
각 테스트 케이스에 대해 l에서 r을 얻으려면 매번 하나씩 추가하여 변경된 총 자릿수를 계산하십시오.
<br><br>

#### 예제 입력 1

> 4     
1 9     
9 10        
10 20       
1 1000000000

#### 예제 출력 1

> 8     
2       
11      
1111111110

<br><br>

### 알고리즘 분류

***

* math
* number theory
* binary search
* dp

<br><br>

### Solution

dp, bs등 다양한 풀이법이 존재한다고 한다. 나는 수학적으로 규칙을 찾아서 풀어내었다.

* 0부터 n까지의 변화 횟수 구하기
    * 숫자들을 나열하고 규칙을 찾아내었다.
        * 0 -> 10 : 11
        * 10 -> 100 : 11 * 9 + 1 = 100
        * 100 -> 1000 : (100 + 11) * 9 + 1 = 1000
        * 1000 -> 10000 : (1000 + 100 + 11) * 9 + 1 = 10000
        * ...
    * 각 자리숫자로 변경될 때의 변경 횟수를 구할 수 있다.
        * 0 -> 10 : 11
        * 0 -> 100 : 100 + 11 = 111
        * 0 -> 1000 : 1000 + 100 + 11 = 1111
        * 0 -> 10000 : 10000 + 1000 + 100 + 11 = 11111
        * ...
    * 규칙을 찾아서 적용한다.
        * 예) 250 -> 2 * 111 + 5 * 11
        * 예2) 1673 -> 1 * 1111 + 6 * 111 + 7 * 11 + 3

* `0 -> r` - `0 -> l` = `l -> r`

```java
public class Main {
    private static int solve(int l, int r) {
        String sr = String.valueOf(r);
        String sl = String.valueOf(l);
        int temp = 1;
        int ret = 0;
        for (int i = sr.length() - 1; i >= 0; i--) {
            ret += Integer.parseInt(String.valueOf(sr.charAt(i))) * temp;
            temp *= 10;
            temp += 1;
        }
        temp = 1;
        for (int i = sl.length() - 1; i >= 0; i--) {
            ret -= Integer.parseInt(String.valueOf(sl.charAt(i))) * temp;
            temp *= 10;
            temp++;
        }
        return ret;
    }
}

```

> 천천히 규칙을 찾아내서 한번에 풀어냈지만 규칙을 찾아내는데 1시간 가량 걸렸던 문제였다. 수학적인 사고력이 필요로 하는 문제이며, 사실 기업의 코테 유형과는 거리가 멀어 보이지만 유연한 사고력을 길러서 나쁠건 없을 것이라 생각한다.

***

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R725_D3/F_Interesting_Function.java)
