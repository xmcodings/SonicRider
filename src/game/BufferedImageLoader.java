/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BufferedImageLoader {

    private BufferedImage image;
    
    public BufferedImage loadImage(String path){
        
        try {
           File file = new File(path);
            image =  ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        return image;
    }
    
}