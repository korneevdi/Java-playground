# Map in Java Collection Framework

Although the **Map** interface does not inherit from the **Collection** interface, the **Map** collection hierarchy is part of the Java Collection Framework. But to make it easier to read and understand, this repository has a separate page for the **Map** hierarchy.

<p align="center">
  <img src="images/Map_Diagram.png" alt="Map diagram" width="750"/>
  <br>
  <em>Figure 1: Map hierarchy in Java Collection Framework</em>
</p>


## Table of Contents

- [1. Map – Key-value pairs with unique keys](#1-map--key-value-pairs-with-unique-keys)
    - [1.1. Class 'HashMap'](#11-class-hashmap)
    - [1.2. Class 'LinkedHashMap'](#12-class-linkedhashmap)
    - [1.3. Class 'TreeMap'](#13-class-treemap)
    - [1.4. Abstract class 'AbstractMap'](#14-abstract-class-abstractmap)
    - [1.5. Interface 'SequencedMap'](#15-interface-sequencedmap)
    - [1.6. Interface 'SortedMap'](#16-interface-sortedmap)
    - [1.7. Interface 'NavigableMap'](#17-interface-navigablemap)
    - [1.8. Class 'Hashtable'](#18-class-hashtable)
    - [1.9. Abstract class 'Dictionary'](#19-abstract-class-dictionary)
    - [1.10. Interface 'Map'](#110-interface-map)


## 1. Map – Key-value pairs with unique keys

### 1.1. Class 'HashMap'

See [official documentation](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) and [my implementation](src/main/java/collection/MyHashMap.java).

**HashMap** is a hash table–based implementation of the **Map** interface. It stores key-value pairs and allows efficient retrieval, insertion, and deletion based on keys.

<p align="center">
  <img src="images/HashMap.png" alt="HashMap structure" width="620"/>
  <br>
  <em>Figure 2: HashMap structure</em>
</p>

Internally, **HashMap** uses an array of buckets (of the type of the internal **Entry** class), where each bucket is a linked list (or a balanced tree for high-collision cases). The position of each key in the array is determined by applying the hashCode() of the key and then compressing it using modulo or bit manipulation:

```
index = hash(key) % capacity;
```

Key characteristics of **HashMap**:

 - Allows null keys and values (only one null key allowed).
 - Unordered: does not maintain insertion order.
 - Not synchronized: must be manually synchronized for thread safety.
 - Time complexity:
   - put(), get(), remove() — average case: O(1), worst case: O(n) (or O(log n) with tree bins).
   - containsKey() / containsValue() — O(1) / O(n).

**HashMap** is the default choice for most use cases when fast access by key is required and order does not matter.

### 1.2. Class 'LinkedHashMap'

**LinkedHashMap** is a hash table–based implementation of the **Map** interface that maintains a predictable iteration order. It extends **HashMap** and preserves the insertion order (or access order, if configured) by maintaining a doubly linked list that runs through all its entries.

Internally, **LinkedHashMap** creates two structures:
 - A hash table (array of buckets) for efficient key-based access, just like in **HashMap**.
 - A doubly linked list connecting all entries in insertion (or access) order.

Each entry stores additional pointers (*before* and *after*) to maintain the list order. This allows ordered iteration without compromising key lookup performance.

Key characteristics of **LinkedHashMap**:
 - Maintains insertion order by default (or access order if accessOrder is true).
 - Allows one null key and multiple null values.
 - Slightly slower than **HashMap** due to linked list maintenance.
 - Time complexity:
   - put(), get(), remove() — O(1) on average.
   - ordered iteration — O(n), in insertion or access order.
 - Not synchronized.

**LinkedHashMap** is ideal when predictable iteration order is needed along with fast lookups — for example, in caches, LRU algorithms, or when you want consistent output order from a map.

### 1.3. Class 'TreeMap'

### 1.4. Abstract class 'AbstractMap'

### 1.5. Interface 'SequencedMap'

### 1.6. Interface 'SortedMap'

### 1.7. Interface 'NavigableMap'

### 1.8. Class 'Hashtable'

### 1.9. Abstract class 'Dictionary'

### 1.10. Interface 'Map'



