package star.wars;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static star.wars.StarWars.rd;

public class Anapencere extends JPanel implements KeyListener {

    int tur = 0;
    private JFrame frame = new JFrame();
    private ImageIcon map = new ImageIcon("images\\map.png");
    private ImageIcon kalp = new ImageIcon("images\\kalp.png");
    private ImageIcon yarimkalp = new ImageIcon("images\\yarimkalp.png");
    MasterYoda my;
    LukeSkywalker sky;
    int Bx = 5, By = 6;//Oyuncunun baişangıç noktaları
    ArrayList<Stormtrooper> Stormtrooper = new ArrayList<>();
    ArrayList<Darthvader> Darthvader = new ArrayList<>();
    ArrayList<KyloRen> KyloRen = new ArrayList<>();
    String name;

    public Anapencere(String oyuncu) {
        this.name = oyuncu;
        if (name.equals("Luke Skywalker")) {
            sky = new LukeSkywalker(name, tur, Bx, By, 3);
        } else {
            my = new MasterYoda(name, tur, Bx, By, 6);
        }
        Harita(0);
        this.setFocusable(true);
        this.addKeyListener(this);
        frame.add(this);
        frame.setSize(570, 545);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(map.getImage(), 0, 0, null);
        int z = 490;
        if (sky != null) {
            if (sky.can == 0) {
                JOptionPane.showMessageDialog(null, "Kaybettiniz");
                frame.dispose();
            }
            System.out.println(sky.can);
            for (int i = 0; i < 3 - sky.can; i++) {
                g.drawImage(kalp.getImage(), z, 15, null);
                z = z - 40;
            }
        } else if (my != null) {
            if (my.can == 0) {
                JOptionPane.showMessageDialog(null, "Kaybettiniz");
                frame.dispose();
            }
            for (int i = 0; i < 6 - my.can; i++) {
                if (i % 2 == 0) {
                    g.drawImage(yarimkalp.getImage(), z, 15, null);
                } else {
                    g.drawImage(kalp.getImage(), z, 15, null);
                    z = z - 40;
                }
            }
        }
        int fx = 0, fy = 0, can = 0;
        if (sky == null) {
            my.drawPlayer(g);
            fx = my.x;
            fy = my.y;
        } else {
            sky.drawPlayer(g);
            fx = sky.x;
            fy = sky.y;
        }

        if (Darthvader != null) {
            for (int i = 0; i < Darthvader.size(); i++) {
                if (Darthvader.get(i).x == fx && Darthvader.get(i).y == fy) {
                    Darthvader.get(i).hedefx = fx;
                    Darthvader.get(i).hedefy = fy;
                } else if (Darthvader.get(i).hedefx != fx || Darthvader.get(i).hedefy != fy) {
                    Darthvader.get(i).tur = tur;
                    //Kötü karakterlerin yeni noktaları
                    Darthvader.get(i).x = Darthvader.get(i).yol.rota.get(2);
                    Darthvader.get(i).y = Darthvader.get(i).yol.rota.get(3);
                    //Kötü karakterlerin hedefini değiştirme
                    Darthvader.get(i).hedefx = fx;
                    Darthvader.get(i).hedefy = fy;
                }
                Darthvader.get(i).Enkisayol();
                Darthvader.get(i).drawBadplayer(g);
                Darthvader.get(i).tur = tur;
                System.out.println("Darthvader uzaklık:" + (Darthvader.get(i).yol.rota.size() / 2 - 1));
                if ((Darthvader.get(i).yol.rota.size() / 2 - 1) == 0) {
                    can = -1;
                }
            }
        }
        if (KyloRen != null) {
            for (int i = 0; i < KyloRen.size(); i++) {
                if (KyloRen.get(i).x == fx && KyloRen.get(i).y == fy) {
                    KyloRen.get(i).hedefx = fx;
                    KyloRen.get(i).hedefy = fy;
                } else if (KyloRen.get(i).hedefx != fx || KyloRen.get(i).hedefy != fy) {
                    KyloRen.get(i).tur = tur;
                    //Kötü karakterlerin yeni noktaları
                    KyloRen.get(i).x = KyloRen.get(i).yol.rota.get(4);
                    KyloRen.get(i).y = KyloRen.get(i).yol.rota.get(5);
                    //Kötü karakterlerin hedefini değiştirme
                    KyloRen.get(i).hedefx = fx;
                    KyloRen.get(i).hedefy = fy;
                }
                KyloRen.get(i).Enkisayol();
                KyloRen.get(i).drawBadplayer(g);
                KyloRen.get(i).tur = tur;
                System.out.println("KyloRen uzaklık:" + ((KyloRen.get(i).yol.rota.size() / 2)));
                int n = (KyloRen.get(i).yol.rota.size() / 2-1);
                if ( n == 1) {
                    can = -1;
                }
            }
        }
        if (Stormtrooper != null) {
            for (int i = 0; i < Stormtrooper.size(); i++) {
                if (Stormtrooper.get(i).x == fx && Stormtrooper.get(i).y == fy) {
                    Stormtrooper.get(i).hedefx = fx;
                    Stormtrooper.get(i).hedefy = fy;
                } else if (Stormtrooper.get(i).hedefx != fx || Stormtrooper.get(i).hedefy != fy) {
                    Stormtrooper.get(i).tur = tur;
                    //Kötü karakterlerin yeni noktaları
                    Stormtrooper.get(i).x = Stormtrooper.get(i).yol.rota.get(2);
                    Stormtrooper.get(i).y = Stormtrooper.get(i).yol.rota.get(3);
                    //Kötü karakterlerin hedefini değiştirme
                    Stormtrooper.get(i).hedefx = fx;
                    Stormtrooper.get(i).hedefy = fy;
                }
                Stormtrooper.get(i).Enkisayol();
                Stormtrooper.get(i).drawBadplayer(g);
                Stormtrooper.get(i).tur = tur;
                System.out.println("Stormtrooper uzaklık:" + (Stormtrooper.get(i).yol.rota.size() / 2 - 1));
                if ((Stormtrooper.get(i).yol.rota.size() / 2 - 1) == 0) {
                    can = -1;
                }
            }
        }
        if (can == -1) {
            Stormtrooper.clear();
            Darthvader.clear();
            KyloRen.clear();
            Harita(can);
        }
    }

