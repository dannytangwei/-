package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.Resources;

//LoadState类表示游戏的加载界面
//在com.jamescho.game.state包中创建LoadState类，并且扩展State。
public class LoadState extends State{

	//注意，在写完public class LoadState extends State这一条语句时，
    //点击有红色下划线的LoadState,自动生成抽象方法！
	@Override
	public void init() {
		//加载Resources类中的资源
		Resources.load();//因为load()是static修饰的静态方法，所以可以由Resources类直接调用
		System.out.println("加载成功");
	}

	@Override
	public void update() {
		// 继承父类的状态转变方法，从LoadState转换到MenuState
		//即调用Game类中的setCurrentState()方法，同时也更新了Game中的currentState的值
		setCurrentState(new MenuState());
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
     
	
	
}
