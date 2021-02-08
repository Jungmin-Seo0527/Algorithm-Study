package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1285_동전_뒤집기
/*
    0과 1을 뒤집어 모두 같은 수가 되도록 할때 뒤집는 횟수를 최소로 하는 방법을 찾는 문제
    단 연속되어있는 숫자들이 같은 수일 경우에는 한번에 뒤집을 수 있다.
    즉 0001100 은 가운데 들어있는 11을 한번에 뒤집어서 모든 수가 같도록 만들 수 있다.

    이 문제는 0 덩어리, 1 덩어리의 갯수중 적은 값이 답이다.
    연속되어 있는 숫자들 즉 덩어리들은 한번에 뒤집기가 가능하니 그 덩어리들의 갯수만큼만 뒤집으면
    0이든 1이든 모두 같은 숫자로 만들수 있다.

 */
public class BOJ1285_동전_뒤집기 {
    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char cur = input.charAt(0);
        int[] arr = new int[2];
        for (int i = 1; i < input.length(); i++) {
            if (cur == input.charAt(i)) continue;
            arr[cur - '0']++;
            cur = input.charAt(i);
        }
        arr[cur - '0']++;
        System.out.println(Math.min(arr[0], arr[1]));
    }
}

