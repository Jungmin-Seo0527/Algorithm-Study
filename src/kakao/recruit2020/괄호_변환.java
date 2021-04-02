package kakao.recruit2020;

public class 괄호_변환 {
    String p;

    public String solution(String p) {
        this.p = p;

        return recur(p);
    }

    String recur(String s) {
        if (s.equals("")) return s;
        if (isGood(s)) return s;
        int left = 0, right = 0, i;
        for (i = 0; i <= s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else if (s.charAt(i) == ')') right++;
            if (left == right) break;
        }
        String u = s.substring(0, i + 1) + "";
        String v = s.substring(i + 1, s.length()) + "";

        if (isGood(u)) {
            return u + recur(v);
        } else {
            String temp = "";
            for (i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') temp += ")";
                else temp += "(";
            }
            return "(" + recur(v) + ")" + temp;
        }

    }

    boolean isGood(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left < right) return false;
        }
        return true;
    }
}