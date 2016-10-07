package com.jamescho.game.model;

import java.awt.Rectangle;

import com.jamescho.game.main.GameMain;

public class Paddle {
	//定义挡板的坐标，以及宽与高，
	//velY变量为速率，表示挡板在每一帧中移动的像素数，即如果没有限制的话会不停的运动！！！！！
    private int x, y, width, height, velY;
    //定义一个边界矩形，用来判断挡板与球的碰撞
    private Rectangle rect;
    private final static int MOVE_SPEED_Y =12;
    
    //定义构造器
    public Paddle(int x, int y, int width, int height)
    {
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	rect = new Rectangle(x, y, width, height);
    	velY = 0;
    }
    
    //移动挡板
    public void update(){
    	//在每一帧中将velY和y相加一次，其效果就是将挡板移动到一个y坐标值为（y+velY）的位置
    	y += velY;
    	
    	if(y < 0)   //限制挡板不会移动到界面外边
    	{
    		y = 0;
    	}
    	else if (y + height > GameMain.GAME_HEIGHT)
    	{
    		y = GameMain.GAME_HEIGHT - height;
    	}
    	updateRect();//将边界矩形移动到相同的位置
    }
    //同步更新矩形边界
    private void updateRect(){
    	rect.setBounds(x, y, width, height);
    }
    public void accelUp(){
    	//这里的移动速度是每帧-5
    	//velY = -5;
    	velY = -MOVE_SPEED_Y;//用常量减少依赖性
    }
    public void accelDown(){
    	//velY = 5;
    	velY = MOVE_SPEED_Y;
    }
    public void stop(){
    	velY = 0;
    }
    //实例变量都是private修饰，创建getter方法，供其它类读取Paddle对象的实例变量
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
    	return height;
    }
    public Rectangle getRect(){
    	return rect;
    }
}
