package chinesecheck;

import java.lang.Thread;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class mthread extends Thread {
    static online online;
    static String[] webinfos;
    checkmain c;

    public mthread(String sid) {
        online = new online(sid);

    }

    public void run() {
        // System.out.println("SB info:" + webinfo + ")");
        // System.out.println(online.webinfo);
        try {
            online.gethttp();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (online.webinfo.contains("Error")) {
            String tempString = new String();
            // tempString += webstatus();
            tempString += "HI," + online.SID + "\n Room is exiesd!";
            checkmain.logString += tempString;
            System.out.println(checkmain.logString);
        } else {
            checkmain.logString += "你的學號:" + online.SID + "\n";
            checkmain.logString += webstatus();
            webinfos = webstatus().split(" ");
            System.out.print("(room ID:" + webinfos[0] + " ,對手學號:" + webinfos[1] + ")");
            try {

                online.selectroom(webinfos);

                System.out.print(online.webinfo);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (online.webinfo.contains("success")) {
                checkmain.logString += "success\n";
                webinfos = null;// 清空
                online.webinfo = null;// 清空
                Thread wc = new walkchess();
                wc.start();
            } else {// ont success
                checkmain.logString += "Check Your SID";
            }

        }

    }

    String webstatus() {
        return online.getcurrentinfo();
    }

}

class walkchess extends Thread {
    static online o = new online(online.SID);
    String[] wifo;

    public walkchess() {

    }

    public void run() {
        try {
            o.webinfo = "";
            o.waittogo();
            wifo = o.webinfo.split(" ");
            checkmain.logString += "\n" + o.webinfo;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (wifo[0]) {
        case "0":
            if (wifo[1] == wifo[2]) {
                try {
                    o.play(online.walkchess);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {

            }
            break;
        case "1":
            break;
        case "2":
            try {
                sleep(3000);
                System.out.println("Waiting");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.run();

            break;
        case "-1":
            break;

        }

    }

}

class online {
    public String webinfo = "";
    static String roomid;
    static String[] walkchess = new String[3];
    public static String SID = "";

    public online(String sid) {
        SID = sid;

    }

    static void srtwalkchess(String[] s) {
        walkchess = s;
    }

    String getcurrentinfo() {
        return webinfo + "\n";
    }

    void gethttp() throws IOException {
        final URL url = new URL("http://140.138.147.44:6004/you_r_fired/start?SID=" + SID);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        final BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
        final byte[] tmp = new byte[1024];
        int len = 0;
        final Charset UTF_8 = Charset.forName("BIG5");
        while ((len = is.read(tmp)) != -1) {
            final String value = new String(tmp, UTF_8);
            webinfo += value;
        }
        is.close();
        System.out.print(url);
    }

    void selectroom(String[] pass) throws IOException {
        roomid = pass[0];
        final URL url = new URL(
                "http://140.138.147.44:6004/you_r_fired/select_room?SID=" + SID + "&room_id=" + pass[0]);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        final BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
        final byte[] tmp = new byte[1024];
        int len = 0;
        final Charset UTF_8 = Charset.forName("BIG5");
        while ((len = is.read(tmp)) != -1) {
            final String value = new String(tmp, UTF_8);
            webinfo += value;
        }
        System.out.print(url);
        is.close();
    }

    void waittogo() throws IOException {
        final URL url = new URL("http://140.138.147.44:6004/you_r_fired/wait?SID=" + SID + "&room_id=" + roomid);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        final BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
        final byte[] tmp = new byte[1024];
        int len = 0;
        final Charset UTF_8 = Charset.forName("BIG5");
        while ((len = is.read(tmp)) != -1) {
            final String value = new String(tmp, UTF_8);
            webinfo += value;
        }
        System.out.print(url);
        is.close();
    }

    void play(String[] status) throws IOException {
        final URL url = new URL("http://140.138.147.44:6004/you_r_fired/play?SID=" + SID + "&room_id=" + roomid
                + "&type=" + status[0] + "&x=" + status[1] + "&y=" + status[2]);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        final BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
        final byte[] tmp = new byte[1024];
        int len = 0;
        final Charset UTF_8 = Charset.forName("BIG5");
        while ((len = is.read(tmp)) != -1) {
            final String value = new String(tmp, UTF_8);
            webinfo += value;
        }

        is.close();
        System.out.print(url);
    }
}
