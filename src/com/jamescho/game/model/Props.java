package com.jamescho.game.model;

import java.awt.Rectangle;

/*
 * һ�������࣬��������ʱ����١�
 * ���ٷ���������Ball�࣬�������ײ�������PlayState���update()����
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
