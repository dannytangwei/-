package com.jamescho.game.model;

import java.awt.Rectangle;

import com.jamescho.framework.util.RandomNumberGenerator;
import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

/*
 * 设计球：
 * 1.Ball中的变量
 *    坐标和大小：
 *    边界矩形：
 *    速率：
 *    
 *  2.Ball类的方法  
 *  
 *  
 */
public class Ball {
     
	private static final int VEL_X = 10;
	private int x, y, width, height,velX,velY;
	private Rectangle rect;
	
	public Ball(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		velX = VEL_X;
		velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
		rect = new Rectangle(x, y, width, height);
	}
	public void update()
	{
		x += velX;
		y += velY;
		correctYCollisions();
		updateRect();
	}
	//判断是否碰到上下边界（根据坐标来判断碰撞）
	private void correctYCollisions()//因为不需要被其它类调用，所有选择private修饰
	{
		if(y < 0)
		{   //在这里只是停留一帧的时间因为小球是每帧都在不停地运动
			y = 0;
		}
		else if(y + height > GameMain.GAME_HEIGHT)
		{
			y = GameMain.GAME_HEIGHT-height;
		}
		else
		{
			return;//如果没有碰到上下边界，则结束该方法
		}
		velY = -velY;//球回弹
		Resources.bounce.play();//调用Resources中的声音资源，发出碰撞的声音
	}
	//同步更新矩形边界
	private void updateRect()
	{
		rect.setBounds(x, y, width, height);
	}
	
	public void onCollideWith(Paddle p){
		//当发生碰撞时，先判断是碰到了哪边的球拍
		if(x < GameMain.GAME_WIDTH / 2)
		{   //如果碰到左边球拍，让球的位置刚好位于球拍之前，这样做的原因是为了
			//避免球不停的在球拍之中碰撞。
			x = p.getX() + p.getWidth();
		}
		else
		{
			x = p.getX() - width;
		}
		velX = -velX;
		velY = RandomNumberGenerator.getRandIntBetween(-2, 3);
	}
	public boolean isDead()
	{
		return (x < 0 || x + width > GameMain.GAME_WIDTH);
	}
	public void reset()//死亡后重新绘制小球
	{
		x = GameMain.GAME_WIDTH/2-60/2;
		y = 280;
		if(RandomNumberGenerator.getRandInt(2) ==1)
		{
			velX = VEL_X;
		}
		else{
			velX = -VEL_X;
		}
		
		velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
	}
	public void increaseSpeed()//加速方法
	{
		velX += velX*0.111112 ;
		//velY = RandomNumberGenerator.getRandIntBetween(1, 2);
	}
	public void changeDirection()
	{
		velY = RandomNumberGenerator.getRandIntBetween(-10, 10);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidty()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public Rectangle getRect()
	{
		return rect;
	}
	
}
