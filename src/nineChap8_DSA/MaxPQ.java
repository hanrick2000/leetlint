package nineChap8_DSA;

// Algs4: algorithm 2.6

public class MaxPQ<Key extends Comparable<Key>> {
  private Key[] pq;
  private int N = 0;

  public MaxPQ(int maxN) {
    pq = (Key[]) new Comparable[maxN + 1];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  public void insert(Key v) {
    pq[++N] = v;
    swim(N);
  }
  
  public Key[] getPQ() {
    return pq;
  }

  public Key delMax() {
    Key max = pq[1];
    // pq[1] = pq[N--]; // why not direct give the value? Since always prefer to use I/O api to
    // access the object's instance
    exch(1, N--);
    pq[N + 1] = null;
    sink(1);
    return max;
  }

  // check if pq[i] is less than pq[j]
  private boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }

  private void exch(int i, int j) {
    Key tmp = pq[i];
    pq[i] = pq[j];
    pq[j] = tmp;
  }
  
  // Devil in the detail!
  private void swim(int i) {
//    while (i/2 > 0 && !less(i, i/2)) {
    while (i > 1 && less(i/2, i)) {
      exch(i, i/2);
      i = i/2;
    }
  }
  
  // Devil in the detail
  private void sink(int k) {
//    while (2*k < N) {
    while (2*k <= N) {
      int j = 2*k;
//      if (less(j, j+1))  j++;
      if (j < N && less(j, j+1))  j++;
//      if (!less(j, k))  break;
      if (!less(k, j))  break;
      exch(k,j);
      k = j;
    }
  }
}
