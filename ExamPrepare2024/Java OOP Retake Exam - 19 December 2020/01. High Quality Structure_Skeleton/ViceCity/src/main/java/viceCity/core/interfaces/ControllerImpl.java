package viceCity.core.interfaces;

import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private static final int TOMY_MAX_HEALTH = 100;
    private static final int    CIVIL_MAX_HEALTH = 50;
    //
    private Player tommyVercetti;
    private Map<String, Player> civilPlayer;
    private Deque<Gun> guns;
    private Neighbourhood neighbourhood;


    //constructor
    public ControllerImpl() {
        tommyVercetti = new MainPlayer();
        civilPlayer = new LinkedHashMap<>();
        guns = new ArrayDeque<>();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player playerToAdd = new CivilPlayer(name);
        civilPlayer.put(name, playerToAdd);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Rifle":
                gun = new Rifle(name);
                break;
            case "Pistol":
                gun = new Pistol(name);
                break;
            default:
                return GUN_TYPE_INVALID;
        }
        guns.offer(gun);

        return String.format(GUN_ADDED,name,type);
    }

    @Override
    public String addGunToPlayer(String playerName) {

        Gun gunToAdd = guns.poll();

        if (gunToAdd == null) {
            return GUN_QUEUE_IS_EMPTY;
        }
        if (playerName.equals("Vercetti")) {
            tommyVercetti.getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gunToAdd.getName(), tommyVercetti.getName());
        }

        Player civilPlay = civilPlayer.get(playerName);
        if (civilPlay == null) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        civilPlay.getGunRepository().add(gunToAdd);

        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gunToAdd.getName(), playerName);
    }

    @Override
    public String fight() {
        neighbourhood.action(tommyVercetti, civilPlayer.values());

        if (tommyVercetti.getLifePoints() == TOMY_MAX_HEALTH &&  //Точките на томи
                civilPlayer.values().stream().allMatch(player -> player.getLifePoints() == CIVIL_MAX_HEALTH)) {   // точките на цивила == 50
            return FIGHT_HOT_HAPPENED;

        }
        List<Player> deadPlayer = civilPlayer.values()
                .stream()
                .filter(player -> !player.isAlive())
                .collect(Collectors.toList());
        //          for (int i = civilPlayers.size() - 1; i >= 0; i--) {
        //            if (!civilPlayers.get(i).isAlive()) {
        //                civilPlayers.remove(i);
        //                deadCivilPlayers++;
        //            }

        StringBuilder builder = new StringBuilder(FIGHT_HAPPENED)
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, tommyVercetti.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayer.size()))
                .append(System.lineSeparator())
                .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayer.size() - deadPlayer.size()));

        for (Player dead : deadPlayer) {
            civilPlayer.remove(dead.getName());
        }

        return builder.toString().trim();
    }
}
