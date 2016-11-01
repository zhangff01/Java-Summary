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

