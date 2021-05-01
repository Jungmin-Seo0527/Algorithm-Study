package kakao.recruit2018;

import java.util.*;

public class 뉴스_클러스터링 {

    private final int mod = 65536;
    Map<String, Integer> map1;
    Map<String, Integer> map2;
    Set<String> set = new HashSet<>();

    public int solution(String str1, String str2) {
        int ans = 0;
        int union = 0;
        int inter = 0;

        str1 = str1.toLowerCase().replaceAll("[^a-z]", "@");
        str2 = str2.toLowerCase().replaceAll("[^a-z]", "@");

        map1 = grouping(str1);
        map2 = grouping(str2);

        for (String str : set) {
            if (map1.containsKey(str) && map2.containsKey(str)) {
                union += Math.max(map1.get(str), map2.get(str));
                inter += Math.min(map1.get(str), map2.get(str));
            } else if (map1.containsKey(str)) {
                union += map1.get(str);
            } else {
                union += map2.get(str);
            }
        }

        if (union == 0) {
            ans = mod;
        } else {
            ans = (int) ((double) inter / union * mod);
        }

        return ans;
    }

    private Map<String, Integer> grouping(String word) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length() - 1; i++) {
            char front = word.charAt(i);
            char tail = word.charAt(i + 1);
            if (front == '@' || tail == '@') continue;
            StringBuilder sb = new StringBuilder();
            sb.append(front).append(tail);
            if (map.containsKey(sb.toString())) {
                map.put(sb.toString(), map.get(sb.toString()) + 1);
            } else {
                map.put(sb.toString(), 1);
            }
            set.add(sb.toString());
        }
        return map;
    }
}