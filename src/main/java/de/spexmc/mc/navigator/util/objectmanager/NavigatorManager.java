package de.spexmc.mc.navigator.util.objectmanager;

import java.util.List;

import de.spexmc.mc.navigator.model.WaypointModel;
import de.spexmc.mc.navigator.storage.Const;
import de.spexmc.mc.navigator.storage.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by Lara on 29.07.2019 for navigator
 */
public final class NavigatorManager {

  public static WaypointModel determineWaypoint(final String name) {
    final List<WaypointModel> waypoints = Data.getInstance().getWaypoints();
    return waypoints.stream().filter(waypoint -> waypoint.getName().equalsIgnoreCase(name))
        .findAny()
        .orElse(null);
  }

  public static WaypointModel determineNearestWaypoint(Player player) {
    final double minDistance = Double.MAX_VALUE;
    WaypointModel shortest = null;
    for (WaypointModel waypoint : Data.getInstance().getWaypoints()) {
      final Location location = waypoint.getLocation();
      final double distance = location.distance(player.getLocation());
      if (distance < minDistance) {
        shortest = waypoint;
      }
    }
    return shortest;
  }

  public static Inventory determineInventory() {
    final List<WaypointModel> waypoints = Data.getInstance().getWaypoints();
    final int size = waypoints.size() / 9 * 9 + 9;
    final Inventory inventory = Bukkit.createInventory(null, size, Const.NAVIGATOR_TITLE);
    waypoints.stream().map(WaypointModel::getItem).forEach(inventory::addItem);
    return inventory;
  }

}