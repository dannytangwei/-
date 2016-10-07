package com.jamescho.framework.util;

import java.util.Random;

/*
 * ����һ���������һ�㲽�裺
 * 1.����Random���� 2.����nextInt(int n)����,����һ��0~n-1֮����������
 * ���ַ����������������Ǳ���Ҫ����һ��Random���󣬶���������ڴ档
 * ��Ϊ���Ǳ�д�Ĵ��붼������Ϸѭ��������ÿ��60�Σ���ִ�еģ�����ζ�ţ����������Paddle��
 * update���������д���һ��Random������ôÿ���ӻ�õ�60���µ�Random����
 * Ϊ�˽�������⣬���Ծ���com.jamescho.framework.util���д���һ��RandomNumberGenerator��
 * ��������ṩ��һ���������������Ҫÿ�ζ������µ�Random����
 */
public class RandomNumberGenerator {
	
     private static Random rand = new Random();
     //ʹ��static���Σ�����ͨ���������ã����Բ���������������
     public static int getRandIntBetween(int lowerBound, int upperBound)
     {
    	 return rand.nextInt(upperBound - lowerBound) + lowerBound;
     }
     
     public static int getRandInt(int upperBound)
     {
    	return rand.nextInt(upperBound);
     }
}
