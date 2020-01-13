package chinesecheck;

import java.lang.Thread;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class mthread extends Thread {
    static online online;
    checkmain c;

    public mthread() {
        online = new online();
    }

    public void run() {
        // System.out.println("SB info:" + webinfo + ")");
        // System.out.println(online.webinfo);
        if (online.webinfo.contains("Error")) {

            checkmain.logString += online.webinfo;
            checkmain.logString += " Room is exiesd";
            System.out.println(checkmain.logString);

        }

    }

    String webstatus() {
        return online.getcurrentinfo();
    }

}

class online {
    public String webinfo = "";

    public online() {
        try {
            gethttp();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    String getcurrentinfo() {
        return webinfo;
    }

    void gethttp() throws IOException {
        final URL url = new URL("http://140.138.147.44:6004/you_r_fired/start?SID=s1052043");
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
}