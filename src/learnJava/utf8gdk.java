package learnJava;

import java.io.UnsupportedEncodingException;

/**
 * Created by 12:26 AM on 10/13/2015.
 */
public class utf8gdk {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String respMsg = "前��是BFS, 后��是Pre-order DFS. �آ��\uE652int和Leet�Є�\uE57D�\uE1BF�����ST�Є方式都不同! 搞的我的valid";
        respMsg = new String(respMsg.getBytes("UTF-8"), "GBK");
        respMsg = new String(respMsg.getBytes("GBk"),"UTF-8");
        System.out.println(respMsg);
    }
}
