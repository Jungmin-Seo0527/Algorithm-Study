package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ16434_드래곤_앤_던전 {

    static int N, inputAtk;
    static long startMaxHp;
    static List<Room> roomList;

    static void solve() {
        long start = 1;
        long end = startMaxHp;
        while (start < end) {
            long mid = (start + end) >>> 1;
            if (doGame(mid, inputAtk)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    static boolean doGame(long maxHp, int atk) {
        Hero hero = new Hero(maxHp, maxHp, atk);
        for (int i = 0; i < N; i++) {
            Room room = roomList.get(i);
            if (room.t == 1) {
                if (!hero.hunting(room)) {
                    return false;
                }
            } else {
                hero.enhanceAtk(room.a);
                hero.healing(room.h);
            }
        }
        return true;
    }

    static class Hero {
        long maxHp, curHp, curAtk;

        public Hero(long maxHp, long curHp, long curAtk) {
            this.maxHp = maxHp;
            this.curHp = curHp;
            this.curAtk = curAtk;
        }

        public void healing(long plusHp) {
            curHp = Math.min(curHp + plusHp, maxHp);
        }

        public void enhanceAtk(long plusAktPoint) {
            curAtk += plusAktPoint;
        }

        public boolean hunting(Room monster) {
            long mh = monster.h;
            long ma = monster.a;

            long killMonster = mh / this.curAtk;
            if (mh % this.curAtk != 0) killMonster++;
            long dead = this.curHp / ma;
            if (curHp % ma != 0) dead++;

            if (killMonster <= dead) {
                this.curHp -= (killMonster - 1) * ma;
                return true;
            } else {
                return false;
            }
        }

        @Override public String toString() {
            return "Hero{" +
                    "maxHp=" + maxHp +
                    ", curHp=" + curHp +
                    ", curAtk=" + curAtk +
                    '}';
        }
    }


    static class Room {
        long t, a, h;

        public Room(long t, long a, long h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }

        @Override public String toString() {
            return "Room{" +
                    "t=" + t +
                    ", a=" + a +
                    ", h=" + h +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        //        System.out.println("===== input =====");
        //        String fileName = "input/input2.txt";
        //        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        //        String s;
        //        while ((s = br2.readLine()) != null) {
        //            System.out.println(s);
        //        }
        //        System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        inputAtk = Integer.parseInt(st.nextToken());
        roomList = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            if (t == 1) {
                startMaxHp += a * h;
            }
            roomList.add(new Room(t, a, h));
        }
        solve();
    }
}