    public void Harita(int can) {

        if (name.equals("Luke Skywalker")) {
            if (sky.can != 0) {
                sky.x = Bx;
                sky.y = By;
                sky.can += can;
            }
        } else {
            if (my.can != 0) {
                my.x = Bx;
                my.y = By;
                my.can += can;
            }
        }
        int x = 0;
        int y = 0;
        int[] koordinatlar = new int[2];
        for (int i = 0; i < rd.list.size(); i = i + 2) {
            if (rd.list.get(i).equals("Stormtrooper")) {
                koordinatlar = Kapikontrol(rd.list.get(i + 1), koordinatlar);
                Stormtrooper.add(new Stormtrooper(rd.list.get(i + 1), tur, koordinatlar[0], koordinatlar[1], rd.harita, Bx, By));
            } else if (rd.list.get(i).equals("Darthvader")) {
                koordinatlar = Kapikontrol(rd.list.get(i + 1), koordinatlar);
                Darthvader.add(new Darthvader(rd.list.get(i + 1), tur, koordinatlar[0], koordinatlar[1], Bx, By));
            } else if (rd.list.get(i).equals("KyloRen")) {
                koordinatlar = Kapikontrol(rd.list.get(i + 1), koordinatlar);
                KyloRen.add(new KyloRen(rd.list.get(i + 1), tur, koordinatlar[0], koordinatlar[1], rd.harita, Bx, By));
            }
        }
        this.repaint();
    }

    public int[] Kapikontrol(String str, int[] xy) {
        if (str.equals("A")) {
            xy[0] = 5;
            xy[1] = 0;
        } else if (str.equals("B")) {
            xy[0] = 0;
            xy[1] = 4;
        } else if (str.equals("C")) {
            xy[0] = 0;
            xy[1] = 12;
        } else if (str.equals("D")) {
            xy[0] = 5;
            xy[1] = 13;
        } else if (str.equals("D")) {
            xy[0] = 10;
            xy[1] = 4;
        }
        return xy;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int fx, fy, a = 0;
        if (sky == null) {
            fx = my.x;
            fy = my.y;
        } else {
            fx = sky.x;
            fy = sky.y;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            fy++;
            a++;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            fy--;
            a++;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            fx++;
            a++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            fx--;
            a++;
        }
        if (a == 1) {//yön tuşarından başka tuşa basılmasını engeller
            if (fy > 13 && fx == 9) {
                JOptionPane.showMessageDialog(null, "Kazandınız");
                frame.dispose();
            } else if (fy >= 0 && fy <= 13 && fx >= 0 && fx <= 10) {
                if (rd.harita[fx][fy] != 0) {
                    tur++;
                    //Oyuncunun yeni noktaları
                    if (sky == null) {
                        my.tur = tur;
                        my.x = fx;
                        my.y = fy;
                    } else {
                        sky.tur = tur;
                        sky.x = fx;
                        sky.y = fy;
                    }
                    this.repaint();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
