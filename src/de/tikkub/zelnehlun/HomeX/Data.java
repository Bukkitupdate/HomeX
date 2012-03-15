package de.tikkub.zelnehlun.HomeX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Data
{
	public static void setHome(Location loc, String name, String world)
	{
		File f = new File("plugins/HomeX/" + world);
		try
		{
			if (!f.exists()) {
				f.mkdir();
			}

			f = new File("plugins/HomeX/" + world + "/homes.txt");

			if (!f.exists()) {
				f.createNewFile();
			}

			BufferedReader br = new BufferedReader(new FileReader(f));

			String[] save = new String[1000];
			String s = "";
			int j = 0;

			while ((s = br.readLine()) != null) {
				if (!s.split(" ")[0].equalsIgnoreCase(name)) {
					save[j] = s;
					j++;
				}
			}

			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(f));

			String x = " " + String.valueOf(loc.getX());
			String y = " " + String.valueOf(loc.getY());
			String z = " " + String.valueOf(loc.getZ());
			String yaw = " " + String.valueOf(loc.getYaw());
			String pitch = " " + String.valueOf(loc.getPitch());

			bw.write(name + x + y + z + yaw + pitch);
			bw.newLine();

			j = 0;

			while (save[j] != null) {
				bw.write(save[j]);
				bw.newLine();
				j++;
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static boolean homeSet(String name, String world) {
		File f = new File("plugins/HomeX/" + world + "/homes.txt");

		if (f.exists())
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String s = "";

				while ((s = br.readLine()) != null) {
					if (s.split(" ")[0].equalsIgnoreCase(name)) {
						br.close();
						return true;
					}
				}

				br.close();
				return false;
			} catch (Exception e) {
				System.out.println(e);
			}
			else {
				return false;
			}

		return false;
	}

	public static Location getHome(Location loc, String name, String world) {
		File f = new File("plugins/HomeX/" + world + "/homes.txt");
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s = "";

			while ((s = br.readLine()) != null)
				if (s.split(" ")[0].equalsIgnoreCase(name)) {
					Double x = Double.valueOf(s.split(" ")[1]);
					Double y = Double.valueOf(s.split(" ")[2]);
					Double z = Double.valueOf(s.split(" ")[3]);
					Float yaw = Float.valueOf(s.split(" ")[4]);
					Float pitch = Float.valueOf(s.split(" ")[5]);

					loc.setX(x.doubleValue());
					loc.setY(y.doubleValue());
					loc.setZ(z.doubleValue());
					loc.setYaw(yaw.floatValue());
					loc.setPitch(pitch.floatValue());
				}
		}
		catch (Exception e) {
			System.out.println(e);
		}

		return loc;
	}

	public static long getDelay() {
		File f = new File("plugins/HomeX/data.txt");
		String s = "";
		long delay = 0L;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));

			while ((s = br.readLine()) != null) {
				if (s.split("=")[0].equalsIgnoreCase("delay")) {
					delay = Long.valueOf(s.split("=")[1]).longValue();
				}
			}

			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return delay;
	}

	public static boolean isEnabled() {
		File f = new File("plugins/HomeX/data.txt");
		String s = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));

			while ((s = br.readLine()) != null) {
				if (s.split("=")[0].equalsIgnoreCase("enable")) {
					if (s.split("=")[1].equalsIgnoreCase("true")) {
						br.close();
						return true;
					}if (s.split("=")[1].equalsIgnoreCase("false")) {
						br.close();
						return false;
					}
				}
			}

			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return true;
	}

	public static ChatColor getStandardColor() {
		String path = "plugins/OtherX/data.txt";
		File file = new File(path);

		ChatColor chatcolor = ChatColor.WHITE;

		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));

				String s = br.readLine();

				while (!s.split("=")[0].equals("standard-color")) {
					s = br.readLine();
				}

				if (s.split("=")[0].equals("standard-color")) {
					s = s.split("=")[1];

					if (s.equals("AQUA"))
						chatcolor = ChatColor.AQUA;
					else if (s.equals("BLACK"))
						chatcolor = ChatColor.BLACK;
					else if (s.equals("BLUE"))
						chatcolor = ChatColor.BLUE;
					else if (s.equals("DARK_AQUA"))
						chatcolor = ChatColor.DARK_AQUA;
					else if (s.equals("DARK_BLUE"))
						chatcolor = ChatColor.DARK_BLUE;
					else if (s.equals("DARK_GRAY"))
						chatcolor = ChatColor.DARK_GRAY;
					else if (s.equals("DARK_GREEN"))
						chatcolor = ChatColor.DARK_GREEN;
					else if (s.equals("DARK_PURPLE"))
						chatcolor = ChatColor.DARK_PURPLE;
					else if (s.equals("DARK_RED"))
						chatcolor = ChatColor.DARK_RED;
					else if (s.equals("GOLD"))
						chatcolor = ChatColor.GOLD;
					else if (s.equals("GRAY"))
						chatcolor = ChatColor.GRAY;
					else if (s.equals("GREEN"))
						chatcolor = ChatColor.GREEN;
					else if (s.equals("LIGHT_PURPLE"))
						chatcolor = ChatColor.LIGHT_PURPLE;
					else if (s.equals("RED"))
						chatcolor = ChatColor.RED;
					else if (s.equals("WHITE"))
						chatcolor = ChatColor.WHITE;
					else if (s.equals("YELLOW")) {
						chatcolor = ChatColor.YELLOW;
					}
				}

				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return chatcolor;
	}

	public static ChatColor getSpecialColor() {
		String path = "plugins/OtherX/data.txt";
		File file = new File(path);

		ChatColor chatcolor = ChatColor.RED;

		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));

				String s = br.readLine();

				while (!s.split("=")[0].equals("special-color")) {
					s = br.readLine();
				}

				if (s.split("=")[0].equals("special-color")) {
					s = s.split("=")[1];

					if (s.equals("AQUA"))
						chatcolor = ChatColor.AQUA;
					else if (s.equals("BLACK"))
						chatcolor = ChatColor.BLACK;
					else if (s.equals("BLUE"))
						chatcolor = ChatColor.BLUE;
					else if (s.equals("DARK_AQUA"))
						chatcolor = ChatColor.DARK_AQUA;
					else if (s.equals("DARK_BLUE"))
						chatcolor = ChatColor.DARK_BLUE;
					else if (s.equals("DARK_GRAY"))
						chatcolor = ChatColor.DARK_GRAY;
					else if (s.equals("DARK_GREEN"))
						chatcolor = ChatColor.DARK_GREEN;
					else if (s.equals("DARK_PURPLE"))
						chatcolor = ChatColor.DARK_PURPLE;
					else if (s.equals("DARK_RED"))
						chatcolor = ChatColor.DARK_RED;
					else if (s.equals("GOLD"))
						chatcolor = ChatColor.GOLD;
					else if (s.equals("GRAY"))
						chatcolor = ChatColor.GRAY;
					else if (s.equals("GREEN"))
						chatcolor = ChatColor.GREEN;
					else if (s.equals("LIGHT_PURPLE"))
						chatcolor = ChatColor.LIGHT_PURPLE;
					else if (s.equals("RED"))
						chatcolor = ChatColor.RED;
					else if (s.equals("WHITE"))
						chatcolor = ChatColor.WHITE;
					else if (s.equals("YELLOW")) {
						chatcolor = ChatColor.YELLOW;
					}
				}

				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return chatcolor;
	}
}