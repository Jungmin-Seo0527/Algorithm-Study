# [Alphabetical Strings](https://codeforces.com/contest/1547/problem/B)

### 난이도

***
800
<br><br>

### 알고리즘 분류

***

* greedy
* implementation
* Strings
* two pointers

<br><br>

### Solution

***

투포인터를 응용하면 쉽게 풀리는 문제였다. 우선 시작할 포인터인 `a`의 위치를 찾고 그 왼쪽과 오른쪽으로 인덱스를 옮겨 가면서 다음 알파벳이 위치했는지 확인하면 되는 문제였다.

단 문자열에서 `a`가 존재하지 않는 예외를 잘 생각해야 한다.

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R731_D3/B_Alphabetical_Strings.java)
