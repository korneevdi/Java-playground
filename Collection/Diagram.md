# Java Collection Framework Diagram

<p align="center">
  <img src="images/Diagram.png" alt="Java Collection Framework diagram" width="1000"/>
  <br>
  <em>Figure 1: Java Collection Framework diagram</em>
</p>

### Class 'ArrayList'

See [official documentation](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) and [my implementation](src/main/java/collection/MyArrayList.java).

<p align="center">
  <img src="images/ArrayList.png" alt="ArrayList structure" width="500"/>
  <br>
  <em>Figure 2: ArrayList structure</em>
</p>

### Interface 'Iterable'

See [official documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html).

The **Iterable** interface is the top-level interface in the Java Collection Framework hierarchy. It represents a collection of elements that can be traversed sequentially, one element at a time. The main purpose of this interface is to provide a standardized way to allow objects to be the target of the enhanced for-each loop introduced in Java 5.

**Iterable** defines a single abstract method:

```
Iterator<T> iterator();
```

This method returns an Iterator<T> that provides the mechanism to iterate over the elements in the collection. The returned iterator allows traversal using methods like hasNext() and next().

Any class that implements **Iterable** can be used in a for-each loop. All major collection types in Java, such as List, Set, and Queue, implement the **Iterable** interface, making them compatible with enhanced for-loops. While the Map interface itself does not directly implement Iterable, its key set (keySet()), value collection (values()), and entry set (entrySet()) do, enabling iteration over keys, values, or key-value pairs.

