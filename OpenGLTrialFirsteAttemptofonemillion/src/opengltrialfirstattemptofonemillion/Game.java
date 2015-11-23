package opengltrialfirstattemptofonemillion;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jvdwi
 */
public class Game extends JFrame implements GLEventListener {

    private static final long serialVersionUID = 1L;

    GLCanvas canvas;
    final private int width = 800;
    final private int height = 600;

    public Game() {
        super("Minimal OpenGL");
        GLProfile profile = GLProfile.get(GLProfile.GL4);
        GLCapabilities capabilities = new GLCapabilities(profile);

        canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);

        this.setName("Minimal OpenGL");
        Button bt = new Button("Test");
        bt.setSize(30, 30);
        Button bt2 = new Button("Another Test");
        bt2.setSize(40,40);
        Button bt3 = new Button("More tests");
        bt3.setSize(50,50);
        this.getContentPane().add(canvas);
        this.getContentPane().add(bt);
        this.getContentPane().add(bt2);
        this.getContentPane().add(bt3);

        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
    }

    public void play() {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);

        //call your draw code here
        canvas.destroy();
        Graphics g = canvas.getGraphics();
        g.clearRect(0, 0, width, height);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\jvdwi\\Documents\\[School]\\FONTYS\\Jaar 2\\Semester 3\\PTS3\\Software\\PTS31D\\StandAlone\\HumanBlueDown1.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        g.drawImage(image, 20, 20, this);

        canvas.display();

        gl.glFlush();

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        gl.glClearColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

}
