package star.wars;

public class Karakter extends Lokasyon {

    String ad;
    int tur;
    int Mx = 100;
    int My = 50;
    int katsayi = 32;

    public Karakter(String ad, int tur, int x, int y) {
        super(x, y);
        setAd(ad);
        setTur(tur);
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getTur() {
        return tur;
    }

    public void setTur(int tur) {
        this.tur = tur;
    }
}
