# [AC](https://www.acmicpc.net/problem/5430)

### 난이도

***
G5
<br><br>

### 알고리즘 분류

***

* 구현
* 자료 구조
* 문자열
* 파싱
* 덱

<br><br>

### Solution

***

이 문제의 포인트는 뒤집기 연산이 나올때 진짜 배열 뒤집으면 안된다는 것이다.     
뒤집기 연산이 나온 이후의 제거 함수를 어떻게 수행할 것이냐를 구현하면 뒤집기를 구현하지 않아도 된다.

우선 디폴트는 배열의 앞쪽 인덱스부터 제거한다. 만약 뒤집기가 수행되면 뒤쪽 인덱스를 제거하면 된다. 즉 `Deque`자료구조를 이용하는 것이 이 문제의 핵심이다.

```java
public class Main {
    static String solve(String p, int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length - 1;
        int f = 1;

        for (int i = 0; i < p.length(); i++) {
            char cp = p.charAt(i);
            if (cp == 'D') {
                if (leftIdx > rightIdx) return "error";
                if (f == 1) leftIdx++;
                else rightIdx--;
            } else {
                f *= -1;
            }
        }
        return getAns(arr, leftIdx, rightIdx, f).toString();
    }

}
```

실제 `Deque` 컬렉션을 이용해도 되었지만 일반 배열로도 충분히 구현할 수 있을 것이라 생각해서 컬렉션은 사용하지 않았다.

주의할 점은 `leftIdx == rightIdx`일때이다. 처음에는 에러 조건문인 `if (leftIdx > rightIdx) retrn "error"`을 제거 연산을 수행한 후에 확인했다. 그런데 이러면 예외가
발생한다.       
만약 현재 arr에 단 한개의 숫자가 존재한다면 `leftIdx == rightIdx`인 상태일 것이다. 만약 여기서 제거 연산을 수행하는 순간 `leftIdx > rightIdx`가 된다. 여기서 `error`를
출력하면 안된다. 이 지점에서 한번 더 제거 연산을 수행해야지 `error`가 발생하는 것이다. 지금은 `error`가 아니다.

> replaceAll        
> 처음 입력을 받을때 입력 형식에 쉼표와 대괄호가 포함되어 있었다. 정수 배열로 만들기 위해서 `replaceAll`를 이용해서 대괄호와 쉼표를 제거한 후에 정수 배열로 매핑을 시켰다.        
> 결과적으로 통과는 했지만 내가 생각했던것 보다 처리시간이 길었다. 그래서 원인을 찾아 본 결과 `replaceAll`이 문제였다.
>
> 이전 `문자열 폭발`문제에서도 `replaceAll`을 이용하면 메모리 초과, 시간 초과가 일어난다. 이 문제에서는 핵심 로직에는 쓰이지 않아서 상관 없었지만 입력값을 파싱하는 과정에서 사용하니 시간을 많이 잡아 먹었다. 파싱 함수를 직접 만들어서 사용하니 3배 정도 시간이 줄었다.(1000 -> 300)
>
> `replaceAll`에 찾아보니 메소드가 실행되면 그 안에서 정말 많은 단계를 수행한다. (정확하게 모두 이해하지는 못했다.) 결과적으로 처리시간이 생명이 코테에서는 가급적 `replaceAll`을 지양하는 것이 좋을 듯 하다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/ds/BOJ5430_AC.java)
