package kakao.recruit2021;

import java.util.*;

public class 순위_검색 {
    String[] info, query;
    List<List<Integer>> list = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    int mapToListIndex;

    public int[] solution(String[] info, String[] query) {
        init(info, query);
        int[] ans = new int[query.length];

        for (String s : info) {
            String[] temp = stringToStringArr(s);
            permutation(temp, "", 0);
        }

        sortList();

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll("and ", "");
            String[] temp = stringToStringArr(query[i]);
            int score = Integer.parseInt(stringToStringArr(query[i])[temp.length - 1]);
            query[i] = query[i].replaceAll("[0-9 ]", "");
            ans[i] = count(query[i], score);
        }

        return ans;
    }

    private int count(String query, int score) {
        int idx = 0;
        if (!map.containsKey(query)) return 0;
        else idx = map.get(query);
        int start = 0;
        int end = list.get(idx).size();
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (list.get(idx).get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return list.get(idx).size() - end;
    }

    private void sortList() {
        for (List<Integer> scoreList : list) {
            Collections.sort(scoreList);
        }
    }

    private void permutation(String[] str, String cur, int idx) {
        if (idx == str.length - 1) {
            int score = Integer.parseInt(str[idx]);
            if (map.containsKey(cur)) {
                list.get(map.get(cur)).add(score);
            } else {
                map.put(cur, mapToListIndex);
                list.add(new ArrayList<>());
                list.get(mapToListIndex).add(score);
                mapToListIndex++;
            }
            return;
        }
        String next = new String(cur + str[idx]);
        String next2 = new String(cur + "-");
        permutation(str, next, idx + 1);
        permutation(str, next2, idx + 1);
    }

    private String[] stringToStringArr(String info) {
        int len = info.length();
        String[] ret = new String[5];
        StringTokenizer st = new StringTokenizer(info);
        for (int i = 0; i < 5; i++) {
            ret[i] = new String(st.nextToken());
        }
        return ret;
    }

    private void init(String[] info, String[] query) {
        this.info = info;
        this.query = query;
    }
}