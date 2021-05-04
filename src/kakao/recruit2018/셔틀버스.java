package kakao.recruit2018;

import java.util.Arrays;

public class 셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        Arrays.sort(timetable);

        int curBusTime = 0;
        int startBusTime = timeToInt("09:00");
        int lastBusTime = startBusTime + (n - 1) * t;
        int curBusCnt = 0;
        int timeTableIdx = 0;
        int lastCustomerTime = 0;

        int i = 0;
        for (; i < n && timeTableIdx < timetable.length; i++) {
            curBusTime = startBusTime + i * t;
            curBusCnt = 0;
            // System.out.println("curBusTime = " + curBusTime);
            while (curBusCnt < m && timeTableIdx < timetable.length) {
                int customer = timeToInt(timetable[timeTableIdx]);
                if (customer <= curBusTime) {
                    curBusCnt++;
                    timeTableIdx++;
                    lastCustomerTime = Math.max(lastCustomerTime, customer);
                } else break;
            }
        }

        if (curBusTime == lastBusTime && curBusCnt == m) {
            answer = timeToString(lastCustomerTime - 1);
        } else {
            answer = timeToString(lastBusTime);
        }
        return answer;
    }

    private int timeToInt(String time) {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
    }

    private String timeToString(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }
}