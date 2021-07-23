# [AquaMoon and Two Arrays](https://codeforces.com/contest/1546/problem/A)

### 난이도

***
800
<br><br>

### 알고리즘 분류

***

* brute force
* greedy

<br><br>

### Solution

***

`a` 배열과 `b`배열이 존재한다. `a`배열에서 서로 다른 두 인덱스 i, j를 뽑았을 때, a[i]--, a[j]++ 를 수행해서 `a`배열이 `b`배열과 같도록(모든 요소가 같다) 할때 수행해야 할 (i,
j) 순서쌍을 구하는 문제

처음부터 a[i] < a[j] 인 경우에 a[i] == b[i] 가 될때까지 a[i]--, a[j]++ 를 수행하면 된다

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R732_D2/A_AquaMoon_and_Two_Arrays.java)
