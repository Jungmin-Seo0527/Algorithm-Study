package kakao.recruit2021;

import java.util.*;

public class 메뉴_리뉴얼 {
    String[] orders, ans;
    int[] course;
    List<List<String>> list = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        for (String order : orders) {
            for (int j = 0; j < order.length(); j++) {
                permutation(order, "", j, 0);
            }
        }
        count();

        return ans;
    }

    void count() {
        List<String> ret = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < course.length; i++) {
            int cnt = course[i];
            int max = 0;
            for (String s : list.get(cnt)) {
                max = Math.max(max, map.get(s));
            }
            if (max == 1) continue;
            for (String s : list.get(cnt)) {
                if (map.get(s) == max && set.add(s)) {
                    ret.add(s);
                }
            }
        }
        Collections.sort(ret);
        ans = new String[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            ans[i] = ret.get(i);
        }
    }

    void permutation(String string, String prev, int idx, int curDepth) {
        String cur = new String(prev + string.charAt(idx));
        for (int i = 0; i < course.length; i++) {
            if (cur.length() == course[i]) {
                list.get(cur.length()).add(cur);
                if (map.containsKey(cur)) {
                    map.put(cur, map.get(cur) + 1);
                } else {
                    map.put(cur, 1);
                }
            }
        }
        if (cur.length() == course[course.length - 1]) {
            return;
        }
        for (int i = idx + 1; i < string.length(); i++) {
            permutation(string, cur, i, curDepth + 1);
        }
    }

    void init(String[] orders, int[] course) {
        this.orders = orders;
        this.course = course;


        for (int i = 0; i <= course[course.length - 1]; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < orders.length; i++) {
            char[] orderChar = orders[i].toCharArray();
            Arrays.sort(orderChar);
            orders[i] = Arrays.toString(orderChar).replaceAll("[\\[ ,\\]]", "");
        }
    }
}