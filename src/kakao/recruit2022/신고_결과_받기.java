package kakao.recruit2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 신고_결과_받기 {

    String[] id_list;
    String[] report;
    Map<String, Set<String>> map = new HashMap<>();
    Map<String, Integer> countMap = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        init(id_list, report);
        for (int i = 0; i < report.length; i++) {
            String[] splitedReport = splitName(report[i]);
            Set<String> reportSet = map.get(splitedReport[0]);
            if (!reportSet.contains(splitedReport[1])) {
                reportSet.add(splitedReport[1]);
                countMap.put(splitedReport[1], countMap.get(splitedReport[1]) + 1);
            }
        }
        return getAnswer(id_list, k);
    }

    private int[] getAnswer(String[] id_list, int k) {
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            Object[] arr = map.get(id_list[i]).toArray();
            int cnt = 0;

            for (int j = 0; j < arr.length; j++) {
                if (countMap.getOrDefault((String) arr[j], 0) >= k) {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }

    public void init(String[] id_list, String[] report) {
        this.id_list = id_list;
        this.report = report;

        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], new HashSet<>());
            countMap.put(id_list[i], 0);
        }
    }

    private String[] splitName(String report) {
        return report.split(" ");
    }
}