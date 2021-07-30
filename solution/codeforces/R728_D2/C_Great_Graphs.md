# [Greate Graphs](https://codeforces.com/contest/1541/problem/C)

### 난이도

***
1400
<br><br>

### 문제

***
농부 John은 일방향 도로로 연결된 n 개의 목초지로 구성된 농장을 가지고 있습니다. 각 도로에는 도로의 시작부터 끝까지 걸리는 시간을 나타내는 가중치가 있습니다. 도로의 무게는 마이너스 일 수 있습니다. 소가
너무 빨리 이동하여 시간을 거슬러 올라갈 수 있습니다! 그러나 Farmer John은 젖소가 일련의 도로를 가로 질러 이동하여 시간을 무한히 거슬러 올라갈 수있는 타임 루프에 갇히는 것은 불가능하다고 보증합니다.
또한 각 목초지 쌍은 각 방향으로 최대 하나의 도로로 연결됩니다.

불행히도 Farmer John은 농장지도를 잃어 버렸습니다. 그가 기억하는 것은 배열 d입니다. 여기서 di는 소가 일련의 도로를 사용하여 목초지 1에서 i 번째 목초지에 도달하는 데 걸리는 최소 시간입니다. 농장의
비용은 각 도로의 무게를 합한 것이며 Farmer John은 자신의 기억과 일치하는 농장의 최소 비용을 알아야합니다.

<br><br>

### 입력

***
첫 번째 줄에는 하나의 정수 t (1≤t≤104) (테스트 케이스 수)가 포함됩니다. 그런 다음 t 사례가 따릅니다.

각 테스트 케이스의 첫 번째 줄에는 목초지의 수인 단일 정수 n (1≤n≤105)이 포함됩니다.

각 테스트 케이스의 두 번째 줄에는 n 개의 공백으로 구분 된 정수 d1, d2,…, dn (0≤di≤109) 인 배열 d가 있습니다. d1 = 0이 보장됩니다.

모든 테스트 케이스에서 n의 합은 105를 초과하지 않습니다.
<br><br>

### 출력

***
각 테스트 사례에 대해 Farmer John의 메모리와 일치하는 팜의 가능한 최소 비용을 출력합니다.
<br><br>

#### 예제 입력 1

> 3     
3       
0 2 3       
2       
0 1000000000        
1               
0

#### 예제 출력 1

> -3        
0       
0

<br><br>

### Node

***

첫 번째 테스트 사례에서는 도로를 추가 할 수 있습니다.

1 번 목초지에서 2 번 목초지로 2시간,         
2 번 목초지에서 3 번 목초지로 1 시간,            
-3의 시간에 목초지 3에서 목초지 1로,         
-1의 시간에 목초지 3에서 목초지 2로,     
-2의 시간에 목초지 2에서 목초지 1로.         
총 비용은 2 + 1 + −3 + −1 + −2 = −3입니다. 두 번째 테스트 사례에서는 비용이 1000000000 인 목초지 1에서 목초지 2로, 비용이 −1000000000 인 목초지 2에서 목초지 1로가는
도로를 추가 할 수 있습니다. 총 비용은 1000000000 + −1000000000 = 0입니다.

세 번째 테스트 사례에서는 도로를 추가 할 수 없습니다. 총 비용은 0입니다.

<br><br>

### 알고리즘 분류

***

* contructive altorithms
* graphs
* greedy
* shortest paths
* sortings

<br><br>

### Solution

***

문제를 읽고 바로 이해가 되지 않았다. 문제분석을 한 후에 어떤 것을 말하고 어떤 것을 구해야 하는지 깨달은 문제이다.

문제에서 소가 너무 빨리 이동해서 시간을 거슬러 올라갈 수 있다고 한다. 왜 이딴식으로 표현했는지는 모르겠다. 또한 도로를 가로질러 이동하여 시간을 무한히 거슬러 올라갈 수 있는 타임루프에 갇히는 것은 불가능 하다고
한다. 이 부분이 어떤것을 말하는지 바로 파악하지 못했었다.

이 문제는 방향이 존재하는 그래프의 간선에 가중치가 존재한다는 것을 인지해야 한다. 그리고 그 가중치는 선형적인 구조이다. 즉 1 -> 2 -> 3 -> 4 -> ... 식으로 한방향으로 이동이 가능한 구조이다.
그리고 주어진 배열이 1에서 출발해서 i까지 가는데 걸리는 시간을 나타낸다.

