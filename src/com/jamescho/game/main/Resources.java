package com.jamescho.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//��com.jamescho.game.main���д���һ��Resources�࣬��resources���м���
//ͼƬ�����ҽ����Ǵ洢Ϊ�ɹ���Ϸ�������������ʵĵĹ��б�����
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
    	darkBlue = new Color(25, 83, 105);//��ɫ������RGB��25,83,105��
    	darkRed = new Color(105, 13, 13);
    }
    
    private static BufferedImage loadImage(String filename){
    	BufferedImage img = null;
    	/*
    	 *  InputStream java.lang.Class.getResourceAsStream(String name)
    	 *  ʹ��getResourcesAsStream��������ͨ����Դ���ҵ���Ӧ����Դ
    	 */
    	try {
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//��֪������ֵĵط�
			System.out.println("�޷��ҵ������ļ���" + filename);
			e.printStackTrace();
		}
    	return img;
    }
    private static AudioClip loadSound(String filename){
    	URL fileURL = Resources.class.getResource("/resources/" + filename);
    	return Applet.newAudioClip(fileURL);
    }
}
