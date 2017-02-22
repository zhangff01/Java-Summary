#工厂模式(FactoryPattern)
##定义
定义了一个创建对象的接口,但是由子类决定要实例化的类是哪一个.工厂方法让类把实例化推迟到子类.
##实现
工厂模式主要分为简单工厂模式,工厂方法模式和抽象工厂模式.
###简单工厂模式
有一个产品抽象接口以及其实现类,一个具体的工厂类构成:

产品接口以及其实现类:
```java
/**
 * @description 抽象产品类
 * @author zhangff01
 */
public abstract class Car {}
...
/**
 * @description 具体产品类-Chery
 * @author zhangff01
 */
public class Chery extends Car {
    public Chery(){
        System.out.println("你获得了一辆奇瑞汽车");
    }
}
...
/**
 * @description 具体产品类-JAC
 * @author zhangff01
 */
public class JAC extends Car {
    public JAC(){
        System.out.println("你获得了一辆江淮汽车");
    }
}
```
具体的工厂:
```java
/**
 * @description 工厂
 * @author zhangff01
 */
public class CarFactory {
    public Car createCar(String type){
        if(type.equals("chery")){
            return new Chery();
        }else if(type.equals("jac")){
            return new JAC();
        }else{
            return null;
        }
    }
}
```
测试:
```java
/**
 * @description 测试代码-顾客
 * @author zhangff01
 */
public class Customer {
    public static void main(String[] args) {
        CarFactory factory=new CarFactory();
        Car car1=factory.createCar("chery");
        Car car2=factory.createCar("jac");
    }
}
...输出结果
你获得了一辆奇瑞汽车
你获得了一辆江淮汽车
```
这是最简单的一种工厂模式,是我们对工厂模式的初步认识.
###工厂方法模式
工厂方法模式两部分构成:
- 一个产品抽象接口及其实现类.
- 一个工厂抽象接口及其实现类.

与简单工厂模式相比,工厂方法模式的工厂变得复杂了,由具体的工厂变成了抽象的及其实现的工厂.

产品抽象接口及其实现类和简单工厂模式一样.

工厂抽象接口及其实现类:
```java
/**
 * @description 抽象工厂
 * @author zhangff01
 */
public interface CarFactory {
    public Car createCar();
}
...
/**
 * @description 具体工厂1
 * @author zhangff01
 */
public class CheryCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Chery();
    }
}
...
/**
 * @description 具体工厂2
 * @author zhangff01
 */
public class JACCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new JAC();
    }
}
```
测试代码:
```java
/**
 * @description 测试代码-顾客
 * @author zhangff01
 */
public class Customer {
    public static void main(String[] args) {
        Car chery=new CheryCarFactory().createCar();
        Car jac=new JACCarFactory().createCar();
    }
}
...输出结果
你获得了一辆奇瑞汽车
你获得了一辆江淮汽车
```
工厂方法模式仿佛已经很完美的对对象的创建进行了包装.

但是当产品种类非常多时,会出现大量的与之对应的工厂对象,这不是我们所希望的.
###抽象工厂模式
抽象工厂模式的构成:
- **多个产品抽象接口及其实现类**.
- 一个工厂抽象接口及其实现类.

可以看出,与工厂方法模式相比,抽象工厂模式可以有多个产品组(产品抽象类及其实现类).

产品抽象接口及其实现类:

第一组产品-水果:
```java
/**
 * @description 抽象产品类-水果
 * @author zhangff01
 */
public abstract class Fruit {
    protected String name;
    public Fruit(String name){
        this.name=name;
    }
}
...
/**
 * @description 抽象产品类Fruit实现类-苹果
 * @author zhangff01
 */
public class Apple extends Fruit {
    public Apple(String name) {
        super(name);
        System.out.println(this.name+"苹果");
    }
}
/**
 * @description 抽象产品类Fruit实现类-葡萄
 * @author zhangff01
 */
public class Grape extends Fruit {
    public Grape(String name) {
        super(name);
        System.out.println(this.name+"葡萄");
    }
}
```
第一组产品-肉制品:
```java
/**
 * @description 抽象产品类-肉制品
 * @author zhangff01
 */
public abstract class Meat {
    protected String name;
    public Meat(String name){
        this.name=name;
    }
}
...
/**
 * @description 抽象产品类Meat实现类-牛肉
 * @author zhangff01
 */
public class Beef extends Meat {
    public Beef(String name) {
        super(name);
        System.out.println(this.name+"牛肉");
    }
}
/**
 * @description 抽象产品类Meat实现类-羊肉
 * @author zhangff01
 */
public class Mutton extends Meat {
    public Mutton(String name) {
        super(name);
        System.out.println(this.name+"羊肉");
    }
}
```
抽象工厂类及其实现类:
```java
/**
 * @description 抽象工厂类-超市
 * @author zhangff01
 */
public abstract class Supermarket {
    private String name;
    public Supermarket(String name){
        this.name=name;
    }
    //通过出售水果的动作来生成水果类
    public abstract Fruit sellFruit();
    //通过出售肉制品的动作来生成肉制品类
    public abstract Meat sellMeat();
}
...
/**
 * @description 抽象工厂类-实现类1
 * @author zhangff01
 */
public class SupermarketOne extends Supermarket {
    public SupermarketOne(String name) {
        super(name);
        System.out.println(name+":");
    }
    @Override
    public Fruit sellFruit() {
        return new Apple("红富士");
    }
    @Override
    public Meat sellMeat() {
        return new Beef("澳大利亚新鲜");
    }
    
}
/**
 * @description 抽象工厂类-实现类1
 * @author zhangff01
 */
public class SupermarketTwo extends Supermarket {
    public SupermarketTwo(String name) {
        super(name);
        System.out.println(name+":");
    }
    @Override
    public Fruit sellFruit() {
        return new Grape("国外进口");
    }
    @Override
    public Meat sellMeat() {
        return new Mutton("新西兰优质");
    }
}
```
测试代码:
```java
/**
 * @description 测试代码
 * @author zhangff01
 */
public class Customer {
    public static void main(String[] args) {
        Supermarket s1=new SupermarketOne("家乐福");
        Fruit apple=s1.sellFruit();
        Meat beef=s1.sellMeat();
        
        Supermarket s2=new SupermarketTwo("大润发");
        Fruit grape=s2.sellFruit();
        Meat mutton=s2.sellMeat();
    }
}
...输出结果
家乐福:
红富士苹果
澳大利亚新鲜牛肉
大润发:
国外进口葡萄
新西兰优质羊肉
```
主要目的还是根据不同的通常来获取对应的Fruit和Meat对象.
