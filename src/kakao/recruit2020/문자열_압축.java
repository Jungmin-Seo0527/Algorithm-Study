package kakao.recruit2020;

public class 문자열_압축 {
    private String s;

    public int solution(String s) {
        init(s);
        int ans = s.length();
        for (int i = 1; i < s.length(); i++) {
            ans = Math.min(ans, zip(i));
        }
        return ans;
    }

    private int zip(int n) {
        int i = 0, ret = 0, cnt = 0;
        String prev = s.substring(0, n);
        String cur = "";
        for (i = 0; i + n <= s.length(); i += n) {
            cur = s.substring(i, i + n);
            if (prev.equals(cur)) {
                cnt++;
            } else {
                ret += n;
                prev = new String(cur);
                if (cnt != 1) {
                    ret += (int) Math.log10(cnt) + 1;
                    cnt = 1;
                }
            }
        }
        ret += n;
        if (cnt > 1) ret += (int) Math.log10(cnt) + 1;
        ret += s.substring(i, s.length()).length();
        return ret;
    }

    private void init(String s) {
        this.s = s;
    }
}