package com.zen.eventLoop;

import java.util.List;

@SuppressWarnings("unused")
public interface Heap<K,V extends Comparable<?> & hasKey<K>> {
  void    record(K key, V val);
  List<V> get();
  void    flush();
}