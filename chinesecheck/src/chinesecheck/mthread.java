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

    public mthread() {
        online = new online();
    }

    public void run() {
        // System.out.println("SB info:" + webinfo + ")");
        // System.out.println(online.webinfo);
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

                online.webinfo = null;// 清空
                online.selectroom(webinfos);
                webinfos = null;// 清空
                System.out.print(online.webinfo);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (online.webinfo.contains("success")) {
                checkmain.logString += "success\n";
            } else {// ont success
                checkmain.logString += "Check Your SID";
            }

        }

    }

    String webstatus() {
        return online.getcurrentinfo();
    }

}

class online {
    public String webinfo = "";
    public String SID = "c02999";

    public online() {
        try {
            gethttp();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    }

    void selectroom(String[] pass) throws IOException {
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
        is.close();
    }

    void waittogo() {

    }
}