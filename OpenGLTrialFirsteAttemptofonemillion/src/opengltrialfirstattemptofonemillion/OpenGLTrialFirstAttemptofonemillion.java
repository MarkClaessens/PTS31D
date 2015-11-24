/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opengltrialfirstattemptofonemillion;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.*;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author jvdwi
 */
public class OpenGLTrialFirstAttemptofonemillion extends JFrame {

    private static String TITLE = "JOGL 2.0 Setup (GLCanvas)";  // window's title
    private static final int CANVAS_WIDTH = 640;  // width of the drawable
    private static final int CANVAS_HEIGHT = 480; // height of the drawable
    private static final int FPS = 60; // animator's target frames per second

    public OpenGLTrialFirstAttemptofonemillion() {
      // Create the OpenGL rendering canvas
      GLCanvas canvas = new Game();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Create a animator that drives canvas' display() at the specified FPS.
      final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
 
      // Create the top-level container frame
      this.getContentPane().add(canvas);
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            // Use a dedicate thread to run the stop() to ensure that the
            // animator stops before program exits.
            new Thread() {
               @Override
               public void run() {
                  if (animator.isStarted()) animator.stop();
                  System.exit(0);
               }
            }.start();
         }
      });
      this.setTitle(TITLE);
      this.pack();
      this.setVisible(true);
      animator.start(); // start the animation loop
   }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OpenGLTrialFirstAttemptofonemillion();  // run the constructor
            }
        });
    }
    
}
