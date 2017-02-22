#策略模式(StrategyPattern)
##定义
策略模式属于用户的行为模式,其用意是针对一组算法(策略/方法),封装到具有共同接口的独立类中.

从而使得它们可以相互替换.策略模式可是使算法在不影响客户端的情况下发生变化.
##实现
策略模式的构成有三部分:使用策略的类,策略接口和一组实现策略接口的实现类(算法组).

**代码**
```java
/**
 * @description 使用策略的类
 * @author zhangff01
 */
public class Person {
    //策略接口
    private Workable work;
    //注入策略
    public Person(Workable work){
        this.work=work;
    }
    
    public void sleep(){
        System.out.println("I need sleep");
    }
    //使用策略方法
    public void workmethod(){
        work.work();
    }
}
```
策略接口以及策略实现组:
```java
/**
 * @description 策略接口
 * @author zhangff01
 */
public interface Workable {
    //策略方法
    public void work();
}
...
/**
 * @description 策略实现1
 * @author zhangff01
 */
public class WorkByStudy implements Workable {
    @Override
    public void work() {
        System.out.println("I work through study");
    }
}
...
/**
 * @description 策略实现2
 * @author zhangff01
 */
public class WorkByTeach implements Workable {
    @Override
    public void work() {
        System.out.println("I work through teach");
    }
}
```
测试代码:
```java
public class TestAction {
    public static void main(String[] args){
        Person p1=new Person(new WorkByStudy());
        p1.workmethod();
        Person p2=new Person(new WorkByTeach());
        p2.workmethod();
    }
}
...运行结果
I work through study
I work through teach
```
##特点
- 优点:灵活性好,对于不同的策略只需要实现并替换即可.
- 缺点:
    - 使用者必须知道所有的策略类,并自行决定使用哪一个策略类.
    - 如果需要的策略太多,那么策略实现类会很庞大.
