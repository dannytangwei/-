package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;

//这里的状态也就是与界面差不多的意思
//创建一个抽象的状态类（超类），为其它的状态界面（子类）提供一个通用的结构
public abstract class State {
	
	 //当跳转到一个新的界面时，初始化将要使用的游戏对象
     public abstract void init();
     //游戏循环将在每一帧上调用当前状态的update方法，用于更新游戏对象
     public abstract void update();
     //游戏循环将在每一帧上调用当前状态的render方法，用于把图形渲染（绘制）到屏幕
     public abstract void render(Graphics g);
     //鼠标点击事件
     public abstract void onClick(MouseEvent e);
     //按下键盘事件
     public abstract void onKeyPress(KeyEvent e);
     //释放键盘事件
     public abstract void onKeyRelease(KeyEvent e);
     
     //自定义一个状态转换的方法
     public void setCurrentState(State newState){
    	 //在JFrame主窗口中的JPanel对象调用setCurrentState()方法
    	 //这样随便一个State的子界面作为参数，都可以传递到主窗口
    	 //之所以界面会转换，是因为setCurrentState()方法就是Game类中的那个方法
    	 //
    	 GameMain.sGame.setCurrentState(newState);
     }
}
