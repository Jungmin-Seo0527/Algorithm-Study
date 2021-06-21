# [Number of Pairs](https://codeforces.com/contest/1538/problem/C)

### 난이도

***
1300
<br><br>

### 문제

***
n 개의 정수 배열 a가 제공됩니다. ai + aj의 합이 l보다 크거나 같고 r보다 작거나 같은 쌍 (i, j) (1≤i <j≤n)의 수를 구합니다 (즉, l≤ai + aj≤ 아르 자형).

예를 들어, n = 3, a = [5,1,2], l = 4 및 r = 7이면 두 쌍이 적합합니다.

i = 1 및 j = 2 (4≤5 + 1≤7); i = 1 및 j = 3 (4≤5 + 2≤7). 입력 첫 번째 줄에는 정수 t (1≤t≤104)가 포함됩니다. 그런 다음 t 테스트 케이스가 따릅니다.

각 테스트 케이스의 첫 번째 줄에는 3 개의 정수 n, l, r (1≤n≤2⋅105, 1≤l≤r≤109), 즉 배열의 길이와 쌍의 합에 대한 제한이 포함됩니다.

두 번째 줄에는 n 개의 정수 a1, a2,…, an (1≤ai≤109)이 있습니다.

n 개의 전체 테스트 케이스의 합이 2⋅105를 초과하지 않음을 보장합니다.

산출 각 테스트 케이스에 대해 단일 정수, 즉 l≤ai + aj≤r이되는 인덱스 쌍의 수 (i, j) (i <j)를 출력합니다.

<br><br>

#### 예제 입력 1

> 4     
3 4 7       
5 1 2       
5 5 8       
5 1 2 4 3       
4 100 1000      
1 1 1 1     
5 9 13      
2 5 5 1 1

#### 예제 출력 1

> 2     
7       
0       
1

***

<br><br>

### 알고리즘 분류

***

* two pointers

<br><br>

### Solution

***

문제를 푸는 아이디어는 이렇다.

* `arr[left] + arr[right] <= r`인 순서쌍을 구한다.
* `arr[left] + arr[right] < l`인 순서쌍을 구한다.
* 두 순서쌍의 차이가 답이다.

위에서 순서쌍을 구하기 위해서 twopointers 알고리즘을 사용했다. (당연히 twopointers 알고리즘을 하용하기 위해 오름차순 정렬을 시켰다.)

```java
public class Main {
    static long pairCnt(List<Integer> arr, int target) {
        long ret = 0;
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            if (arr.get(left) + arr.get(right) <= target) {
                ret += (long) right - (long) left;
                left++;
            } else {
                right--;
            }
        }
        return ret;
    }

}
```

* 기본 아이디어는 `left`인 경우 나올수 있는 `right`를 모두 구하는 것이다.

**정렬**

* [java 내장 sort 메서드 간 속도 차이](https://www.acmicpc.net/board/view/36536)
* [속도 비교](https://www.acmicpc.net/blog/view/58)

이 문제를 풀때 twopointers 알고리즘으로 풀기 위해서 정렬을 사용했다. 이 문제를 이분탐색으로 풀어낼 수 있는데 마찬가지로 정렬이 선행되어야 한다. 그런데 이 정렬에서 문제가 있다.

처음에는 `int[]` 형태로 배열을 작성해서 `Arrays.sort(arr)`로 정렬 후 문제를 풀었으나 시간초과가 일어난다. (정확하게 tc7에서 일어난다.) 처음에는 문제를 푸는 방법 자체가 잘못되었다고
생각했으나 그것이 아니었다. `List<Integer> arr` 형태로 배열을 만들고 `Collections.sort(arr)`로 정렬을 하니 통과를 받았다. 완전히 같은 풀어법이지만 arr의 형태에 따라서(정확히는
정렬 내장 함수에 따라서) 문제의 당락이 결정되었다. 기본적으로 `Array.sort()`가 빠르지만 최악의 경우에 `O(N^2)`로 걸릴때는 시간초과가 일어난다. 그리고 `Collections.sort()`는
비교적 느리지만 어떠한 경우에서는 (아마도 `Arrays.sort`의 최악의 경우) `Arrays.sort`보다 빠른듯 하다.         

결론적으로 주어진 데이터를 보고 더 효율적인 데이터구조를 사용해서 내장 함수를 사용해야 할 것 같은데... 그 판단을 어떻게 정확하게 해야 할지는 모르겠다.

> 완전히 같은 로직인데도 이 문제처럼 내장 함수의 선택에 당락이 좌우되는 문제는 개인적으로 좋은 문제라고 생각하진 않는다. 다음엔 이런 경우에는 그냥 두개 다 써보는 방법밖엔 없는 듯 하다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R725_D3/C_Number_of_Pairs.java)
