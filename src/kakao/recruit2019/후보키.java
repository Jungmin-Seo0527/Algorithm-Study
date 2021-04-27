package kakao.recruit2019;

import java.util.*;

public class 후보키 {

    private String[][] relation;
    private int ans, len;
    private final List<Integer> candidateKeyList = new ArrayList<>();

    public int solution(String[][] relation) {
        init(relation);

        return ans();
    }

    private int ans() {
        Queue<Integer> que = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        List<Integer> candidateKeyList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            que.add(1 << i);
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (isCandidateKey(cur, candidateKeyList)) {
                candidateKeyList.add(cur);
                continue;
            }
            for (int i = 0; i < len; i++) {
                if ((cur & 1 << i) == 0 && cur < 1 << i) {
                    int next = cur | 1 << i;
                    if (set.contains(next)) continue;
                    que.add(next);
                    set.add(next);
                }
            }
        }

        return candidateKeyList.size();
    }

    private boolean isCandidateKey(int bit, List<Integer> list) {
        Set<String> set = new HashSet<>();

        for (Integer e : list) {
            if ((bit | e) == bit) return false;
        }

        for (String[] tuple : relation) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                if ((bit & (1 << i)) != 0) {
                    sb.append(tuple[i]).append(" ");
                }
            }
            if (set.contains(sb.toString())) {
                return false;
            } else {
                set.add(sb.toString());
            }
        }
        return true;
    }


    private void init(String[][] relation) {
        this.relation = relation;
        this.len = relation[0].length;
    }
}
