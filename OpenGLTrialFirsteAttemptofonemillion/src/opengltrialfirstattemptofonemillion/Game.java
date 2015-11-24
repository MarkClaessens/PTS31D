package opengltrialfirstattemptofonemillion;

import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.*;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
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
//http://stackoverflow.com/questions/10296685/draw-image-in-jogl2

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();
        //call your draw code here
        int widthIn = 0;
        int heightIn = 0;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\jvdwi\\Documents\\[School]\\FONTYS\\Jaar 2\\Semester 3\\PTS3\\Software\\PTS31D\\StandAlone\\HumanBlueDown1.png"));
            widthIn = image.getWidth();
            heightIn = image.getHeight();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, widthIn, heightIn, 4, null);
        ComponentColorModel colorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8, 8}, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);

        BufferedImage dukeImg = new BufferedImage(colorModel, raster, false, null);

        Graphics2D g = dukeImg.createGraphics();
        g.drawImage(image, null, null);

        DataBufferByte dukeBuf = (DataBufferByte) raster.getDataBuffer();
        byte[] dukeRGBA = dukeBuf.getData();
        ByteBuffer bb = ByteBuffer.wrap(dukeRGBA);

        bb.position(0);
        bb.mark();

        gl2.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        gl2.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        gl2.glEnable(GL2.GL_TEXTURE_2D);
        gl2.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_DECAL);
        gl2.glBindTexture(GL2.GL_TEXTURE_2D, 13);
        gl2.glPixelStorei(GL2.GL_UNPACK_ALIGNMENT, 1);

        gl2.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, widthIn, heightIn, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, bb);

        int left = 100;
        int top = 100;

        gl2.glBegin(GL2.GL_QUADS);
        gl2.glTexCoord2d(0, 0);
        gl2.glVertex3f(left, top, 0);
        gl2.glTexCoord2d(1, 0);
        gl2.glVertex3f(left + widthIn, top, 0);
        gl2.glTexCoord2d(1, 1);
        gl2.glVertex3f(left + widthIn, top + heightIn, 0);
        gl2.glTexCoord2d(0, 1);
        gl2.glVertex3f(left, top + heightIn, 0);
        gl2.glEnd();
        
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
        GL2 gl = drawable.getGL().getGL2();

        if (height == 0) {
            height = 1;
        }
        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);
        
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

}
