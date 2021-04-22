package greedy;

import java.io.*;

// BOJ12904_A와_B
/*
    문자열 두개를 같게 만들수 있는지 판별하는 문제
    문자열은 A와 B로만 이루어져 있고 두개의 연산만 가능하다.
    1) 문자열 뒤에 A를 추가
    2) 문자열을 뒤집고 뒤에 B를 추가

    연산을 잘 살펴보면 결국에는 어떤 연산을 하던지 뒤에 알파벳을 추가한다.
    즉 짧은 문자열로 연산을 하여 긴 문자열로 만들려고 하지말고 긴 문자열을 가지고 연산의 역행으로 짧은 문자열을 만드는 방법으로
    정답 도출이 가능하다.
    연산을 보면 두 연산 모두 A 혹은 B를 추가한다. 즉 긴 문자열의 입장에서 보면 A가 맨 뒤에 있을때는 이전 문자열에서 그대로 A를 추가
    B 문자가 맨 뒤에 있는 경우에는 이전 문자열에서 뒤집기를 수행후 B 문자를 추가한 것이다.
    그렇다면 역행으로 맨 뒤에 A가 있으면 그대로 A를 지워주고
    B가 있다면 B를 지워주고 뒤집기를 수행하면 된다.
    이 작업을 짧은 문자열의 길이가 될때까지 수행한다.
    그 결과 짧은 문자열과 비교하여 같으면 1, 다르면 0을 출력하면 된다.
 */
public class BOJ12904_A와_B {
    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(String str1, String str2) {
        while (str2.length() > str1.length()) {
            char last = str2.charAt(str2.length() - 1);
            str2 = str2.substring(0, str2.length() - 1);
            if (last == 'B') str2 = new StringBuffer(str2).reverse().toString();
        }
        if (str1.compareTo(str2) == 0) System.out.println(1);
        else System.out.println(0);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strA = br.readLine();
        String strB = br.readLine();
        solve(strA, strB);
    }
}

