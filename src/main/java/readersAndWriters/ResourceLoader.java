package readersAndWriters;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ResourceLoader
{
    public static ImageIcon getImageIcon(String imageName)
    {
        ImageIcon imageIcon = null;
        URL imgURL = null;
        String path = null;
        imgURL = ResourceLoader.class.getClassLoader().getResource(imageName);
        if(imgURL != null)
        {
            imageIcon =  new ImageIcon(imgURL);
            Image img = imageIcon.getImage();
            imageIcon = new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }

        return(imageIcon);
    }
}
