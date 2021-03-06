package star.wars;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Darthvader extends Karakter {

    Enkisayol yol = new Enkisayol();
    static String imgPath = "images\\darthvader.jpg";
    int hedefx;
    int hedefy;
    int[][] map = new int[11][14];

    public Darthvader(String ad, int tur, int x, int y, int hedefx, int hedefy) {
        super(ad, tur, x, y);
        setHedefx(hedefx);
        setHedefy(hedefy);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        Enkisayol();
    }

    void Enkisayol() {
        yol.rota.clear();
        yol.Enkisamesafe(map, x, y, hedefx, hedefy);
    }

    public void drawBadplayer(Graphics g) {
        ImageIcon img = new ImageIcon(imgPath);
        int PosX = x * katsayi + Mx;
        int PosY = y * katsayi + My;
        g.drawImage(img.getImage(), PosY, PosX, null);
        for (int i = 2; i < yol.rota.size() - 2; i = i + 2) {
            int NodeX = yol.rota.get(i) * katsayi + Mx + 10;
            int NodeY = yol.rota.get(i + 1) * katsayi + My + 10;
            g.fillRect(NodeY, NodeX, katsayi / 2, katsayi / 2);
        }
    }

    public int getHedefx() {
        return hedefx;
    }

    public void setHedefx(int hedefx) {
        this.hedefx = hedefx;
    }

    public int getHedefy() {
        return hedefy;
    }

    public void setHedefy(int hedefy) {
        this.hedefy = hedefy;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
