package star.wars;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class LukeSkywalker extends Karakter {

    int can;
    static String imgPath = "images\\LukeSkywalker.jpg";

    public LukeSkywalker(String ad, int tur, int x, int y, int can) {
        super(ad, tur, x, y);
        this.can=can;
    }

    public void drawPlayer(Graphics g) {
        ImageIcon img = new ImageIcon(imgPath);
        int PosX = x * katsayi + Mx;
        int PosY = y * katsayi + My;
        g.drawImage(img.getImage(), PosY, PosX, null);
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

}
