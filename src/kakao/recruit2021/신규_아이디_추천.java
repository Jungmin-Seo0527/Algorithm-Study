package kakao.recruit2021;

public class 신규_아이디_추천 {
    public String solution(String new_id) {
        String answer = "";

        new_id = new_id.toLowerCase()
                .replaceAll("[^-_.a-z0-9]", "")
                .replaceAll("[.]{2,}", ".")
                .replaceAll("^[.]|[.]$", "");
        if (new_id.equals("")) {
            new_id += "a";
        } else if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15)
                    .replaceAll("^[.]|[.]$", "");
        }
        while (new_id.length() <= 2) {
            new_id += new_id.charAt(new_id.length() - 1);
        }
        return new_id;
    }
}