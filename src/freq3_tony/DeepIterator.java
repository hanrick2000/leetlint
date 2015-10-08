package freq3_tony;

import java.util.*;

/**
 * http://blog.baozitraining.org/2014/08/linkedin-twitter-and-hulu-onsite-how-to.html
 * 包子铺的解法.
 * 
 * 先理解N00t的iterator of array很有帮助: http://n00tc0d3r.blogspot.com/2013/08/implement-iterator-for-arrays.html
 * 
 * @author tzhang
 *
 */
public class DeepIterator implements Iterator<Object> {
  private Stack<Iterator> iteratorStack = new Stack<Iterator>();
  private Integer top = null; // what is top??? Ans: 即当前的next 值.

  /**
   * ctor需要一个obj. 注意Java里面的class是(全部?)implemented Iterable的. 还有注意iterator是stateful的. 所以hasNext更新top, next取这个top. 然后才在hasNext()
   * @param iterable
   */
  public DeepIterator(Iterable iterable) {
    this.iteratorStack.push(iterable.iterator());
  }

  @Override
  public boolean hasNext() {
    if (this.top != null)
      return true;

    while (!this.iteratorStack.isEmpty()) {
      Iterator tmpIterator = this.iteratorStack.peek();

      if (tmpIterator.hasNext()) {
        Object tmp = tmpIterator.next();
        if (tmp instanceof Integer) {
          this.top = (Integer) tmp;
          return true;
        } else if (tmp instanceof Iterable) {
          this.iteratorStack.push(((Iterable) tmp).iterator());
        } else {
          throw new RuntimeException("Unsupported data type. ");
        }
      } else {
        this.iteratorStack.pop();
      }
    }
    return false;
  }

  @Override
  public Integer next() throws NoSuchElementException {
    if (hasNext()) {
      Integer tmp = this.top;
      this.top = null;
      return tmp;
    }

    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("This is not supported right now.");
  }
  
  
  
  /**
   * 测试
   * @param args
   */
  public static void main(String[] args){
    List<Object> list1 = new LinkedList();
    list1.add(0);
    list1.add(new LinkedList<Integer>());
    list1.add(1);
    list1.add(new LinkedList<Integer>());
    List<Object> list2 = new LinkedList();
    list2.add(list1);
    list2.add(2);
        
    List<Integer> newList = new LinkedList<Integer>();
    DeepIterator di = new DeepIterator(list2);

    while(di.hasNext()) {
        newList.add(di.next());
    }

    // Use assert if you want
    System.out.println(String.format("newList size is %d, expcted 3", newList.size()));

    list1 = new LinkedList();
    newList = new LinkedList<Integer>();
    di = new DeepIterator(list1);
    while(di.hasNext()) {
        newList.add(di.next());
    }  

    // assertTrue(newList.size() == 0);
    System.out.println(String.format("newList size is %d, expcted 0", newList.size()));

    list1 = new LinkedList();
    list1.add(new LinkedList<Integer>());
    list1.add(1);
    list1.add(new LinkedList<Integer>());
    list2 = new LinkedList();
    list2.add(list1);
    list2.add(2);
    List<Integer> list3 = new LinkedList<Integer>();
    list3.add(3);
    list3.add(4);
    list3.add(5);
    List list4 = new LinkedList();
    list4.add(list2);
    list4.add(list3);

    newList = new LinkedList<Integer>();

    di = new DeepIterator(list4);
    while(di.hasNext()) {
        newList.add(di.next());
    }

    // assertTrue(newList.size() == 5);
    System.out.println(String.format("newList size is %d, expcted 5", newList.size()));


    /**
                                  ----> 5
                                  |
                  ---- 3 -> 4  -> L  -> 6
                  |
        1 -> 2 -> L -> 7-> 8
     */
    list3 = new LinkedList<Integer>();
    list3.add(3);

    list2 = new LinkedList<Object>();
    list2.add(2);
    list2.add(list3);
    list2.add(4);

    list1 = new LinkedList<Object>();
    list1.add(1);
    list1.add(list2);
    list1.add(5);
    list1.add(6);

    di = new DeepIterator(list1);
    newList = new LinkedList<Integer>();
    while(di.hasNext()) {
        newList.add(di.next());
    }
    System.out.println(String.format("newList size is %d, expcted 6", newList.size()));
  
    // tony ->
    List<Object> inOne = Arrays.asList(list1);
    System.out.println(inOne);
    // <- tony
  }
}