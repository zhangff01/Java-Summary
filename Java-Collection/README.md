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
在AbstractCollection抽象类里面实现了Collection接口里定义的方法,但是并没有实现equals和hashCode方法.

##AbstractSet抽象类
因为Set接口和Collection接口里面定义的方法一样,所以AbstractSet抽象类里面只是实现了equals和hashCode方法,

并且重写了removeAll方法.

##AbstractList抽象类
AbstractList抽象类继承了AbstractCollection抽象类的方法并实现了List接口中其他的方法,并且在内部实现了Iterator接口的实现类.

##Set -> HashSet(继承AbstractSet抽象类)
查看HashSet的实现源码你会发现HashSet是用HashMap来实现的,如下图:
![image](https://github.com/zhangff01/Java-Summary/blob/master/Java-Collection/HashSet%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0.png)

然后我们继续查看代码:
```java
  private static final Object PRESENT = new Object();
  ...
  public boolean add(E e) {
        return map.put(e, PRESENT)==null;
  }
  ...
```
可以看到HashSet添加一个新元素其实是在map里面put一个(元素,PRESENT)值,只不过这个元素的value值都是一个Object对象.

(下面会讲HashMap是怎么实现的)

HashSet的小例子:
```java
  public class CollectionMain {

	  public static void main(String[] args) {
		  Set<Student> set=new HashSet<Student>();
		  set.add(new Student("清华附中", "小王", 15));
		  set.add(new Student("清华附中", "小王", 15));
		  set.add(new Student("清华附中", "小张", 15));
		  set.add(null);
		  set.add(null);
		  System.out.println("集合长度:"+set.size());
	  }

  }

  class Student{
	  private String school;
	  private String name;
	  private int age;
	
	  public Student(String school,String name,int age) {
		  this.school=school;
		  this.name=name;
		  this.age=age;
	  }
	
	  @Override
	  public boolean equals(Object o){
		  if(o==null){
			  return false;
		  }
		  if(o==this){
			  return true;
		  }
		  if(o instanceof Student){
			  Student s=(Student)o;
			  return (s.school.equals(school)&&s.name.equals(name)&&s.age==age);
		  }
		  return false;
	  }
	
	  @Override
	  public int hashCode(){
		  int h=1;
		  h =31*h+((school.equals("")==true)?0:school.hashCode());
		  h =31*h+((name.equals("")==true)?0:name.hashCode());
		  return h;
	  }
  }
  ...
  //输出结果为 集合长度:3
```
##Set -> TreeSet(继承AbstractSet抽象类并实现了NavigableSet接口(继承自SortedSet))
和HashSet集合一样,TreeSet的实现也是采用了Map来实现的,不过是NavigableMap.

采用TreeSet的话就是可以对集合的元素进行排序.
方法一:
```java
  Set<Student> set=new TreeSet<Student>(new Comparator<Student>(){
			@Override
			public int compare(Student s1, Student s2) {
				if(s1==null||s2==null){
					return 1;
				}
				if(s1.getAge()>s2.getAge()){
					return 1;
				}else if(s1.getAge()<s2.getAge()){
					return -1;
				}else{
					return 0;
				}
			}
	});
  //在TreeSet集合初始化的时候用内部类实现Comparator接口
```
方法二:
```java
  class Student implements Comparable<Student>{
    @Override
    public int CompareTo(Student o){
      
    }
  }
  //对象实现Comparable接口并实现CompareTo方法(a>b返回1为正序,返回-1位倒序)
```
##Set -> LinkedHashSet(继承自HashSet,是其子类)
LinkedHashSet集合也是根据元素hashCode值来决定元素存储位置,但它同时使用链表维护元素的次序.
这样使得元素看起来是以插入的顺序保存的,也就是说,当遍历LinkedHashSet集合里元素时.
HashSet将会按元素的添加顺序来访问集合里的元素。

##List -> ArrayList(继承了AbstractList抽象类并实现List接口)
终于轮到我们最最熟悉的ArrayList集合了.ArrayList顾名思义就是用Array数组实现的集合.

优点是在末位新增特别快,缺点是删除比较慢.

##List -> LinkedList(继承自AbstractSequentialList抽象类(继承于AbstractList抽象类),实现List和Deque接口)
ArrayList是基于数组实现的,而LinkedList是基于链表的数据结构实现的.
阅读代码:
```java
    transient int size = 0;
    //集合的长度
    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;
    //指向链表的第一个节点
    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;
    //指向链表的最后一个节点	
    /**
     * Constructs an empty list.
     */
    public LinkedList() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param  c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    //如果是以其它集合为构造函数的参数会先调用LinkedList的默认无参的构造函数,然后再调用addAll方法.
```
链表的数据结构:
```java
   private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
   }
```
LinkedList的add方法:
```java
   public boolean add(E e) {
       linkLast(e);
       return true;
   }
   ...
   void linkLast(E e) {
       final Node<E> l = last;
       final Node<E> newNode = new Node<>(l, e, null);
       last = newNode;
       if (l == null)
           first = newNode;
       else
           l.next = newNode;
       size++;
       modCount++;
   }   
```
调用add方法就是调用linkLast方法:判断链表的最后一个节点last是不是为null,为null(代表集合为null)则把新节点e作为第一个first节点

不为空则把最后一个节点last的的next节点指向新节点e即可(modCount是AbstractList定义的字段,代表集合修改的次数).

再来看看LinkedList的get(int index)方法:
```java
   public E get(int index) {
       checkElementIndex(index);
       return node(index).item;
   }
   ...
   Node<E> node(int index) {
       // assert isElementIndex(index);

       if (index < (size >> 1)) {
           Node<E> x = first;
           for (int i = 0; i < index; i++)
               x = x.next;
           return x;
       } else {
           Node<E> x = last;
           for (int i = size - 1; i > index; i--)
               x = x.prev;
           return x;
       }
   }
```
checkElementIndex方法相当于一个assert断言,当输入的index不正确时会抛出错误,程序无法执行下去.

get方法最后return了一个node节点的item值(因为一个node类型包含item,prev,next三个node实例对象).

在node(int index)方法中可以看到为了获得索引值为index的数据,都要用for循环让node节点追溯index次

(循环之前使用中位值size>>1与index比较大小来剔除不必要的遍历)

符合LinkedList适合删除添加数据,不适合取数据的场景.

##List -> Vector(继承自AbstractList抽象类并实现List接口)
阅读Vector的源码可以发现Vector同样是用数组来实现的,只是许多操作集合的方法使用了synchronized同步关键字

来实现了多线程之间使用集合的线程安全.

##*Java集合的Map接口
```java
   public interface Map<K,V>{
   	int size();	  //返回map中key-value映射的个数
	boolean isEmpty();//map中key-value映射的个数大于零时返回true
	boolean containsKey(Object key);//map中含有key时返回true(keyb是对象时会先判断hashCode,相等再判断equals)
	boolean containsValue(Object value);//同上
	V get(Object key);//返回key对应的value值
	V put(K key,V value);//返回key之前对应的value值(或null)
	V remove(Object key);//返回key之前对应的value值(或null)
	void putAll(Map<? extends K, ? extends V> m);
	void clear();	     //清空map
	Set<K> keySet();     //以set集合(Set接口的实现类)的形式返回map中包含的所有key
	Collection<V> values();//以Collection集合(Collection接口的实现类)的形式返回map中包含的所有key
	Set<Map.Entry<K, V>> entrySet();
	//在我看来Entry接口就是对map中key-value映射的封装,同时持有key和key对应的value.
	//源码中是这样说的:return a set view of the mappings contained in this map(返回map中)
	//Entry是一个接口,entrySetg方法返回一个包含Entry接口实现的类的set集合.
	interface Entry<K,V> {
   		K getKey();
		V getValue();
		V setValue(V value);
		boolean equals(Object o);
		int hashCode();
   	}
	boolean equals(Object o);
	int hashCode();
   }
```
与有抽象类AbstractList,AbstractSet分别实现List,Set接口一样,抽象类AbstractMapc实现Map接口.

我们来看AbstractMap中size方法的实现:
```java
    public int size() {
        return entrySet().size();
    }
```
是不是对Entry概念有更清晰的认识.

再看迭代器:
```java
	//AbstractMap中的entrySet
	public abstract Set<Entry<K,V>> entrySet();
	//因为entrySet是一个set的实现类,继承自AbstractCollection抽象类
	//在AbstractCollection抽象类中有如下代码:
	public abstract Iterator<E> iterator();
	//所以util下所有的集合实现类都可以直接使用iterator迭代器
	//(Iterator接口中定义了boolean hasNext(),E next()和void remove()三个方法)
```
##Map -> HashMap(继承AbstractMap抽象类并实现Map接口)
HashMap是基于哈希表实现的.(以下部分内容参考[有且仅有的路的博客](http://blog.csdn.net/u010297957/article/details/51974340))
###Hash表
####什么是哈希表
#####线性表和树
线性表、树 这些结构中,记录在结构中的相对位置是随机的,和记录的关键字之间不存在确定关系.

因此,在结构中查找时需要进行一系列和关键字的比较.这一类查找方法建立在“比较”的基础上.在顺序查找时,比较的结果为“=”与“≠”2种可能.

在折半查找、二叉排序树查找和B-树查找时,比较的结果为“<”“=”“>”3种可能,查找的效率依赖于查找过程中所进行的比较次数(看看上面LinkedList的查找代码).

#####哈希表
理想的情况是希望不经过任何比较,一次存取便能得到所查记录,那就必须在记录的存储位置和它的关键字之间建立一个确定的关系f.

使每个关键字和结构中一个唯一的存储位置相对应.因而在查找时,只要根据这个对应关系f找到给定值K的像f(K)(即要找的数据=f(K)).

若结构中存在关键字和K相等的记录,则必定在f(K)的存储位置上,反之在这个位置上没有记录.

由此,不需要比较便可直接取得所查记录.在此,我们称这个对应关系f为哈希(Hash)函数 ,按这个思想建立的表为哈希表.

#####哈希函数

*灵活 

哈希函数是一个映像,因此哈希函数的设定很灵活,只要使得任何关键字由此所得的哈希函数值都落在表长允许的范围之内即可.

*冲突 

对不同的关键字可能得到同一哈希地址,即key1≠key2,而f(key1)=f(key2) ,这种现象称为冲突（collision）.

冲突只能尽量地少,而不能完全避免.因为,哈希函数是从关键字集合到地址集合的映像,而通常关键字集合比较大,

它的元素包括所有可能的关键字,而地址集合的元素仅为哈希表中的地址值.

因此,在实现哈希表这种数据结构的时候不仅要设定一个“好”的哈希函数,而且要设定一种处理冲突的方法.

###HashMap的数据结构:
![image](https://github.com/zhangff01/Java-Summary/blob/master/Java-Collection/hashmap%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.png)
Entry类的代码:
```java
    static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        int hash;
	...
    }
```
查看HashMap中的部分源码:
```java
    static final Entry<?,?>[] EMPTY_TABLE = {};

    transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;
    
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);

        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();
    }
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
```
可以看到HashMap的构造函数最终都是调用HashMap(int initialCapacity, float loadFactor)这个构造函数.

实际上构造函数只是校验了一下参数(HashMap的大小和加载因子),然后执行了这个操作:threshold = initialCapacity.

我们看HashMap的数据结构图可以得知,HashMap的大小就是数组的大小,就是代码中的table变量,此时table数组指向{}的.

threshold这个变量在HashMap初始化之后如果不做任何操作(也就是为空),是没有用的.

只有HashMap进行操作时HashMap才会真正创建大小为threshold(也就是我们传入的初始化大小initialCapacity)的数组.

看下面的put操作:
```java
    public V put(K key, V value) {
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);
        int i = indexFor(hash, table.length);
	//如果key在链表中已存在,则替换为新value(就是覆盖)
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;
	//key不存在,就在table指定位置之处新增Entry
        addEntry(hash, key, value, i);
        return null;
    }
    private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize
	//计算出大于toSize最临近的2的N此方的值,假设此处传入6,那么最临近的值为2的3次方,也就是8
        int capacity = roundUpToPowerOf2(toSize);

        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
	//创建Entry数组，这个Entry数组就是HashMap所谓的容器
        table = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }
```
如果table为空,则会创建大小为(int)roundUpToPowerOf2(传入参数)*loadFactor的Entry数组.然后根据hash函数得到key对应的hashcode.

调用indexFor方法得到应该存放在table数组的位置(这里指索引),如果key在链表中已存在,则替换为新value,就是覆盖原来的value值.

并且返回oldValue;key不存在,就在table指定位置之处新增Entry.

我们再来看remove方法的源码实现:
```java
    public V remove(Object key) {
        Entry<K,V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }
    final Entry<K,V> removeEntryForKey(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);
        Entry<K,V> prev = table[i];
        Entry<K,V> e = prev;

        while (e != null) {
            Entry<K,V> next = e.next;
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k)))) {
                modCount++;
                size--;
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                e.recordRemoval(this);
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }
```
可以看到remove方法实际上是调用removeEntryForKey(顾名思义是用过key值来删除Entry对象),如果map为空返回null.

根据key得到与之对应的hashcode和table数组的索引值,然后对table[index]值(链表结构)进行遍历,找到与key值相同的Entry对象.
##Map -> LinkedHashMap(继承HashMap类并实现Map接口)




