package star.wars;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

    ArrayList<String> list = new ArrayList<>();
    int harita[][] = new int[11][14];

    public Read() {
        File file = new File("Harita.txt");
        try {
            int i = 0, j = 0;
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String a = sc.next();
                if (a.equals("0") || a.equals("1")) {
                    harita[i][j] = Integer.parseInt(a);
                    j++;
                    if (j % 14 == 0) {
                        i++;
                        j = 0;
                    }
                } else {
                    list.add(a.substring(a.indexOf(":") + 1, a.indexOf(",")));
                    String str[] = a.split(":");
                    list.add(str[str.length - 1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
