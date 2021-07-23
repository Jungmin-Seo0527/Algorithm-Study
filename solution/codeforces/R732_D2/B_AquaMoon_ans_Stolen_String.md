# [AquaMoon ans Stolen_String](https://codeforces.com/contest/1546/problem/B)

### 난이도

***
1200
<br><br>

### 알고리즘 분류

***

* math

<br><br>

### Solution

***

번뜩이는? 아이디어가 필요했던 문제이다. 길이가 같은 홀수개의 문자열이 존재할 때 서로 짝을 지어서 서로 같은 자리의 문자를 바꿔치기를 한 후에 짝이 되지 않는 문자열은 없애 버린다. 이전의 문자열들과 바꿔치기 한
후 짝이 안된 문자열을 버린 후의 문자열들이 주어졌을 때 버려진 문자열을 찾는 문제이다.

문자들의 바꿔치기를 잘 생각해 보자. 짝이 되는 문자열의 임의의 갯수의 문자들이 서로 같은 자리의 문자들과 자리를 바꾼다. 즉 문자의 갯수는 바뀌지 않는다. 문자의 자리만 바꿔치기 될 뿐 **문자의 갯수는 작업을
수행해도 변함이 없다.** 그렇다면 문자열들을 아래로 나열하여 2차원 그래프 형태로 놓았을 때 한 컬럼에서 홀수번 등장하는 문자는 버려지는 문자열에 해당하는 문자가 된다.

또한 문자들을 세지 않아도 컬럼에서 문자의 갯수는 변하지 않는다는 성질로 다른 방법으로 풀어낼 수 있다.      
컬럼을 기준으로 처음 주어지는 문자들은 모두 더해버린다. 이후 바꿔치기 한 문자들은 뺀다. 그렇다면 결국 마지막에 남은 것은 버려진 문자열의 문자들이 남는다.

```java
public class Main {
    static String solve(int n, int m, String[] s1, String[] s2) {
        int[] ans = new int[m];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                ans[j] += s1[i].charAt(j) - s2[i].charAt(j);
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int j = 0; j < m; j++) {
            ans[j] += s1[n - 1].charAt(j);
            ret.append((char) ans[j]);
        }
        return ret.toString();
    }
}
```

**컬럼에서 문자의 등장 횟수는 변함이 없다.** 이 문장이 문제를 푸는 핵심 아이디어였다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R732_D2/B_AquaMoon_ans_Stolen_String.java)
