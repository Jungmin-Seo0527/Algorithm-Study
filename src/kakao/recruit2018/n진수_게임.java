package kakao.recruit2018;

public class n진수_게임 {
    public String solution(int n, int t, int m, int p) {
        int buf = 0;
        int num = 0;
        StringBuilder ret = new StringBuilder();
        while (t > 0) {
            String transedInt = transInt(num++, n);
            for (int i = 0; i < transedInt.length(); i++) {
                buf++;
                if (buf == p) {
                    ret.append(transedInt.charAt(i));
                    t--;
                    if (t == 0) break;
                }
                if (buf == m) {
                    buf = 0;
                }
            }

        }
        return ret.toString();
    }

    private String transInt(int num, int n) {
        StringBuilder ret = new StringBuilder();
        while (true) {
            int moc = num / n;
            int na = num % n;
            if (na < 10) {
                ret.append(na);
            } else {
                char c = (char) ('A' + na - 10);
                ret.append(c);
            }
            if (moc == 0) break;
            num = moc;
        }
        // System.out.println(temp + " " + n + "->" + ret.reverse().toString());
        return ret.reverse().toString();
    }
}