package kakao.recruit2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class 매칭점수 {
    List<WebPage> webPageList = new ArrayList<>();
    List<String[]> outLinkedUrlList = new ArrayList<>();
    HashMap<String, Integer> urlToListIdx = new HashMap<>();

    public int solution(String word, String[] pages) {
        int answer = 0;
        for (int i = 0; i < pages.length; i++) {
            WebPage curWebPage = new WebPage();
            curWebPage.url = findUrl(pages[i]);
            urlToListIdx.put(curWebPage.url, i);
            outLinkedUrlList.add(findOutLinkUrl(pages[i]));
            curWebPage.basicScore = searchWord(word, pages[i]);
            curWebPage.outLinkedCnt = outLinkedUrlList.get(i).length;
            webPageList.add(curWebPage);
        }

        int idx = 0;
        for (String[] strArr : outLinkedUrlList) {
            for (String url : strArr) {
                if (!urlToListIdx.containsKey(url)) continue;
                int urlIdx = urlToListIdx.get(url);
                webPageList.get(urlIdx).linkScore += ((double) webPageList.get(idx).basicScore / webPageList.get(idx).outLinkedCnt);
            }
            idx++;
        }

        for (int i = 0; i < webPageList.size(); i++) {
            webPageList.get(i).matchingScore = webPageList.get(i).basicScore + webPageList.get(i).linkScore;
        }

        double max = 0.0d;
        for (int i = 0; i < webPageList.size(); i++) {
            if (webPageList.get(i).matchingScore > max) {
                max = webPageList.get(i).matchingScore;
                answer = i;
            }
        }

        return answer;
    }

    private String findUrl(String page) {
        int headTagStartIdx = page.indexOf("<head>");
        int metaTagStartIdx = page.indexOf("<meta", headTagStartIdx);
        String contentStr = "<meta property=\"og:url\" content=\"";
        int contentIdx = page.indexOf(contentStr, metaTagStartIdx);
        int urlStartIdx = contentIdx + contentStr.length();
        int urlEndIdx = page.indexOf("\"/>", urlStartIdx);
        return page.substring(urlStartIdx, urlEndIdx);
    }

    private String[] findOutLinkUrl(String page) {
        List<String> list = new ArrayList<>();

        String aTag = "<a href=\"";
        String aTagEnd = "\">";
        int nextIdx = 0;
        while (true) {
            int aTagIdx = page.indexOf(aTag, nextIdx);
            if (aTagIdx == -1) break;
            int urlStartIdx = aTagIdx + aTag.length();
            int urlEndIdx = page.indexOf(aTagEnd, urlStartIdx);
            String url = page.substring(urlStartIdx, urlEndIdx);
            list.add(url);
            nextIdx = urlEndIdx;
        }
        String[] ret = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }

    private int searchWord(String word, String page) {
        String lowPage = page.toLowerCase();
        String lowWord = word.toLowerCase();
        String[] strArr = lowPage.split("[^a-z]");
        int ret = 0;
        for (String str : strArr) {
            if (lowWord.equals(str)) ret++;
        }

        return ret;
    }

    private class WebPage {
        String url;
        int basicScore, outLinkedCnt;
        double linkScore, matchingScore;

        public WebPage() {
            this.basicScore = 0;
            this.outLinkedCnt = 0;
            this.linkScore = 0;
            this.matchingScore = 0;
        }

        @Override
        public String toString() {
            return "WebPage{" +
                    "url='" + url + '\'' +
                    ", basicScore=" + basicScore +
                    ", outLinkedCnt=" + outLinkedCnt +
                    ", linkScore=" + linkScore +
                    ", matchingScore=" + matchingScore +
                    '}';
        }
    }

}
