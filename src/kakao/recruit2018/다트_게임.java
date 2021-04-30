package kakao.recruit2018;

import java.util.ArrayList;
import java.util.List;

public class 다트_게임 {
    List<Integer> scoreList = new ArrayList<>();

    public int solution(String dartResult) {
        int answer = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            char cur = dartResult.charAt(i);
            if (cur == 'S') {
                continue;
            } else if (cur == 'D') {
                int score = scoreList.get(scoreList.size() - 1);
                score = (int) Math.pow(score, 2);
                scoreList.set(scoreList.size() - 1, score);
            } else if (cur == 'T') {
                int score = scoreList.get(scoreList.size() - 1);
                score = (int) Math.pow(score, 3);
                scoreList.set(scoreList.size() - 1, score);
            } else if (cur == '*') {
                scoreList.set(scoreList.size() - 1, scoreList.get(scoreList.size() - 1) * 2);
                if (scoreList.size() >= 2) {
                    scoreList.set(scoreList.size() - 2, scoreList.get(scoreList.size() - 2) * 2);
                }
            } else if (cur == '#') {
                scoreList.set(scoreList.size() - 1, scoreList.get(scoreList.size() - 1) * -1);
            } else {
                if (i > 0 && dartResult.charAt(i - 1) == '1') {
                    int n = 10 + cur - '0';
                    scoreList.set(scoreList.size() - 1, n);
                } else {
                    scoreList.add(cur - '0');
                }
            }
        }

        for (Integer e : scoreList) {
            answer += e;
        }
        return answer;
    }
}