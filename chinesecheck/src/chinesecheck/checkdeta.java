/*
 * check deta 主要用來進行server的溝通與client的網路功能 同時存取棋局資訊
 * 
 * 
 * package chinesecheck;
 * 
 * public class checkdeta {
 * 
 * }
 * 
 * import java.io.IOException;
 * 
 * public class thread implements Runnable { public void run() { board b = new
 * board(); // 取得棋盤 String http; String data = ""; String ss[]; int type; int x;
 * int y; net N = new net();
 * 
 * // ---------------第一步 // net N = new net(); // String http =
 * "http://140.138.147.44:6004/you_r_fired/start?SID=s1052043"; // String
 * data=""; // data = N.getdata(http); // System.out.println("房號 對手"+data); //
 * String[] ss = data.split("\\s+"); //切割字串 以空白為基準 // room_id =
 * Integer.parseInt(ss[0]); //取得房號 string to int // // //--------------- // http
 * = //
 * "http://140.138.147.44:6004/you_r_fired/select_room?SID=s1052043&room_id=" +
 * // room_id; // data = N.getdata(http); // System.out.println("連接房間"+data); //
 * // // // if(data=="sucess") // { // http =
 * "http://140.138.147.44:6004/you_r_fired/wait?SID=s1072043&room_id="+ //
 * room_id; // data = N.getdata(http); // ss = data.split("\\s+"); //獲得狀態 //
 * status = ss[0]; // if(ss[1]=="1") // isme = false; // thread t = new
 * thread(); // Thread thread=new Thread(t); // thread.start(); // } //
 * 
 * while (board.status == "2") { if (board.isme == true) { if (board.isok =
 * true) { http =
 * "http://140.138.147.44:6004/you_r_fired//play?SID=1052043&room_id=" +
 * board.room_id + "&type=" + moveEvent.chess_type + "&x=" + moveEvent.x + "&y="
 * + moveEvent.y; board.isme = false; } else { http =
 * "http://140.138.147.44:6004/you_r_fired/wait?SID=s1072043&room_id=" +
 * board.room_id; try { data = N.getdata(http); } catch (IOException e) { //
 * TODO Auto-generated catch block e.printStackTrace(); } ss =
 * data.split("\\s+"); type = Integer.parseInt(ss[3]); x =
 * Integer.parseInt(ss[4]); y = Integer.parseInt(ss[5]); for (int i = 0; i < 10;
 * i++) for (int j = 0; j < 9; j++) { if (b.getPiece(y, x) == type)
 * b.setPiece(y, x, -1); } b.setPiece(y, x, type); board.isme = true; } }
 * 
 * } } }
 */