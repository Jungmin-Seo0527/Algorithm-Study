package kakao.internship2019;

import java.util.*;

public class 튜플 {
    public int[] solution(String s) {
        List<Integer> ans = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        s = s.substring(1, s.length() - 1);
        String[] split = s.split("},");
        for (String ss : split) {
            ss = ss.replaceAll("[{}]", "");
            String[] ssSplit = ss.split(",");
            int[] numArr = new int[ssSplit.length];
            for (int i = 0; i < numArr.length; i++) {
                numArr[i] = Integer.parseInt(ssSplit[i]);
            }
            list.add(numArr);
        }

        list.sort(Comparator.comparingInt(n -> n.length));
        // list.sort((n1, n2) -> Integer.compare(n1.length, n2.length));

        for (int[] l : list) {
            for (int j : l) {
                if (!set.contains(j)) {
                    set.add(j);
                    ans.add(j);
                }
            }
        }

        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}