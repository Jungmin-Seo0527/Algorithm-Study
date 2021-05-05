package kakao.internship2020;

import java.util.*;

public class 보석_쇼핑 {
    private final Map<String, Integer> hashMap = new HashMap<>();
    private final Set<String> set = new HashSet<>();

    public int[] solution(String[] gems) {
        set.addAll(Arrays.asList(gems));

        return ans(gems);
    }

    private int[] ans(String[] gems) {
        int left = 0;
        int right = 0;
        int ansLeft = 0, ansRight = 0, ansDist = Integer.MAX_VALUE;
        while (right < gems.length) {
            String rightGem = gems[right];
            if (hashMap.containsKey(rightGem)) {
                hashMap.put(rightGem, hashMap.get(rightGem) + 1);
            } else {
                hashMap.put(rightGem, 1);
            }
            while (left <= right) {
                String leftGem = gems[left];
                if (hashMap.containsKey(leftGem) && hashMap.get(leftGem) > 1) {
                    left++;
                    hashMap.put(leftGem, hashMap.get(leftGem) - 1);
                } else {
                    break;
                }
            }
            if (set.size() == hashMap.size()) {
                int d = right - left;
                if (d < ansDist) {
                    ansDist = d;
                    ansLeft = left;
                    ansRight = right;
                }
            }
            right++;
        }
        return new int[]{ansLeft + 1, ansRight + 1};
    }
}