# [Wonderful Coloring 1](https://codeforces.com/contest/1551/problem/B1)

### 난이도

***
800
<br><br>

### 알고리즘 분류

***

* greedy
* sorting

<br><br>

### Solution

***

문자열의 각 알파벳에 서로 다른 2가지 색을 칠할때 최대한 많은 알파벳에 칠하는 경우를 구하는 문제이다.

우선 출현 빈도가 2번 이상인 알파벳은 2가지 색을 모두 칠할 수 있다. 하지만 2번 미만의 빈도를 가진 알파벳은 그 갯수를 모두 더해서 2로 나누어 각각의 알파벳에 2가지 색을 나누어 칠할 수 있다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R734_D3/B1_Wonderful_Coloring_1.java)
