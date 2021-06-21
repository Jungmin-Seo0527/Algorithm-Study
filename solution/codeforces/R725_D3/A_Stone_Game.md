# [A. Stone Game](https://codeforces.com/contest/1538/problem/A)

### 난이도

***
800
<br><br>

### 문제

***
Polycarp은 새로운 컴퓨터 게임을하고 있습니다. 이 게임은 연속적으로 n 개의 돌을 가지고 있습니다. i 위치의 돌은 정수 ai를가집니다. 모든 돌의 힘은 뚜렷합니다.

매 턴마다 Polycarp는 첫 번째 위치에있는 돌이나 마지막 위치에있는 돌 (즉, 맨 왼쪽 또는 맨 오른쪽 돌)을 파괴 할 수 있습니다. Polycarp가 돌을 파괴하면 더 이상 존재하지 않습니다.

이제 Polycarp은 두 가지 업적을 원합니다. 그는 가장 적은 힘을 가진 돌과 가장 큰 힘을 가진 돌을 파괴하면 그것들을 얻습니다. Polycarp가 목표를 달성하기 위해 수행해야하는 최소 동작 수를 알아 내도록
도와주세요.

예를 들어, n = 5이고 a = [1,5,4,3,2]이면 Polycarp은 다음과 같은 동작을 수행 할 수 있습니다.

가장 왼쪽에있는 돌을 파괴하십시오. 이 이동 후 a = [5,4,3,2]; 가장 오른쪽에있는 돌을 파괴하십시오. 이 이동 후 a = [5,4,3]; 가장 왼쪽에있는 돌을 파괴하십시오. 이 이동 후 a = [4,3].
Polycarp는 가장 크고 가장 적은 힘으로 돌을 파괴하여 게임을 끝낼 수 있습니다. 위의 예에서는 두 단계로 게임을 완료 할 수 있습니다. 예를 들면 :

가장 왼쪽에있는 돌을 파괴하십시오. 이 이동 후 a = [5,4,3,2]; 가장 왼쪽에있는 돌을 파괴하십시오. 이 이동 후 a = [4,3,2]. Polycarp는 가장 크고 가장 적은 힘으로 돌을 파괴하여 게임을
끝낼 수 있습니다.

<br><br>

### 입력

***
첫 번째 줄에는 정수 t (1≤t≤100)가 포함됩니다. 그런 다음 t 테스트 케이스가 따릅니다.

각 테스트 케이스의 첫 번째 줄에는 하나의 정수 n (2≤n≤100), 즉 돌의 수가 포함됩니다.

두 번째 줄에는 n 개의 고유 한 정수 a1, a2,…, an (1≤ai≤n)이 포함되어 있습니다.

<br><br>

### 출력

***
각 테스트 케이스에 대해 가장 큰 힘과 가장 낮은 힘으로 돌을 파괴하는 데 필요한 최소 이동 횟수를 출력합니다.

<br><br>

#### 예제 입력 1

> 5     
5       
1 5 4 3 2       
8       
2 1 3 4 5 6 8 7     
8       
4 2 3 1 8 6 7 5     
4       
3 4 2 1     
4       
2 3 1 4

#### 예제 출력 1

> 2     
4       
5       
3       
2



<br><br>

### 알고리즘 분류

***

* brute force
* dp
* greedy

<br><br>

### Solution

***

* 최소값, 최대값의 위치를 찾아낸다.
* 두 값을 뺄수 있는 4가지 경우를 모두 조사한다.
    1. 모두 왼쪽으로 빼기
    2. 모두 오른쪽으로 빼기
    3. 최소값은 왼쪽으로, 최대값은 오른쪽으로
    4. 최소값은 오른쪽으로, 최대값은 왼쪽으로

    ```java
    public class Main {
        static int solve(int[] arr, int N) {
            int max = 0;
            int min = 101;
            int maxIdx = 0;
            int minIdx = 0;
    
            for (int i = 0; i < N; i++) {
                if (min > arr[i]) {
                    min = arr[i];
                    minIdx = i;
                }
                if (max < arr[i]) {
                    max = arr[i];
                    maxIdx = i;
                }
            }
    
            int ret = Math.max(minIdx + 1, maxIdx + 1); // 둘다 왼쪽으로 빼기
            ret = Math.min(ret, Math.max(N - minIdx, N - maxIdx)); // 둘다 오른쪽으로 빼기
            ret = Math.min(ret, minIdx + 1 + N - maxIdx);
            ret = Math.min(ret, maxIdx + 1 + N - minIdx);
    
            return ret;
        }
    }
    ```
    * 인덱스와, 자신의 차례를 잘 구분해야 한다.
        * 인덱스 0번의 숫자가 왼쪽에서 꺼내지는 차례는 1번이다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R725_D3/A_Stone_Game.java)
