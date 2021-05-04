package kakao.recruit2018;

import java.io.File;
import java.util.Arrays;

public class 파일명_정렬 {
    public String[] solution(String[] files) {
        File[] fileArr = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            fileArr[i] = new File(files[i]);
        }
        Arrays.sort(fileArr);

        String[] answer = new String[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            answer[i] = fileArr[i].fileName;
        }
        return answer;
    }

    private static class File implements Comparable<File> {
        String fileName, head;
        int num;

        public File(String file) {
            int i = 0;
            StringBuilder sb = new StringBuilder();
            StringBuilder headSb = new StringBuilder();

            for (; i < file.length(); i++) {
                char c = file.charAt(i);
                if (c >= '0' && c <= '9') break;
                headSb.append(c);
            }
            this.head = headSb.toString().toLowerCase();

            for (; i < file.length(); i++) {
                char c = file.charAt(i);
                if (c < '0' || c > '9') break;
                sb.append(c);
            }
            this.fileName = file;
            this.num = Integer.parseInt(sb.toString());
        }

        public int compareTo(File f) {
            if (!this.head.equals(f.head)) {
                return this.head.compareTo(f.head);
            } else {
                return this.num - f.num;
            }
        }
    }

}