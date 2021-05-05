package kakao.internship2020;

import java.util.*;

public class 수식_최대화 {
    private String[] exp;
    private long ans;

    public long solution(String expression) {
        this.exp = splitExpression(expression);
        permutaion(new ArrayList<>(), new String[]{"*", "+", "-"}, new boolean[3]);
        return ans;
    }

    private void func(List<String> op) {
        List<String> list = new ArrayList<>(Arrays.asList(exp));
        for (int i = 0; i < op.size(); i++) {
            String curOp = op.get(i);
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(curOp)) {
                    long listLastNum = Long.parseLong(temp.get(temp.size() - 1));
                    long nextNum = Long.parseLong(list.get(j + 1));
                    long n;
                    if (curOp.equals("+")) {
                        n = listLastNum + nextNum;
                    } else if (curOp.equals("*")) {
                        n = listLastNum * nextNum;
                    } else {
                        n = listLastNum - nextNum;
                    }
                    j++;
                    temp.set(temp.size() - 1, String.valueOf(n));
                } else {
                    temp.add(list.get(j));
                }
            }
            list = temp;
        }
        ans = Math.max(Math.abs(Long.parseLong(list.get(0))), ans);
    }

    private void permutaion(List<String> list, String[] arr, boolean[] visited) {
        if (list.size() == 3) {
            func(list);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                List<String> nextList = new ArrayList<>(list);
                nextList.add(arr[i]);
                permutaion(nextList, arr, visited);
                visited[i] = false;
            }
        }
    }

    private String[] splitExpression(String expression) {
        String[] num = expression.split("[-+*]");
        String[] op = expression.split("[0-9]");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < op.length; i++) {
            if (!op[i].equals("")) {
                list.add(op[i]);
            }
        }
        String[] ans = new String[list.size() + num.length];
        int idx = 0;
        for (int i = 0; i < num.length; i++) {
            ans[idx] = num[i];
            idx += 2;
        }
        idx = 1;
        for (int i = 0; i < list.size(); i++) {
            ans[idx] = list.get(i);
            idx += 2;
        }
        return ans;
    }
}