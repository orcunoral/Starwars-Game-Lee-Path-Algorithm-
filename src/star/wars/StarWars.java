package star.wars;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class StarWars extends JPanel {

    static Read rd = new Read();
    private JFrame frame = new JFrame();
    static String str;

    public StarWars() {
        frame.setSize(200, 120);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 120);

        String[] s = {"Luke Skywalker", "Master Yoda"};
        JComboBox cmbox = new JComboBox(s);
        cmbox.setSize(50, 100);
        JButton btn = new JButton("Başla");
        btn.setSize(50, 100);
        this.add(cmbox);
        this.add(btn);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                str = cmbox.getSelectedItem().toString();
                if (str != null) {
                    System.out.println("Karakter:" + str);
                    for (int i = 0; i < rd.harita.length; i++) {
                        for (int j = 0; j < rd.harita[0].length; j++) {
                            System.out.print(rd.harita[i][j] + " ");
                        }
                        System.out.println();
                    }
                    frame.dispose();
                    Anapencere arayüz = new Anapencere(str);
                }
            }
        });
        frame.add(this);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        StarWars giris = new StarWars();
    }

}
