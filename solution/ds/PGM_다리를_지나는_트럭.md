# [다리를 지나는 트럭](https://programmers.co.kr/learn/courses/30/lessons/42583)

### 난이도

***
Level 2
<br><br>

### 알고리즘 분류

***

* 큐

<br><br>

### Solution

***

시간이 지남에 따라 다리위의 상태를 큐로 표현할 수 있다면 쉽게 풀수 있는 문제        
단 이미 다리위에 있는 차량이 움직이는 것을 표현하기 위해서 시간이 지남에 따라 `que.poll()`을 시켜주며 만약 그 값이 차량인 경우(0보다 큰 경우)에 상태 변화(현재 다리에 있는 차량의 갯수, 다리위의
차량의 무게의 합)를 갱신해 준다.      
마지막 단계에서는 아무런 의미가 없는 값인 0을 계속 push하여 차량의 이동을 표현한다.

```java
public class Solution {
    public static void main(String[] args) {
        while (truckIdx < truck_weights.length) {
            time++;
            int out = que.poll();
            if (out > 0) truckCnt--;
            curWeight -= out;
            if (curWeight + truck_weights[truckIdx] <= weight && truckCnt + 1 <= bridge_length) {
                curWeight += truck_weights[truckIdx];
                que.add(truck_weights[truckIdx++]);
                truckCnt++;
            } else que.add(0);
        }
    }
}
```

위 코드가 설명을 코드로 작성한 부분이다. 반복문의 전처리, 후처리로 poll과 add를 수행하며 단 다리에 차량이 진입 가능한 경우에만 차량을 add 시켜주면 된다. 주의할 점은 truckIdx가 마지막 인덱스
다음을 가리킬때 반복문을 탈출하며, 그 상태에서는 다리위에 차량이 모두 빠져나오지 못한 경우가 존재하므로 que의 사이즈 만큼만 time을 더해주면 된다. (마지막 차량이 다리에 진입한 시점에서 반복문을 탈출하기
때문에 큐의 사이즈만 더해주면 만사 해결)

**대기하고 있는 트럭의 순서는 변경할 수 없다!!!**

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/ds/PGM_다리를_지나는_트럭.java)
