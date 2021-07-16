# [Shortest Path with Obstacle](https://codeforces.com/contest/1547/problem/A)

### 난이도

***
800
<br><br>

### 알고리즘 분류

***

* implementation
* math

<br><br>

### Solution

***

두 좌표가 주어지고, 단 하나의 이동할 수 없는 좌표가 주어질 때 두 좌표의 최단거리를 구하는 문제

만약 이동 불가능한 좌표(`F`)가 없다고 생각해 보면 주어진 두 좌표(`A`, `B`)의 최단 거리는 일직선의 경로이다. 그렇다면 최단 거리가 변경되는 경우는 `F`좌표가 `A`와 `B`의 일직선 경로 중간에
존재할 때 이다. 이 경우 일직선상의 최단 경로에서 `F`좌표를 돌아서 가는 경로를 더해주면 된다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R731_D3/A_Shortest_Path_with_Obstacle.java)
