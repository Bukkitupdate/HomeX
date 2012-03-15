package de.tikkub.zelnehlun.HomeX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HomeX extends JavaPlugin
{
	private static final Logger log = Logger.getLogger("Minecraft");
	Data data = new Data();

	ChatColor standard = ChatColor.WHITE;
	ChatColor special = ChatColor.RED;

	long delay = 0L;

	boolean enabled = true;

	public void onDisable()
	{
		log.info("HomeX disabled");
	}

	public void onEnable()
	{
		log.info("HomeX enabled");

		if (!new File("plugins/HomeX").exists()) {
			log.info("HomeX directory does not exist. Creating new folder...");
			new File("plugins/HomeX").mkdir();
			log.info("HomeX direcotry created.");
		}

		if (!new File("plugins/HomeX/data.txt").exists()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("plugins/HomeX/data.txt")));

				bw.write("delay=0");
				bw.newLine();
				bw.write("enable=true");

				bw.flush();
				bw.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		this.standard = Data.getStandardColor();
		this.special = Data.getSpecialColor();

		this.delay = (Data.getDelay() * 20L);
		this.enabled = Data.isEnabled();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if ((sender instanceof Player)) {
			Player player = (Player)sender;
			String name = player.getName();
			String world = player.getWorld().getName();
			Location loc = player.getLocation();

			if (cmd.getName().equalsIgnoreCase("sethome")) {
				if (player.isOp() || player.hasPermission("HomeX.sethome")) {
					Data.setHome(loc, name, world);

					player.sendMessage(this.standard + "Your " + this.special + "home" + this.standard + " has been set.");
				} else {
					player.sendMessage(this.standard + "You do not have access to this command.");
				}
			}

			if (cmd.getName().equalsIgnoreCase("home")) {
				if (player.isOp() || player.hasPermission("HomeX.home")) {
					if (Data.homeSet(name, world)) {
						if (this.delay == 0L) {
							loc = Data.getHome(loc, name, world);

							player.teleport(loc);
							player.sendMessage(this.standard + "You have been teleported to your " + this.special + "home" + this.standard + ".");
						} else {
							Player fPlayer = player;
							Location fLoc = Data.getHome(loc, name, world);
							double x1 = Math.round(fPlayer.getLocation().getX());
							double y1 = Math.round(fPlayer.getLocation().getY());
							double z1 = Math.round(fPlayer.getLocation().getZ());

							player.sendMessage(this.standard + "You will be teleported in " + this.special + this.delay / 20L + this.standard + " seconds.");
							player.sendMessage(this.standard + "Do not" + this.special + " move" + this.standard + ".");

							getServer().getScheduler().scheduleSyncDelayedTask(this, new Teleporter(fPlayer, x1, y1, z1, fLoc, this), this.delay);
						}
					}
					else
						player.sendMessage(this.standard + "You have to set a " + this.special + "home" + this.standard + " first.");
				}
				else {
					player.sendMessage(this.standard + "You cannot access this command.");
				}
			}
		}

		return true;
	}
}