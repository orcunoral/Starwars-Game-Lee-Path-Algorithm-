package star.wars;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class MasterYoda extends Karakter {

    int can;
    static String imgPath = "images\\MasterYoda.jpg";

    public MasterYoda(String ad, int tur, int x, int y, int can) {
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
