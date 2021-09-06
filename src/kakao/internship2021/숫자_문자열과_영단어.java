package kakao.internship2021;

import java.util.HashMap;
import java.util.Map;

public class 숫자_문자열과_영단어 {
    Map<String, String> map = new HashMap<>();

    public int solution(String s) {
        init();
        return ansByStack(s);
    }

    private int ansByStack(String s) {
        int len = s.length();
        StringBuilder ans = new StringBuilder();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                ans.append(c);
            } else {
                str.append(c);
                if (map.containsKey(str.toString())) {
                    ans.append(map.get(str.toString()));
                    str = new StringBuilder();
                }
            }
        }
        return Integer.parseInt(ans.toString());
    }

    private int ansByReplace(String s) {
        s = s.replaceAll("zero", "0")
                .replaceAll("one", "1")
                .replaceAll("two", "2")
                .replaceAll("three", "3")
                .replaceAll("four", "4")
                .replaceAll("five", "5")
                .replaceAll("six", "6")
                .replaceAll("seven", "7")
                .replaceAll("eight", "8")
                .replaceAll("nine", "9");

        return Integer.parseInt(s);
    }

    private void init() {
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
    }
}