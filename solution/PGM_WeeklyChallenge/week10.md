# [10주차](https://programmers.co.kr/learn/courses/30/lessons/87377)

### 난이도

***
L2
<br><br>

### 알고리즘 분류

***

* 구현
* 선분 교차

<br><br>

### Solution

***

주어진 선분의 교차점에 별을 찍는 문제이다. 친절하게 선분의 교차점을 구하는 식을 제공해 준다.

그림을 그려야 할 좌표의 크기는 주어지지 않는다. 따라서 교차점의 x값의 최소값, 최대값, y값의 최소값, 최대값의 정보를 토대로 좌표의 크기를 정하면 된다.

이 문제에서 해맨 두가지 포인트가 있다.

* x, y좌표 vs r, c 좌표
* 자료형 초과 (int -> long)

x, y좌표와 r, c좌표는 서로 반대로 생각해야 한다. 또한 x, y좌표는 좌하단이 0이다.(단 이 문제는 좌표의 중앙을 0점으로 잡는다.)
그림을 그릴때는 y값의 최대값부터 아래로 내려가면서, x값의 최소값부터 그리면 평소 r, c좌표로 그리는 것 처럼 그릴 수 있다.

자료형도 잘 확인해야 한다. 직선 방정식의 상수의 최대값이 10,000이다. 교차점을 구하는 공식에서 두 상수를 곱하는 과정이 존재한다. 즉 10,000 * 10,000 이 되는데 이는 int 범위를 초과하므로
long으로 코드를 작성하는 것이 좋다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/PGM_WeeklyChallenge/week10.java)
