package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ2812_크게_만들기
/*
    N자리 숫자중 K개를 지워서 가장 큰 숫자를 만드는 문제
    가장 큰자리 숫자가 큰수, 작은자리숫자가 작은수 즉 내림차순 형태로 숫자를 만드는 것이 중요하다.
    정석은 스택을 이용하는 문제이나 더 편리한 deque를 이용해서 풀어냈다. 설명에선 편리상 stack이라고 칭하겠다.

    주어진 숫자들을 탐색을 한다.
    현재 숫자가 stack의 top 보다 큰 경우에는 내림차순을 성립하지 못하므로 내림차순이 될때까지
    즉 stack 의 top 의 숫자가 현재 숫자보다 클때까지 pop을 해준다.
    단 여기서 pop의 개념은 숫자를 지우는 개념이므로 K번까지 숫자를 지울수 있다는 조건이 존재하므로
    K가 0이 되는순간에는 pop이 불가능 하다
    또한 당연히 stack이 비어있으면 pop이 불가능 하다.

    여기까지 오면 숫자를 K번 지운 가장 큰 숫자를 만들수 있다.
    단 예외가 존재하는데 숫자를 지운 횟수가 K번이 되지 않은채로 끝나는 경우가 있다.
    6 4
    198794
    이 경우 최종적으로 stack에는 994가 남아 있다. 하지만 여기까지 올때 pop을 한 횟수, 즉 숫자를 지운 횟수가
    3번이다. (상식적으로 N이 6이고 지울수 있는 숫자가 4개이면 출력 숫자는 2자리어야 한다.)
    따라서 stack에서 bottom에 있는것부터 출력해야 하는 숫자의 갯수만 출력한다.
    정답 : 99
 */
public class BOJ2812_크게_만들기 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(String str) {
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (K > 0 && !deque.isEmpty() && deque.peekLast() < str.charAt(i)) {
                deque.pollLast();
                K--;
            }
            deque.addLast(str.charAt(i));
        }
        System.out.println(deque.toString());

        StringBuilder sb = new StringBuilder();
        int dequeSize = deque.size();
        for (int i = 0; i < dequeSize - K; i++) {
            sb.append(deque.pollFirst());
        }
        System.out.println(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        solve(input);
    }
}

