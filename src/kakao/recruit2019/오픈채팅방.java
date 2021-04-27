package kakao.recruit2019;

import java.util.*;

public class 오픈채팅방 {
    private final Map<String, String> hashMap = new HashMap<>();
    private final List<String> opList = new ArrayList<>();

    public String[] solution(String[] record) {
        init(record);
        return ans();
    }

    private String[] ans() {
        String[] ret = new String[opList.size()];
        int retIdx = 0;
        for (String op : opList) {
            String[] opStr = op.split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(hashMap.get(opStr[1])).append("님이 ");
            if (opStr[0].equals("Enter")) sb.append("들어왔습니다.");
            else sb.append("나갔습니다.");
            ret[retIdx++] = sb.toString();
        }

        return ret;
    }

    private void init(String[] record) {

        for (String rec : record) {
            String[] str = rec.split(" ");
            if (str[0].equals("Enter")) {
                opList.add(str[0] + " " + str[1]);
                hashMap.put(str[1], str[2]);
            } else if (str[0].equals("Leave")) {
                opList.add(str[0] + " " + str[1]);
            } else if (str[0].equals("Change")) {
                hashMap.put(str[1], str[2]);
            }
        }
    }
}
