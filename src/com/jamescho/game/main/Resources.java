package com.jamescho.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//在com.jamescho.game.main包中创建一个Resources类，从resources包中加载
//图片，并且将它们存储为可供游戏中其它类来访问的的公有变量。
public class Resources {
	
	public static BufferedImage welcome, iconimage, line;
	public static AudioClip hit, bounce;
	public static Color darkBlue, darkRed;
	
    public static void load(){
    	welcome = loadImage("welcome.png");
    	iconimage = loadImage("iconimage.png");
    	line = loadImage("line.png");
    	hit = loadSound("hit.wav");
    	bounce = loadSound("bounce.wav");
    	darkBlue = new Color(25, 83, 105);//蓝色背景（RGB：25,83,105）
    	darkRed = new Color(105, 13, 13);
    }
    
    private static BufferedImage loadImage(String filename){
    	BufferedImage img = null;
    	/*
    	 *  InputStream java.lang.Class.getResourceAsStream(String name)
    	 *  使用getResourcesAsStream方法，可通过资源名找到相应的资源
    	 */
    	try {
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//告知问题出现的地方
			System.out.println("无法找到请求文件：" + filename);
			e.printStackTrace();
		}
    	return img;
    }
    private static AudioClip loadSound(String filename){
    	URL fileURL = Resources.class.getResource("/resources/" + filename);
    	return Applet.newAudioClip(fileURL);
    }
}
