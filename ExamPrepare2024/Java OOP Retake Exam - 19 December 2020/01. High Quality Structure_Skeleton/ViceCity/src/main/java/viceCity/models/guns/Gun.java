package viceCity.models.guns;

public interface Gun {
    String getName();

    int getBulletsPerBarrel();   // куршуми на барел

    boolean canFire();

    int getTotalBullets();

    int fire();
}
