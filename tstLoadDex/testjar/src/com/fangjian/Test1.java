package com.fangjian;

public class Test1 implements ITest {
	
	public Test1(){};

	@Override
	public String hello() {
		return "hello";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test1 t1=new Test1();
		System.out.println(t1.hello());
		System.out.println(String.class.getClassLoader());
		System.out.println(Test1.class.getClassLoader());
		
		try {
			Class T1Clazz=ClassLoader.getSystemClassLoader().loadClass("com.fangjian.Test1");
			ITest t2=(ITest)T1Clazz.newInstance();
			System.out.println("t2:"+t2.hello());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
