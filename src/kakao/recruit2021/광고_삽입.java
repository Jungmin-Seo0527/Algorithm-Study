package kakao.recruit2021;

import java.util.Arrays;

public class 광고_삽입 {
    String play_time, adv_time;
    String[] logs;
    long[] timeTable;
    int maxTime;

    public String solution(String play_time, String adv_time, String[] logs) {
        init(play_time, adv_time, logs);
        logsToTimeTable();
        return ans();
    }

    private String ans() {
        int start = 0;
        int end = timeToInt(adv_time);
        long sum = 0;
        long maxSum = 0;
        int ret = 0;
        for (int i = 0; i < end; i++) {
            sum += timeTable[i];
        }
        maxSum = sum;

        while (end <= maxTime) {
            sum = sum + timeTable[end++] - timeTable[start++];
            if (maxSum < sum) {
                maxSum = sum;
                ret = start;
            }
        }
        return intToTime(ret);
    }

    private void logsToTimeTable() {
        for (String t : logs) {
            String[] time = t.split("-");
            timeTable[timeToInt(time[0])]++;
            timeTable[timeToInt(time[1])]--;
        }
        for (int i = 1; i < maxTime; i++) {
            timeTable[i] += timeTable[i - 1];
        }
    }

    private int timeToInt(String time) {
        String[] timeArr = time.split(":");
        int ret = 0;
        int temp = 3600;
        for (String s : timeArr) {
            ret += Integer.parseInt(s) * temp;
            temp /= 60;
        }
        return ret;
    }

    private String intToTime(int n) {
        return String.format("%02d:%02d:%02d", n / 3600, (n / 60) % 60, n % 60);
    }

    private void init(String play_time, String adv_time, String[] logs) {
        this.play_time = play_time;
        this.adv_time = adv_time;
        this.logs = logs;
        this.maxTime = timeToInt(play_time);
        timeTable = new long[maxTime + 1];
    }
}