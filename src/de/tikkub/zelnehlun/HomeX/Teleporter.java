package de.tikkub.zelnehlun.HomeX;

import de.tikkub.zelnehlun.HomeX.HomeX;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleporter implements Runnable{
	Player fPlayer;
	double x1, y1, z1;
	Location fLoc;
	HomeX plugin;

	public Teleporter(Player player, double x1, double y1, double z1, Location loc, HomeX plugin){
		this.fPlayer = player;

		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;

		this.fLoc = loc;

		this.plugin = plugin;
	}

	public void run() {
		double x2 = Math.round(this.fPlayer.getLocation().getX());
		double y2 = Math.round(this.fPlayer.getLocation().getY());
		double z2 = Math.round(this.fPlayer.getLocation().getZ());

		if ((this.x1 == x2) && (this.y1 == y2) && (this.z1 == z2)) {
			this.fPlayer.teleport(this.fLoc);
			this.fPlayer.sendMessage(plugin.standard + "You have been teleported to your " + plugin.special + "home" + plugin.standard + ".");
		} else {
			this.fPlayer.sendMessage(plugin.standard + "You have not been" + plugin.special + " teleported " + plugin.standard + "because you moved.");
		}
	}
}
