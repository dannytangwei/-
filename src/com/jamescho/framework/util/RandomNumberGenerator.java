package com.jamescho.framework.util;

import java.util.Random;

/*
 * 创建一个随机数的一般步骤：
 * 1.创建Random对象 2.调用nextInt(int n)方法,产生一个0~n-1之间的随机数；
 * 这种方法的问题在于我们必须要创建一个Random对象，而这会消耗内存。
 * 因为我们编写的代码都是在游戏循环（至少每秒60次）中执行的，这意味着，如果我们在Paddle的
 * update（）方法中创建一个Random对象，那么每秒钟会得到60个新的Random对象。
 * 为了解决此问题，所以就在com.jamescho.framework.util包中创建一个RandomNumberGenerator类
 * 这样框架提供了一个随机数，而不需要每次都创建新的Random对象
 */
public class RandomNumberGenerator {
	
     private static Random rand = new Random();
     //使用static修饰，可以通过类来调用，可以不创建对象来调用
     public static int getRandIntBetween(int lowerBound, int upperBound)
     {
    	 return rand.nextInt(upperBound - lowerBound) + lowerBound;
     }
     
     public static int getRandInt(int upperBound)
     {
    	return rand.nextInt(upperBound);
     }
}
