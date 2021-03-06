package relop;

import global.SearchKey;
import heap.HeapFile;
import index.HashIndex;
import index.BucketScan;
import global.RID;

/**
 * Wrapper for bucket scan, an index access method.
 */
public class IndexScan extends Iterator {

  private HeapFile file;
  //private Schema schema;
  private HashIndex index;
  private BucketScan scan;
  private boolean open;
  private RID lastRID;

  /**
   * Constructs an index scan, given the hash index and schema.
   */
  public IndexScan(Schema schema, HashIndex index, HeapFile file) {
    //throw new UnsupportedOperationException("Not implemented");
    this.file = file;
    scan = index.openScan();
    this.schema = schema;
    this.open = true;
  }

  /**
   * Gives a one-line explaination of the iterator, repeats the call on any
   * child iterators, and increases the indent depth along the way.
   */
  public void explain(int depth) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Restarts the iterator, i.e. as if it were just constructed.
   */
  public void restart() {
    //throw new UnsupportedOperationException("Not implemented");
    this.scan.close();
    this.scan = index.openScan();
    this.open = true;
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
    //throw new UnsupportedOperationException("Not implemented");
    return open;
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {
    //throw new UnsupportedOperationException("Not implemented");
    scan.close();
    open = false;
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
    //throw new UnsupportedOperationException("Not implemented");
    return scan.hasNext();
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() {
    //throw new UnsupportedOperationException("Not implemented");
    this.lastRID = scan.getNext();
    return new Tuple(schema, file.selectRecord(lastRID));
  }

  /**
   * Gets the key of the last tuple returned.
   */
  public SearchKey getLastKey() {
    //throw new UnsupportedOperationException("Not implemented");
    return scan.getLastKey();
  }

  /**
   * Returns the hash value for the bucket containing the next tuple, or maximum
   * number of buckets if none.
   */
  public int getNextHash() {
    //throw new UnsupportedOperationException("Not implemented");
    return scan.getNextHash();
  }

  public RID getLastRID() {
    return lastRID;
  }
} // public class IndexScan extends Iterator
