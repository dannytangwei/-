package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.Resources;

//菜单界面
public class MenuState extends State{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("进入菜单界面");
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Resources.welcome, 0, 0, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("您点击了鼠标");
		//void com.jamescho.game.state.State.setCurrentState(State newState)
        //此时显示在屏幕的是MenuState界面，所以在这里进行事件处理
		//点击屏幕触发此方法，跳转到游戏过程界面
		setCurrentState(new PlayState());
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("您按下了键盘");
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("您松开了键盘");
	}

}
