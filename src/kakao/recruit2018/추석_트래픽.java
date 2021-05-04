package kakao.recruit2018;

public class 추석_트래픽 {
    public int solution(String[] lines) {
        int answer = 0;
        Traffic[] traffic = new Traffic[lines.length];
        for (int i = 0; i < lines.length; i++) {
            traffic[i] = new Traffic(lines[i]);
        }

        for (int i = 0; i < traffic.length; i++) {
            Traffic cur = traffic[i];
            float end = cur.end + 0.999f;
            int cnt = 0;
            for (int j = i; j < traffic.length; j++) {
                if (traffic[j].start <= end) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    private class Traffic {
        String date;
        float start, end, time;

        public Traffic(String input) {
            String[] inputSplit = input.split(" ");
            this.date = inputSplit[0];
            this.end = timeToFloat(inputSplit[1]);
            this.time = Float.parseFloat(inputSplit[2].replaceAll("[s]", ""));
            this.start = end - time + 0.001f;
        }

        private float timeToFloat(String time) {
            String[] splitTime = time.split(":");
            return Float.parseFloat(splitTime[0]) * 3600 +
                    Float.parseFloat(splitTime[1]) * 60 +
                    Float.parseFloat(splitTime[2]);
        }
    }
}