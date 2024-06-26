package viceCity.models.guns;

public class Pistol extends BaseGun {

    private static final int TOTAL_BULLETS = 100;
    private static final int BULLETS_PER_BARREL = 10;

    private  static  final  int BULLETS_PER_SHOT = 1;




    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0) {
            reload();
        }
        if (getBulletsPerBarrel() > 0){
        this.setBulletsPerBarrel(getBulletsPerBarrel() -BULLETS_PER_SHOT);
        }
            return BULLETS_PER_SHOT;
    }

    private void reload() {
       setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
       setBulletsPerBarrel(BULLETS_PER_BARREL);
    }
}
