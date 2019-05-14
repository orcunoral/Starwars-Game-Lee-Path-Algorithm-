package star.wars;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Dugum {

    int x, y, dist;
    Dugum sol, sag, yukari, asagi, onceki;

    Dugum(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        sol = sag = yukari = asagi = onceki = null;
    }
};

public class Enkisayol {
    // M x N matrix

    private static final int M = 11;
    private static final int N = 14;

    private static final int satir[] = {-1, 0, 0, 1};
    private static final int sutun[] = {0, -1, 1, 0};
    private static Dugum agac;
    ArrayList<Integer> rota = new ArrayList<>();
    int Z = 0;

    private static boolean kontrol(int harita[][], boolean visited[][], int sat, int sut) {
        return (sat >= 0) && (sat < M) && (sut >= 0) && (sut < N) && harita[sat][sut] == 1 && !visited[sat][sut];
    }

    public void Enkisamesafe(int harita[][], int x, int y, int hedefx, int hedefy) {
        boolean[][] visited = new boolean[M][N];
        Queue<Dugum> kuyruk = new ArrayDeque<>();
        visited[x][y] = true;
        kuyruk.add(new Dugum(x, y, 0));
        int min_mesafe = Integer.MAX_VALUE;
        agac = new Dugum(x, y, 0);
        while (!kuyruk.isEmpty()) {
            int z = 0;
            Dugum node = kuyruk.poll();
            x = node.x;
            y = node.y;
            int dist = node.dist;
            if (x == hedefx && y == hedefy) {// Hedefe varıldıysa bitir
                min_mesafe = dist;
                break;
            }
            for (int k = 0; k < 4; k++) {
                if (kontrol(harita, visited, (x + satir[k]), (y + sutun[k]))) {
                    // Noktanın 4 tarafını kontrol et 1 olan  varsa gir
                    Find(agac, x, y, dist + 1, (x + satir[k]), (y + sutun[k]));
                    visited[x + satir[k]][y + sutun[k]] = true;
                    kuyruk.add(new Dugum(x + satir[k], y + sutun[k], dist + 1));
                }
            }
        }
        if (min_mesafe == Integer.MAX_VALUE) {
            System.out.println("Uzaklık bulunamadı");
        }
        Traversal(agac, hedefx, hedefy);
    }

    // Eklenmesi istenen  değerlerin bağlı  olduğu düğümü  bulur
    public void Find(Dugum agac, int x, int y, int dist, int eklenecekx, int ekleneceky) {
        if (agac != null) {
            if (agac.x == x && agac.y == y) {
                Insert(agac, eklenecekx, ekleneceky, dist);
            } else {
                Find(agac.yukari, x, y, dist, eklenecekx, ekleneceky);
                Find(agac.asagi, x, y, dist, eklenecekx, ekleneceky);
                Find(agac.sag, x, y, dist, eklenecekx, ekleneceky);
                Find(agac.sol, x, y, dist, eklenecekx, ekleneceky);
            }
        } else if (agac == null) {
            return;
        }
    }

    //Değerleri ağaca ekler
    public void Insert(Dugum agac, int x, int y, int dist) {
        if (agac.x >= x && agac.y == y) {
            if (agac.yukari == null) {
                agac.yukari = new Dugum(x, y, dist);
                agac.yukari.onceki = agac;
            } else {
                Insert(agac.yukari, x, y, dist);
            }
        } else if (agac.x <= x && agac.y == y) {
            if (agac.asagi == null) {
                agac.asagi = new Dugum(x, y, dist);
                agac.asagi.onceki = agac;
            } else {
                Insert(agac.asagi, x, y, dist);
            }
        } else if (agac.x == x && agac.y <= y) {
            if (agac.sol == null) {
                agac.sol = new Dugum(x, y, dist);
                agac.sol.onceki = agac;
            } else {
                Insert(agac.sol, x, y, dist);
            }
        } else if (agac.x == x && agac.y >= y) {
            if (agac.sag == null) {
                agac.sag = new Dugum(x, y, dist);
                agac.sag.onceki = agac;
            } else {
                Insert(agac.sag, x, y, dist);
            }
        }
    }

    //Ağaçta gezinme
    public void Traversal(Dugum agac, int x, int y) {
        if (agac != null) {
            if (agac.x == x && agac.y == y) {
                Print(agac);
            }
        } else if (agac == null) {
            return;
        }

        Traversal(agac.yukari, x, y);
        Traversal(agac.asagi, x, y);
        Traversal(agac.sag, x, y);
        Traversal(agac.sol, x, y);

    }

    //Hedefe giden yolu yazdırır.
    public void Print(Dugum agac) {
        if (agac == null) {
            return;
        }
        Print(agac.onceki);
        rota.add(agac.x);
        rota.add(agac.y);
    }
}