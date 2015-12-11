package interview;

import java.util.List;

/**
 * http://stackoverflow.com/questions/6409652/random-weighted-selection-in-java
 * Created at 10:40 AM on 11/30/15.
 */
public class WeightedRandChooser {
  interface Item {
    double getWeight();
  }

  class RandomItemChooser {
    public Item chooseOnWeight(List<Item> items) {
      double completeWeight = 0.0;
      for (Item item : items)
        completeWeight += item.getWeight();
      double r = Math.random() * completeWeight;
      double countWeight = 0.0;
      for (Item item : items) {
        countWeight += item.getWeight();
        if (countWeight >= r)
          return item;
      }
      throw new RuntimeException("Should never be shown.");
    }
  }
}
