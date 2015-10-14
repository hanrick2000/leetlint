package learnJava;

import java.io.UnsupportedEncodingException;

/**
 * Created by 12:26 AM on 10/13/2015.
 */
public class utf8gdk {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String respMsg = "å‰è¢ã…æ˜¯BFS, åè¢ã…æ˜¯Pre-order DFS. ïØ¢ãä»\uE652intå’ŒLeetïĞ„é\uE57Dç›\uE1BF¡¨ç¤ÖşSTïĞ„æ–¹å¼éƒ½ä¸åŒ! æçš„æˆ‘çš„valid";
        respMsg = new String(respMsg.getBytes("UTF-8"), "GBK");
        respMsg = new String(respMsg.getBytes("GBk"),"UTF-8");
        System.out.println(respMsg);
    }
}
