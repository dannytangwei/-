package com.jamescho.game.model;

import java.awt.Rectangle;

/*
 * 一个道具类，当球碰到时会加速。
 * 加速方法设置在Ball类，处理的碰撞的语句在PlayState类的update()方法
 */
public class Props { 
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle rect;
	
	public Props(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getRect()
	{
		return rect;
	}
	
}
