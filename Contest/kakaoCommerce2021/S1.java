package kakaoCommerce2021;

class S1 {
    private final int MAX = 100_000;
    private int[] arr, gift_cards, wants;

    public int solution(int[] gift_cards, int[] wants) {
        init(gift_cards, wants);
        return ans();
    }

    private int ans() {
        int ret = 0;
        for (int i = 0; i < gift_cards.length; i++) {
            arr[gift_cards[i]]++;
        }

        for (int i = 0; i < wants.length; i++) {
            if (arr[wants[i]] != 0) {
                arr[wants[i]]--;
                ret++;
            }
        }
        return wants.length - ret;
    }

    private void init(int[] g, int[] w) {
        this.gift_cards = g;
        this.wants = w;
        arr = new int[MAX + 1];
    }
}