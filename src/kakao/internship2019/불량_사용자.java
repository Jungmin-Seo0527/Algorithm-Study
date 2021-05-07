package kakao.internship2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 불량_사용자 {
    private String[] user_id, banned_id;
    private List<List<String>> banList = new ArrayList<>();
    private Set<Set<String>> ans = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        init(user_id, banned_id);
        for (int i = 0; i < banList.get(0).size(); i++) {
            Set<String> set = new HashSet<>();
            set.add(banList.get(0).get(i));
            combination(set, 1);
        }

        return ans.size();
    }

    private void combination(Set<String> set, int cur) {
        if (cur == banList.size()) {
            ans.add(set);
            return;
        }
        for (int i = 0; i < banList.get(cur).size(); i++) {
            if (set.contains(banList.get(cur).get(i))) continue;
            Set<String> nextSet = new HashSet<>(set);
            nextSet.add(banList.get(cur).get(i));
            combination(nextSet, cur + 1);
        }
    }

    private void init(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        for (int i = 0; i < banned_id.length; i++) {
            this.banList.add(new ArrayList<>());
        }
        for (int i = 0; i < user_id.length; i++) {
            for (int j = 0; j < banned_id.length; j++) {
                if (matchingId(user_id[i], banned_id[j])) {
                    banList.get(j).add(user_id[i]);
                }
            }
        }
    }

    private boolean matchingId(String user, String ban) {
        if (user.length() != ban.length()) return false;
        for (int i = 0; i < user.length(); i++) {
            char bc = ban.charAt(i);
            char uc = user.charAt(i);
            if (bc == '*') continue;
            if (bc != uc) return false;
        }
        return true;
    }
}