예제 `0, 2, 3`을 보면 그래프의 노드는 3개가 존재하고 1 -> 2 -> 3으로 갈수 있으며, 1 -> 2의 간선의 가중치는 2, 2 -> 3으로 가는 간선의 가중치는 1인 것을 알수 있다.

그리고 소는 시간을 거슬러 올라갈 수 있다. 다시 한번 말하지만 왜 이딴식으로 서술했는지는 모르겠다. 어쨋든 이 의미는 현재 소가 있는 기준으로 이전에 지나왔던 모든 노드로 되돌아 갈수 있다는 의미이다. 그리고
이때의 가중치가 1에서 현재 있는 가중치의 누적합의 음수값이 된다.         
1 -> 2: 2, 2 -> 3: 1이었던 위의 예제에서 2에서 시간을 거슬러 올라가면 1로 다시 갈수 있다. 그리고 그 가중치는 1 -> 2의 가중치인 2의 음수값인 -2가 된다.       
3에서 시간을 거슬러 올라간다면 2와 1로 되돌아 갈 수 있다. 2 -> 3: 1이었으므로 -1, 1 -> 3은 3으로 주어졌으므로 -3, 따라서 총은 `Note`에 주어진 대로 -3이 된다.

이 총 합의 최소값을 구해야 하니 주어진 배열을 오름차순으로 정렬한다. 양수의 값이 최소값이 되야 하니 가중치가 양수가 되는 인접한 노드간의 간선을 최소화로 하기 위해서이다.

그리고 각 노드에서 이전 노드로 다시 되돌아 가는 모든 가중치를 더해야 한다.

여기서 만약 0번 노드를 시작으로 1번 노드로 가는 가중치가 d1, 1번 노드에서 2번 노드로 가는 가중치를 d2... 로 하자.

d1 = a[1] - a[0],       
d2 = a[2] - a[1], ....

* 만약 소가 1번 노드로 왔다. (시작 노드는 0이다!!!) 그리고 소는 그 노드에서 0번 노드로 되돌아 갈 수 있다.   
  `d1 - (d1)`

* 소가 1번에서 2번 노드로 왔다. 소는 0번, 1번 노드로 되돌아 갈 수 있다.        
  `d2 - {(d1 + d2) + (d2)}`

* 소가 2번에서 3번노드로 왔다. 소는 0번, 1번, 2번 노드로 되돌아 갈 수 있다.   
  `d3 - {(d1 + d2 + d3) + (d2 + d3) + (d3)}`

* 소가 3번 노드에서 4번노드로 이동했다. 소는 0번, 1번, 2번, 3번 노드로 되돌아 갈 수 있다.      
  `d4 - {(d1 + d2 + d3 + d4 ) + (d2 + d3 + d4) + (d2 + d3 + d4) + (d3 + d4) + (d4)`       
  `a[4] - a[3] - ((a[4] - a[0]) + (a[4] - a[1]) + (a[4] - a[2]) + (a[4] - a[3]))`     
  `a[4] - a[3] - (a[4] * 4 - (a[0] + a[1] + a[2] + a[3])`

위의 과정을 일반화 하여 식을 세우면        
`ans[i] = a[i] - a[i-1] +sum(i-1) - i * a[i];`이 성립한다.

```java
public class Main {
    static long solve(long[] arr, int N) {
        long ans = 0;
        long sum = 0;
        Arrays.sort(arr);
        for (int i = 1; i < N; i++) {
            ans += arr[i] - arr[i - 1] + sum - i * arr[i];
            sum += arr[i];
        }
        return ans;
    }
}
```

> Solution 처음에도 언급했지만 문제 이해가 쉽지 않은 문제였다. 결과적으로 배열에서 현재 위치에서 지나온 모든 노드까지의 가중치를 이중 반복문이 아닌 반복문 한번으로 O(N)으로 푸는 것이 관건인 문제였다.    
> 문제를 이해해도 방법을 한번에 알아내기 어려운 문제였다. contructive algorithms문제를 만날때 위와 같이 가능한 식을 일반화 하는 노력을 해야 될것 같다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R728_D2/C_Great_Graphs.java)