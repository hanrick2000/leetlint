package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// http://stackoverflow.com/a/18675945/3984911

public class Regex1 {
  public static void main(String args[]) {

    // String to be scanned to find the pattern.
    String line = "This order was placed for QT3000! OK?";
    String pattern = "(.?)(\\d+)(.*)";

    // Create a Pattern object
    Pattern r = Pattern.compile(pattern);

    // Now create matcher object.
    Matcher m = r.matcher(line);
    if (m.find()) {
      System.out.println("Found value: " + m.group(0));
      System.out.println("Found value: " + m.group(1));
      System.out.println("Found value: " + m.group(2));
      System.out.println("Found value: " + m.group(3));
    } else {
      System.out.println("NO MATCH");
    }
    
    final String EXAMPLE_TEST = "This is , for TQ3000! OK?";
    String pattern1 = "(\\w)(\\s+)([\\.,])";
    System.out.println(EXAMPLE_TEST.replaceAll(pattern1, "$1$3")); 
    
    // http://stackoverflow.com/questions/13796451/how-to-extract-a-string-between-two-delimiters
    String mydata = "some string with 'the data i want' inside";
    Pattern pattern2 = Pattern.compile("'(.*?)'");
    Matcher matcher = pattern2.matcher(mydata);
    if (matcher.find())
    {
        System.out.println(matcher.group(1));
    }
    
  }
}
