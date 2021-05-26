package contest.devMatching2021_backEnd;

import java.util.*;

public class 다단계_칫솔_판매 {
    private Map<String, Node> hashMap = new HashMap<>();
    private String[] enroll, referral, seller;
    private int[] amount;
    private int N;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        init(enroll, referral, seller, amount);

        for (int i = 0; i < seller.length; i++) {
            String sellerName = seller[i];
            int price = amount[i] * 100;
            hashMap.get(sellerName).calProfit(price);
        }
        return ans();
    }

    private void init(String[] enroll, String[] referral, String[] seller, int[] amount) {
        this.enroll = enroll;
        this.referral = referral;
        this.seller = seller;
        this.amount = amount;
        this.N = enroll.length;
        Node root = new Node("-", null, 0);
        hashMap.put("-", root);

        for (int i = 0; i < N; i++) {
            String childName = enroll[i];
            String parentName = referral[i];
            Node childNode = new Node(childName, hashMap.get(parentName), 0);
            hashMap.put(childName, childNode);
        }
    }

    private int[] ans() {
        int[] ret = new int[N];
        for (int i = 0; i < N; i++) {
            ret[i] = hashMap.get(enroll[i]).profit;
        }
        return ret;
    }

    private class Node {
        Node parent;
        String name;
        int profit;

        public Node(String name, Node parent, int profit) {
            this.parent = parent;
            this.name = name;
            this.profit = profit;
        }

        public void calProfit(int profit) {
            int nextProfit = profit / 10;
            this.profit += profit - nextProfit;
            if (this.parent != null && nextProfit != 0) {
                this.parent.calProfit(nextProfit);
            }
        }
    }
}