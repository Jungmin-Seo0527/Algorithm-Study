# [Wonderful Coloring - 2](https://codeforces.com/contest/1551/problem/B2)

### 난이도

***
??
<br><br>

### 알고리즘 분류

***

* binar search
* constructive algorithm
* data structure
* greedy

<br><br>

### Solution

***

알고리즘 분류에서 특히 그리디 방식으로 풀어냈다. 그리고 코테에서도 나올법한 좋은 문제인것 같다.      
숫자가 주어진 배열에서 색깔을 칠한다. 임의의 같은 색깔 두개를 고르면 그 숫자는 항상 다르다. 그리고 모든 색깔의 칠해진 갯수는 같다.

우선 배열에 나오는 각 숫자들 중 색깔의 갯수인 k 개 이상인 숫자들은 k개 색을 하나씩 칠할 수 있다. 문제는 k개 미만으로 나오는 숫자들이다. 문제에서 가장 색을 많이 칠하는 경우를 출력하라고 했으니 k개 미만인
숫자들도 색을 칠해야 한다.

우선 k개 미만인 색들을 모두 나열해서 색을 차례로 모두 칠하면 된다. 칠하는 k개의 색을 완전히 한바퀴 돌 수 있을 때 까지 칠해야 한다. 즉 k개 미만인 숫자들을 색을 +1 하면서 계속 칠하다가 마지막 색칠하는
숫자의 색은 k 색이어야 한다.

여기까지는 라운드에서도 생각해낸 방법이다. 이 문제는 방법은 정확하게 알아냈지만 구현을 해내지 못했다.

우선 이전 문제에서 각 숫자들의 갯수를 저장하는 배열에 너무 포커싱이 잡혀 있었다. 이 문제는 각 숫자들에 칠해지는 색까지 구현해야 했다. 즉 각 숫자들의 빈도수와, 그 인덱스까지 함께 가져가면 어렵지 않게 풀수
있는 문제였다. 하지만 인덱스를 가져가는 생각을 하지 못해서 구현을 해내지 못했다.

```java
public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("input/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            List<List<Integer>> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(st.nextToken());
                list.get(a - 1).add(i);
            }

            out.append(solve(n, k, list)).append("\n");
        }
        System.out.println(out);
    }
}
```

위 코드에서 중요한 것은 2차원 배열을 이용해서 **각 숫자들의 인덱스를 저장**한 점이다. ArrayList 형식으로 저장하여 각 배열의 사이즈는 숫자의 빈도수, 그리고 각 List의 숫자들이 출현 인덱스이다.

```java
public class Main {
    static String solve(int n, int k, List<List<Integer>> list) {
        /**
         * 색을 칠할 모든 숫자들의 갯수(=cnt) 구하기 
         */
        int cnt = 0;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            cnt += Math.min(k, list.get(i).size());
        }

        /**
         * 색을 완전하게 한바퀴 돌수 있도록 나머지는 빼준다.
         */
        cnt -= cnt % k;

        /**
         * 색이 k개 이상인 숫자들은 색을 1 ~ k까지 한바퀴 돌아준다.
         */
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (list.get(i).size() >= k) {
                for (int j = 0; j < k; j++) {
                    ret[list.get(i).get(j)] = c++ % k + 1;
                }
            }
        }

        /**
         * 색이 k개 미만인 숫자들을 따로 모아서 색을 1 ~ k 까지, 색이 칠해진 숫자들이 cnt가 될 때까지 칠한다.
         */
        for (int i = 0; i < n; i++) {
            if (list.get(i).size() < k) {
                for (int j = 0; j < list.get(i).size() && c < cnt; j++) {
                    ret[list.get(i).get(j)] = c++ % k + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ret[i]).append(" ");
        }
        return sb.toString();
    }
}
```

* 모든 숫자들의 빈도수를 조사하여 더해준다. 단 빈도수가 k개 이상인 경우에는 k 개만 더해준다.
    * 그리고 `cnt -= cnt % k`를 해준다. k개 만큼 나누어 나머지를 뺌으로써 k 색을 완전히 돌수 있도록 cnt를 만들어 준다. **이 `cnt`가 곧 색을 칠할 수 있는 모든 숫자들의 갯수가
      된다.**

* k개 이상인 숫자들은 1 ~ k 색을 한번씩 칠해준다.
* k개 미만인 숫자들은 나머지 cnt 갯수만큼 색을 1 ~ k까지 돌면서 순차적으로 칠해준다.
    * 같은 숫자들에 한번에 색을 순차적으로 칠하기 때문에, 그리고 이 숫자들은 k개 미만이기 때문에 절대 중복되는 색을 칠하지 않는다.
    * **이 부분에서 막혓었다. 결국 k개 마만의 숫자들을 모아서 한 숫자들에 한번에 색을 돌려야 했다. 처음 입력으로 주어진 배열에서 칠하려고 하니 계속 중복되는 색을 칠하게 되었다. 키포인트는 각 숫자들의
      인덱스를 저장한 2차원 배열이었다.**

<br><br>

### [전체 코드](https://github.com/Jungmin-Seo0527/CodingTest/blob/main/src/codeforces/R734_D3/B2_Wonderful_Coloring_2.java)
