#装饰者模式(DecoratePattern)
##定义
动态的将责任添加到对象上.若要扩展功能,装饰者提供了比继承更有弹性的替代方案.
##实现
装饰者模式分为两个部分,装饰者和被装饰者.被装饰者通常是我们考虑的主体.

装饰者与被装饰者拥有共同的超类,继承的目的是继承类型,而不是行为.

被装饰者通常由抽象接及其实现类构成:
```java
/**
 * @description 被装饰者基类
 * @author zhangff01
 */
public abstract class Humburger {
    
    protected String name;
    
    public String getName(){
        return name;
    }
    
    public abstract double getPrice();
}
...
/**
 * @description 被装饰者基类的简单实现
 * @author zhangff01
 */
public class BeefHumburger extends Humburger {
    
    public BeefHumburger(){
        this.name="牛肉汉堡";
    }
    
    @Override
    public double getPrice() {
        return 20.0;
    }
}
```
装饰者由装饰者抽象类(继承自被装饰者,目的是继承类型)和具体实现类构成:
```java
/**
 * @description 装饰者基类-装饰者与被装饰者拥有共同的超类,继承的目的是继承类型,而不是行为
 * @author zhangff01
 */
public abstract class Condiment extends Humburger {
    public abstract String getName();
}
...
/**
 * @description 装饰者1
 * @author zhangff01
 */
public class TomatoCondiment extends Condiment {
    
    private Humburger hum;
    
    public TomatoCondiment(Humburger hum){
        this.hum=hum;
    }
    
    @Override
    public String getName() {
        return "番茄"+hum.getName();
    }
    @Override
    public double getPrice() {
        return hum.getPrice()+3.0;
    }
}
...
/**
 * @description 装饰者2
 * @author zhangff01
 */
public class EggCondiment extends Condiment {
    
    private Humburger hum;
    
    public EggCondiment(Humburger hum) {
        this.hum=hum;
    }
    
    @Override
    public String getName() {
        return "鸡蛋"+hum.getName();
    }
    @Override
    public double getPrice() {
        return hum.getPrice()+4.0;
    }
}
```
测试代码:
```java
public class TestAction {
    public static void main(String[] args) {
        Condiment cd1=new TomatoCondiment(new EggCondiment(new BeefHumburger()));
        System.out.println(cd1.getName());
        System.out.println(cd1.getPrice());
    }
}
...运行结果
番茄鸡蛋牛肉汉堡
27.0
```
##描述
Java IO就是使用装饰者模式实现的.
