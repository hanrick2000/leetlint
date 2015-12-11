package interview;

import java.util.*;

/**
 * 9chap system design
 * Created at 7:20 PM on 11/30/15.
 */
public class TinyURL9chap {
  final static char[] Encode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
  Map<String, String> mLong2Short = new HashMap<>();
  Map<String, String> mShort2Long = new HashMap<>();

  String insert(String longURL) {
    if (!mLong2Short.containsKey(longURL)) {
      String shortURL = generateShortURL(longURL);
      mLong2Short.put(longURL, shortURL);
      mShort2Long.put(shortURL, longURL);
      return shortURL;
    }
    else {
      return mLong2Short.get(longURL);
    }
  }

  private String generateShortURL(String longURL) {
    return convertTo62(mLong2Short.size()+1);
  }

  private String convertTo62(int size) {
    StringBuilder sb = new StringBuilder();
    while (size != 0) {
      sb.append(Encode[size % 62]);
      size /= 62;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    TinyURL9chap tu9c = new TinyURL9chap();
    tu9c.test();
  }

  public void test() {
    String web1 = "www.jiuzhang.com/course/2/";
    String web2 = "www.inoreader.com/all_articles";
    String web3 = "http://blog.csdn.net/longyulu/article/details/9159589";
    TinyURL9chap tiny = new TinyURL9chap();
    tiny.insert(web1);
    tiny.insert(web2);
    tiny.insert(web3);
  }
}
