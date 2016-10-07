package com.jamescho.framework.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jamescho.game.state.State;

/*
 * InputHandler是一个监听器类
 * 它实现了KeyListener与MouseListener接口
 */
public class InputHandler implements KeyListener, MouseListener{
	
	private State newCurrentState;//创建一个新的State类型的实例变量
	//这个方法用的参数是State类型,意味着随便一个界面状态传递进来都会使newCurrentState得到更新。
	public void setCurrentState(State currentState)
	{
		this.newCurrentState = currentState;
		System.out.println("这是InputHandler现在的界面状态。。。" + newCurrentState);
	}
    
	/*
	 * 当点击鼠标时，触发此方法
	 * mouseClicked(MouseEvent e)方法，就是事件处理器，其中的参数MouseEvent是鼠标事件，当进行单击、
	 * 按下、松开、移动鼠标等动作时，触发该鼠标事件，从而启动方法（事件处理器），执行方法体内的语句。
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		newCurrentState.onClick(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		newCurrentState.onKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		newCurrentState.onKeyRelease(e);
	}

}
