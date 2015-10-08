package freq4_tony;

import java.util.*;

public class testHashMap {

  public static void play() {
    HashMap<String, ArrayList<Integer>> sa = new HashMap<>();
    ArrayList<Integer> ai = new ArrayList<Integer>();
    ai.add(23);
    ai.add(5);
    ai.add(99);
    sa.put("Tony", ai);

    ArrayList<Integer> aj = (ArrayList<Integer>) ai.clone();
    aj.remove(1);
    aj.add(417);
    sa.put("Jing", aj);

    System.out.println(sa.values());
    for (ArrayList<Integer> valSet : sa.values())
      System.out.println(valSet);
    // sa.get("Tony");
    // System.out.println("Tony: " + sa.get("Tony") + "and jing: " +
    // sa.get("Jing"));
  }

  public static void hashMapDemo() {
    // Create a hash map
    HashMap<String, Double> hm = new HashMap<>();
    // Put elements to the map
    hm.put("Zara", new Double(3434.34));
    hm.put("Mahnaz", new Double(123.22));
    hm.put("Ayan", new Double(1378.00));
    hm.put("Daisy", new Double(99.22));
    hm.put("Qadir", new Double(-19.08));

    // Get a set of the entries
    Set set = hm.entrySet();
    // Get an iterator
    Iterator i = set.iterator();
    // Display elements
    while (i.hasNext()) {
      Map.Entry me = (Map.Entry) i.next();
      System.out.print(me.getKey() + ": ");
      System.out.println(me.getValue());
    }
    System.out.println();
    // Deposit 1000 into Zara's account
    double balance = ((Double) hm.get("Zara")).doubleValue();
    hm.put("Zara", new Double(balance + 1000));
    System.out.println("Zara's new balance: " + hm.get("Zara"));
  }

  public static void hashMapSimple() {
    // Create a hash map
    HashMap<Integer, ArrayList<String>> hm = new HashMap<>();
    // Put elements to the map
    hm.put(1, new ArrayList<String>(Arrays.asList("hello", "olleh")));
    hm.put(2, new ArrayList<String>(Arrays.asList("to", "ot", "tt", "oo")));
    hm.put(4, new ArrayList<String>(Arrays.asList("only")));
    // Get a set of the entries
    Set st = hm.entrySet();
    Iterator it = st.iterator();
    // get iterator
//    System.out.println(st);
    while (it.hasNext()){
//      System.out.println(it.next());
      Map.Entry me = (Map.Entry) it.next();
      System.out.print(me.getKey() + ": ");
      System.out.println(me.getValue());
    }
    hm.get(4).add("not only");
//    hm.put(4, );
    System.out.println(st);
  }

  public static void main(String args[]) {
    // hashMapDemo();
    // play();
    hashMapSimple();
    return;
  }
}
