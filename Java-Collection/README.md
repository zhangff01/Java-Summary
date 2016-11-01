#Java-集合
Java集合的Collection接口和Map接口

查看java.util下的源码找到Collection接口,可以看到它继承了Iterable接口,所有实现了Collection接口的容器类都有iterator方法，

用于返回一个实现了Iterator接口的对象。Iterator对象称作迭代器，Iterator接口方法能以迭代方式逐个访问集合中各个元素.

在Collection接口中定义了一下方法:
```java
  int size();       //@return the number of elements in this collection(返回集合中的元素个数)
  boolean isEmpty();//@return true if this collection contains no elements(集合中没有数据时返回true)
  boolean contains(Object o);
  /*
  Returns <tt>true</tt> if this collection contains the specified element.More formally, 
  returns true if and only if this collection contains at least one element e such that
  (o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e)).
  (当对象o不等于null并且集合中至少有一个元素e不等于null并且o.equals(e)时返回true,意味着对象的话我们要重写equals和haseCode方法)
  */
  Iterator<E> iterator();//迭代器
  Object[] toArray();    //@return an array containing all of the elements in this collection(返回一个包含集合所有元素的数组)
  <T> T[] toArray(T[] a);//泛型定义的toArray
  boolean add(E e);      
  /*
  Ensures that this collection contains the specified element (optional operation).Returns true 
  if this collection changed as a result of the call.(Returns false if this collection does
  not permit duplicates and already contains the specified element.)
  确保此集合包含指定的元素,如果集合因此而改变,返回true(如果此集合不允许重复,并且已经包含指定的元素,则返回false.)
  */
  boolean remove(Object o);
  /*
  removes an element e such that (o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e)), if
  this collection contains one or more such elements.
  如果o不等于null并且集合中至少有一个元素e不等于null并且o.equals(e)时返回true,g否则返回false.
  */
  boolean containsAll(Collection<?> c);
  /*
  Returns true if this collection contains all of the elements in the specified collection.
  如果集合包含指定集合c中所有的元素返回true,否则返回false.
  */
  boolean addAll(Collection<? extends E> c);
  boolean removeAll(Collection<?> c);
  boolean retainAll(Collection<?> c);//从当前集合中 删除 指定集合c 中不含 的所有元素
  void clear();//清空集合中的所有的元素,isEmpty()==true;
  boolean equals(Object o);
  int hashCode();
```
然后可以看到List和Set接口都继承自Collection接口,在List接口中又多了以下内容:
```java
  E get(int index);              //E返回类型:泛型控制的集合元素的类型
  E set(int index, E element);
  void add(int index, E element);//在指定位置处插入元素
  E remove(int index);           //@return the element previously at the specified position(删除并返回指定d位置的元素)
  int indexOf(Object o);         //o不在集合里面返回-1,o在集合里面有多个对应的话返回index值小的
  int lastIndexOf(Object o);     //o不在集合里面返回-1,在的话返回index最大的
  ListIterator<E> listIterator();//迭代器
  ListIterator<E> listIterator(int index);
  List<E> subList(int fromIndex, int toIndex);//@return a view of the specified range within this list
```
*在Set接口中的方法和Collection接口中的一样.

##AbstractCollection,AbstractList和AbstractSet抽象类
既然有了接口就要有类去实现它,要不然这个接口就没有意义(就像有制度就要去遵循一样,没有人遵循的制度就没有存在的意义)

继续往下看就会发现,有三个抽象类AbstractCollection,AbstractList和AbstractSet分别实现了Collection,List和Set接口.

而且AbstractList和AbstractSet抽象类都继承了AbstractCollection抽象类.

##AbstractCollection抽象类
在AbstractCollection抽象类里面实现了Collection接口里定义的方法,但是并没有实现equals和haseCode方法.

##AbstractSet抽象类
因为Set接口和Collection接口里面定义的方法一样,所以AbstractSet抽象类里面只是实现了equals和haseCode方法,

并且重写了removeAll方法.

##AbstractList抽象类
AbstractList抽象类继承了AbstractCollection抽象类的方法并实现了List接口中其他的方法,并且在内部实现了Iterator接口的实现类.

##Set -> HashSet

