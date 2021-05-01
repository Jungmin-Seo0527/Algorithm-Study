package kakao.recruit2018;

import java.util.*;

public class 방금그곡 {
    private String M;
    private String[] musicInfos;

    public String solution(String m, String[] musicinfos) {
        init(m, musicinfos);
        return ans();
    }

    private String ans() {
        this.M = transCode(this.M, -1);
        PriorityQueue<Music> musicPriorityQueue = new PriorityQueue<>(new Comparator<Music>() {
            @Override
            public int compare(Music o1, Music o2) {
                int time1 = o1.endTime - o1.startTime;
                int time2 = o2.endTime - o2.startTime;
                if (time1 != time2) return time2 - time1;
                else return o1.startTime - o2.startTime;
            }
        });

        for (String music : musicInfos) {
            String[] info = music.split(",");
            int startTime = getIntTime(info[0]);
            int endTime = getIntTime(info[1]);
            String name = info[2];
            String code = info[3];
            String transedCode = transCode(code, endTime - startTime);
            if (transedCode.contains(this.M)) {
                musicPriorityQueue.add(new Music(startTime, endTime, name));
            }
        }
        if (musicPriorityQueue.isEmpty()) return "(None)";
        else return musicPriorityQueue.poll().name;
    }

    private void init(String M, String[] musicinfos) {
        this.M = M;
        this.musicInfos = musicinfos;
    }

    private String transCode(String code, int time) {
        String ret = code.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");
        if (time == -1) return ret;

        StringBuilder sb = new StringBuilder();
        int len = ret.length();
        for (int i = 0; i < time; i++) {
            sb.append(ret.charAt(i % len));
        }
        return sb.toString();
    }

    private int getIntTime(String time) {
        String[] timeSplie = time.split(":");
        if (timeSplie.length != 2) return -1;
        int h = Integer.parseInt(timeSplie[0]);
        int m = Integer.parseInt(timeSplie[1]);
        return h * 60 + m;
    }

    private static class Music {
        int startTime, endTime, idx;
        String name;

        public Music(int startTime, int endTime, String name) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.name = name;
        }
    }

}