package greedy;

import java.io.*;

// BOJ1541_잃어버린_괄호
// Greedy Algorithm
// + 와 - , 괄호들로 이우러져 있는 식에서 괄호들을 모두 없애고 새롭게 괄호를 넣어서 최소값이 되도록 식을 만드는 문제
// 처음 - 를 처음 만나기 전까지의 숫자들은 모두 더하고 '-'를 만난 이후의 숫자들은 모두 빼주면 된다.
// 식에서 최소값을 구하기 위해서는 '-' 끼리 괄호를 묶어버리면 된다.
// 예) 1+2-3-4+5-6+7+8-9-10 -> 1+2-(3)-(4+5)-(6+7+8)-(9)-(10)  최소값이 된다.
public class BOJ1541_잃어버린_괄호 {
    static String input;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ret = 0;
        int num = 0;
        int idx = 0;
        for (; idx < input.length(); idx++) {
            if (input.charAt(idx) == '-') {
                break;
            } else if (input.charAt(idx) == '+') {
                ret += num;
                num = 0;
            } else num = num * 10 + (int) input.charAt(idx) - '0';
        }
        ret += num;
        if (idx == input.length()) {
            System.out.println(ret);
            return;
        }
        idx++;
        num = 0;
        for (; idx < input.length(); idx++) {
            if (input.charAt(idx) == '-' || input.charAt(idx) == '+') {
                ret = ret - num;
                num = 0;
            } else num = num * 10 + (int) input.charAt(idx) - '0';
        }
        ret = ret - num;
        System.out.println(ret);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
    }
}

