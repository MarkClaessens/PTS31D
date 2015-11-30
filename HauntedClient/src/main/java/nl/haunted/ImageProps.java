/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author jvdwi
 */
public class ImageProps {
    private Image[] images;
    private Entity entity;
    
    public Image[] getImg() {
        return images;
    }

    public Entity getEntity() {
        return entity;
    }
    
    public ImageProps(Image[] img, Entity entity){
        this.images = img;
        this.entity = entity;
    }
}
