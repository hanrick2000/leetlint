package freq3_tony;

/**
 * http://rosettacode.org/wiki/Flatten_a_list#Java 先看看怎么把list of list 扁平化. 然后再去看看怎么设计iterator of
 * list of list.
 */
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class FlattenUtil {

  public static void flatten(List<?> from, List<Object> to) {
    for (Object item : from) {
      if (item instanceof List<?>) {
        flatten((List<?>)item, to);
      }
      else {
        to.add(item);
      }
    }
  }

  private static List<Object> a(Object... a) {
    return Arrays.asList(a);
  }

  public FlattenUtil(){
    List<Object> ret = new LinkedList<Object>();
    List<Object> input = a(a(1), 2, a(a(3,4), 5), a(a(a())), a(a(a(6))), 7, 9, a() ); 
    flatten(input, ret);
    System.out.println(input);
    System.out.println(ret);
  }
  public static void main(String[] args) {
//    List<Object> xx = a(1, 2, 3, "AB_tony");
//    System.out.println(xx);
    FlattenUtil fu = new FlattenUtil();
    
  }
}
