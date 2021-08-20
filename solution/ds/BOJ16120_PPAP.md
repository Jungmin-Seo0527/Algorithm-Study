# [PPAP](https://www.acmicpc.net/problem/16120)

### 난이도

***
G4
<br><br>

### 알고리즘 분류

***

* 스택
* 그리디
* 문자열

<br><br>

### Solution

***

이전에 모 기업의 코테에서 이런 비슷한 유형의 문제를 푼 적이 있다. 이때는 무지성으로 `replaceAll`메소드를 무한 반복해서 풀어냈다. 당연히 문자열의 길이가 길어질 수록 시간초과가 일어난다.

아래는 시간초과 코드이다.

```java
public class Main {
    static void solve(String input) {
        String ret = "";
        while (true) {
            ret = input.replaceAll("PPAP", "P");
            if (ret.equals(input)) {
                break;
            }
            input = ret;
        }
        if (ret.equals("P")) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
```

이후에 알게 되었지만 `replaceAll`메소드는 알고리즘 문제에 적합하지 않는 메소드이다. 시간을 많이 잡아 먹는 메소드이다.        
특정 문자열을 반복적으로 다른 문자열로 변환하는 문제는 스택을 이용해서 풀어내면 `O(N)`으로 풀어낼 수 있다.

이 문제는 `A`문자를 기준으로 `PPAP`문자열이 존재하는지 검사하여 변환하는 방식으로 풀어냈다. (그리디)

```java
public class Main {
    static void solve() {
        Stack<Character> stack = new Stack<>();
        int len = input.length;
        for (int i = 0; i < len; i++) {
            if (stack.size() < 3) {
                stack.push(input[i]);
            } else {
                if (input[i] == 'P' && stack.peek() == 'A') {
                    char a = stack.pop();
                    char p1 = stack.pop();
                    char p2 = stack.pop();
                    if (p1 == 'P' && p2 == 'P') {
                        stack.push('P');
                    } else {
                        stack.push(p2);
                        stack.push(p1);
                        stack.push(a);
                        stack.push(input[i]);
                    }
                } else {
                    stack.push(input[i]);
                }
            }
        }

        if (stack.size() == 1 && stack.peek() == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
```

스택에 존재하는 문자을 꺼내서 PPAP가 만들어지는지 확인하는 방식을 이용했다.

스택의 원리만 이용한 일반 배열로 구현하여 `push, pop` 대신에 현재 인덱스를 조절해서 풀어내만 시간을 더 줄여낼 수 있다.

```java
public class Main {
    static void solve() {
        char[] arr = new char[input.length]; // 스택을 대신하는 배열
        int len = input.length;
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (idx <= 2) {
                arr[idx++] = input[i];
            } else {
                if (input[i] == 'P' && arr[idx - 1] == 'A' && arr[idx - 2] == 'P' && arr[idx - 3] == 'P') { // push, pop 대신에 idx 이동으로 탐색
                    idx -= 2;
                } else {
                    arr[idx++] = input[i];
                }
            }
        }
        if (idx == 1 && arr[idx - 1] == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
```

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/ds/BOJ16120_PPAP.java)
