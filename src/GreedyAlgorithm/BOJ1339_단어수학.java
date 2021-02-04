package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1339_단어수학
// 주어진 알파벳 문자열에서 각 알파벳이 서로 다른 숫자를 할당받는다
// 입력대로 숫자를 대입해서 그 숫자들을 더한값이 최대가 되는 값을 구하는 문제
// 조건에서 모든 단어에 포함되어 있는 알파벳은 최대 10개이고, (이는 0부터 9까지 할당받기 위함)
// 수의 최대 길이가 8이다. 이는 Integer 이내에서 풀어낼수 있다.
// N 즉 단어의 갯수는 10 이하이다.
// 이 조건에서 보다 더 큰자리에 더 큰수를 할당해주어야 한다는것을 알수 있다.
// 만약 AB 에서 A를 1를 주고 B를 2를 준다. 허나 B가 1을 자리에 있는 단어들이 11개고 10의 자리에 있는 알파벳은 모두 다른경우
// A는 1, B는 2인 경우가 A가 2, B가 1인 경우보다 더 크게 된다.
// 하지만 단어의 갯수가 10개 미만이므로 더 적은 자리의 숫자들을 아무리 더해봐도 더 큰자리의 수를 넘지 못한다.
// 따라서 결론은 자릿수가 클수록 높은 수를 할당받고 만약 같은 자리수라면 나다타는 빈도가 더 잦은 알파벳이 더 큰 수를 할당받는다.

// 각각의 가장 높은 자리수, 그리고 빈도수를 저장하는 방법이 있다.
// 하지만 더 좋은 방법이 입력할때마다 그 알파벳을 1을 할당받는다고 가정하고 그자리 그대로 더해준다.
// 즉 ABC 이면 A=100, B=10, C=1 이라고 하고 저장해준다.
// 이 값이 가장 큰 알파벳이 가장 큰 자리의 수를 할당받으면 된다.
// 이 방법으로 반복문을 최소화 하여 가장 큰 자리의 알파벳과 각각의 빈도수를 구할 수 있다.
public class BOJ1339_단어수학 {
    static int N;
    static int arr[];

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[26];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char inputChar = input.charAt(j);
                arr[inputChar - 'A'] += Math.pow(10, input.length() - 1 - j);
            }
        }
        Arrays.sort(arr);

        int num = 9;
        int ret = 0;
        for (int i = 25; i >= 0; i--) {
            if (arr[i] != 0) ret += arr[i] * num--;
        }
        System.out.println(ret);
    }
}

