# [Pair Programming](https://codeforces.com/contest/1547/problem/C)

### 난이도

***
1100
<br><br>

### 알고리즘 분류

***

* greedy
* two pointers

<br><br>

### Solution

***

문제가 시키는 대로 구현하면 무난하게 풀리는 문제였다.      
이미 k줄을 채운 상태에서 각각의 배열에 존재하는 해당 줄을 바꿀 수 있을때, 각 줄은 k 이상이어야 한다.        
첫번째 예시를 보면
> 3 2 2     
> 2 0
> 0 5

가 입력으로 주어진다. 이미 3줄을 작업을 한 상태이고 두 사람은 각각 `[2, 0]`, `[0, 5]`작업을 필요로 한다. 기존에 3줄이 존재하므로 선택사항은 두가지이다. a배열의 2번 줄을 수정하는 것. 혹은
b번배열의 0, 즉 k+1을 하여 줄을 추가하는 것이다.    
줄을 추가하면 k = 4인 상태가 된다. 여기서 b 배열의 5번 줄 수정이 불가능 하다. 현재 줄은 k인 4줄이므로 5번줄은 존재하지도 않는다. 따라서 이 순간에는 a 배열의 2번 줄 수정만이 가능하다. 이후 k가
5가 된 순간에는 5번 줄 수정이 가능하다. 이 문제는 각각의 배열에 포인터를 둔 투포인터 알고리즘으로 쉽게 풀어낼 수 있다.

```java
public class Main {
    static int[] solve(int k, int n, int m, int[] a, int[] b) {
        int aIdx = 0;
        int bIdx = 0;
        int[] ret = new int[n + m];
        int retIdx = 0;
        while (aIdx < n || bIdx < m || retIdx < n + m) {
            if (aIdx < n && a[aIdx] == 0) { // a배열의 줄 추가 작업
                ret[retIdx++] = 0;
                aIdx++;
                k++;
            } else if (bIdx < m && b[bIdx] == 0) { // b배열의 줄 추가 작업
                ret[retIdx++] = 0;
                bIdx++;
                k++;
            } else if (aIdx < n && a[aIdx] <= k) { // a배열의 줄 수정 작업
                ret[retIdx++] = a[aIdx++];
            } else if (bIdx < m && b[bIdx] <= k) { // b 배열의 줄 수정 작업
                ret[retIdx++] = b[bIdx++];
            } else { // 그 어떤 작업도 불가능 하다면 이는 작업을 모두 완료하지 못하는 경우
                ret[0] = -1;
                return ret;
            }
        }
        return ret;
    }
}
```

여기서 줄 추가 작업은 어떠한 조건이 없이 수행할 수 있다. 따라서 가장 우선적으로 항상 a배열, b 배열의 줄 추가작업 순서가 오면 먼저 처리했다. 이렇게 해야 나중에 줄 수정 작업이 올때 가능한 줄 추가는 모두
수행했으니 줄 수정 작업을 할 수 있는 가능성이 높아진다.        
만약 하나의 루프에서 어떠한 작업도 하지 못한 경우에는 이후에도 계속 아무런 작업도 하지 못하므로 주어진 작업을 모두 수행하는 것이 불가능 하므로 -1을 반환한다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R731_D3/C_Pair_Programming.java)
