package de.spexmc.mc.navigator.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.navigator.storage.Messages;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Lara on 20.07.2019 for navigator
 */
public final class Messenger {
  private static final Logger logger;

  static {
    logger = Logger.getLogger(Messenger.class.getName());
  }

  public static void administratorMessage(String msg) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.isOp()) {
        sendMessage(player, "&a&l" + msg);
      }
    }

    logger.log(Level.INFO, msg);
  }

  public static void sendMessage(Player player, String msg) {
    player.sendMessage(Messages.PREFIX + msg);
  }

  public static void sendMessage(Player player, String message, String command) {
    final TextComponent text = new TextComponent(message);
    text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
    player.spigot().sendMessage(text);
  }

}
