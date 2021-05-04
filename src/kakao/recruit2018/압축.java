package kakao.recruit2018;

import java.util.*;

public class 압축 {
    Map<String, Integer> map = new HashMap<>();

    public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<>();
        int lastIdxNum = 0;

        for (char i = 'A'; i <= 'Z'; i++) {
            map.put(i + "", ++lastIdxNum);
        }

        int len = msg.length();
        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(i));
            for (int j = i + 1; j < len; j++) {
                if (!map.containsKey(sb.toString() + msg.charAt(j))) {
                    map.put(sb.toString() + msg.charAt(j), ++lastIdxNum);
                    i = j - 1;
                    break;
                } else i = j;
                sb.append(msg.charAt(j));
            }
            ans.add(map.get(sb.toString()));
        }

        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}