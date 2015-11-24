package opengltrialfirstattemptofonemillion;

import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.*;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class Game extends GLCanvas implements GLEventListener {

    private static final long serialVersionUID = 1L;

    private int xPos = 2;
    GLCanvas canvas;
    final private int width = 800;
    final private int height = 600;
    FPSAnimator animator = null;
    private GLU glu;

    public Game() {
        this.addGLEventListener(this);

    }

    public void play() {
//        animator = new FPSAnimator(canvas, 60, true);
//        animator.start();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();
        //call your draw code here

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\jvdwi\\Documents\\[School]\\FONTYS\\Jaar 2\\Semester 3\\PTS3\\Software\\PTS31D\\StandAlone\\HumanBlueDown1.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Graphics2D g = image.createGraphics();
        g.drawImage(image, null, null);

//        canvas.destroy();
//        Graphics g = canvas.getGraphics();
//        g.clearRect(0, 0, width, height);
//
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new File("C:\\Users\\jvdwi\\Documents\\[School]\\FONTYS\\Jaar 2\\Semester 3\\PTS3\\Software\\PTS31D\\StandAlone\\HumanBlueDown1.png"));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        g.drawImage(image, xPos, xPos, this);
//        xPos++;
//        canvas.display();
        gl2.glFlush();

    }

    public GLCanvas getCanvas() {
        return canvas;
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
        //gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}
