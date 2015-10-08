package learnJava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LearnIterable {
  /**
   * ctor
   * @param iterable
   */
  public LearnIterable(Iterable iterable) {
    Iterator it = iterable.iterator();
    learnIterator(it);
  }
  
  /**
   * 确实generic的iterator可以遍历到每一个element. 但是要注意. 这里遇到obj element的话就当作整个, 而不会是nested iteration.
   * @param it
   */
  private void learnIterator(Iterator it) {
    while(it.hasNext()) 
      System.out.println(it.next() + " ");
  }
  
  public static void main(String[] args) {

  List<Integer> arri = new ArrayList<>();
  arri.add(3);
  arri.add(6);
  arri.add(12);
  arri.add(24);
  arri.add(49);
//  System.out.println(arr instanceof Serializable);
//  System.out.println(arr instanceof Cloneable);
//  System.out.println(arr instanceof Iterable);
  
  List<Object> arr = new ArrayList<>();
  arr.add(3);
  arr.add(6);
  arr.add(new LinkedList<Integer>());
  arr.add(12);
  arr.add("ni hao");
  arr.add(24);
  List<Integer> arrIn = new LinkedList<>();
  arrIn.add(38);
  arrIn.add(39);
  arrIn.add(41);
  arr.add(arrIn);
  arr.add(49);
  
  if (arr instanceof List<?>) { // instanceof is for compile time, so can only do List<?>
    System.out.println("Hello");
  } else if (arr instanceof Iterable) {
    System.out.println("World");
  }
//  Object[] array = new Object[]{};
//  System.out.println((array instanceof Serializable));//passed
//  System.out.println((array instanceof Cloneable));//passed
  // http://stackoverflow.com/questions/11354958/why-doesnt-array-instanceof-iterable-compile-in-java
//  System.out.println(array instanceof Iterable); // false
  
  LearnIterable li = new LearnIterable(arr);
  
  }
}
