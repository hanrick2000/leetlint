package interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Uber's 3rd_Guillaume @ 20151029
 * Created this class in interview at 11:49 PM, 10/29/2015.
 */
public class EventBusUber {
  Map<String, Set<Listener>> jobQ = new HashMap<>();

  public synchronized void register(String eventName, Listener listener) {
        /* TODO */

    if (!jobQ.containsKey(eventName)) {
      Set<Listener> set = new HashSet<Listener>();
      set.add(listener);
      jobQ.put(eventName, set);
    }
    else {
      jobQ.get(eventName).add(listener);
    }
  }

  public void unregister(String eventName, Listener listener) {
        /* TODO */
    if (!jobQ.containsKey(eventName)) {
      System.err.println("No event registerd: " + eventName);
    }
    else {
      jobQ.get(eventName).remove(listener);
//         List<Listener> list = jobQ.get(eventName);
//         List<Listener> dummy = LinkedList<>();
//         dummy.next = list;
//         list = dummy;

//         while (dummy)
    }
  }

  public void postEvent(String eventName, Object data) {
        /* TODO */
    if (!jobQ.containsKey(eventName)) {
      System.out.println("no listener");
      return;
    }
    else {
      for (Listener l : jobQ.get(eventName)) {
        l.onEvent(data);
      }
    }

  }

  public interface Listener {
    void onEvent(Object data);
  }

}
