# [n진수 게임](https://programmers.co.kr/learn/courses/30/lessons/17687)

### 난이도

***
Level 2
<br><br>

### 알고리즘 분류

***

* 구현
* 문자열

<br><br>

### Solution

***

조심해야 할 것은 한 숫지를 진법대로 변환했을때 튜브가 그 숫자들중 꼭 1개만 말하는 법은 없다. 그 숫자들중에서 2개를 말해야 하는 경우도 존재한다.

* 숫자를 진법대로 변환해서 문자열로 반환한다.
    * 나머지가 10보다 큰 경우에는 알파벳으로 표현한다.

```java
public class Solution {
    private String transInt(int num, int n) {
        StringBuilder ret = new StringBuilder();
        while (true) {
            int moc = num / n;
            int na = num % n;
            if (na < 10) {
                ret.append(na);
            } else {
                char c = (char) ('A' + na - 10);
                ret.append(c);
            }
            if (moc == 0) break;
            num = moc;
        }
        // System.out.println(temp + " " + n + "->" + ret.reverse().toString());
        return ret.reverse().toString();
    }
}
```

* 변환된 문자열의 길이만큼 buf를 하나씩 더하다가 튜플의 순서일때의 문자를 저장, 사람 인원수와 같은 값이 될때는 buf값을 다시 0으로 되돌린다.

```java
public class Solution {
    public String solution(int n, int t, int m, int p) {
        int buf = 0;
        int num = 0;
        StringBuilder ret = new StringBuilder();
        while (t > 0) {
            String transedInt = transInt(num++, n);
            for (int i = 0; i < transedInt.length(); i++) {
                buf++;
                if (buf == p) { // 튜플차례
                    ret.append(transedInt.charAt(i));
                    t--;
                    if (t == 0) break;
                }
                if (buf == m) { // 모든 인원이 차례대로 숫자를 말함
                    buf = 0;
                }
            }

        }
        return ret.toString();
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/kakao/recruit2018/n진수_게임.java)
