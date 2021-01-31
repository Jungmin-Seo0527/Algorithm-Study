package DataStructure_Stack;

import java.io.*;
import java.util.*;

// BOJ9012_괄호
// Stack
// ( 가 입력으로 나오면 push, ) 가 입력으로 나오면 pop을 진행한다.
// pop을 진행해야 하는 과정에서 stack이 비워져 있으면 NO
// 입력을 끝낸후에도 stack에 데이터가 남아있으면 No
// 위의 괄호의 짝이 맞지 않는 상황 그 외에는 모두 YES(괄호의 짝이 맞음)
public class BOJ9012_괄호 {

    static int T;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            String input = br.readLine();
            stack.clear();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '(') stack.push(input.charAt(i));
                else {
                    if (stack.isEmpty()) {
                        stack.push('(');
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            if (stack.size() == 0) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
