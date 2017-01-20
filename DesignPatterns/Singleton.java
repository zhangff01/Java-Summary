/**
 * @author zhangff01
 * @description 单例模式
 */
public class Singleton {
	
	/**
	 * 第一种饿汉模式,JVM在解析类时会初始化一个实例,不管你会不会调用
	 */
	private Singleton(){}
	
	private static Singleton instance=new Singleton();
	
	public static Singleton getInstance(){
		return instance;
	}
	
	/**
	 * 第二种懒汉模式,第一次调用时才会初始化一个实例，以后再调用不会再初始化
	 * 考虑到线程安全的话可以使用synchronized同步
	 */
	/*
	private Singleton(){}
	
	private static Singleton instance;
	
	public static Singleton getInstance(){
		if(instance==null){
			instance=new Singleton();
		}
		return instance;
	}
	*/
}
