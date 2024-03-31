package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.*;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player tommyVercetti, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGumRepository = tommyVercetti.getGunRepository();
        Deque<Gun> tommyGuns = new ArrayDeque<>(tommyGumRepository.getModels());  //оръжията на Томи
        Deque<Player> players = new ArrayDeque<>(civilPlayers);
        //civil

        Player player = players.poll();
        Gun gun = tommyGuns.poll();

        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }

            // take new gun
            // take a new target

            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = tommyGuns.poll();
            }
        }


        // II phase
        for (Player civilPlayer : civilPlayers) {

            if (civilPlayer.isAlive()) {
                Repository<Gun> civilPlayerRepository = civilPlayer.getGunRepository();
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayerRepository.getModels());

                Gun civilPlayerGun = civilPlayerGuns.poll();
                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && tommyVercetti.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        tommyVercetti.takeLifePoints(shot);
                    }
                    civilPlayerGuns.poll();
                }
            }
        }
    }
}
