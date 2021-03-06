package com.github.maxopoly.SkilUp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import vg.civcraft.mc.civmodcore.ACivMod;

import com.github.maxopoly.SkilUp.commands.CommandHandler;
import com.github.maxopoly.SkilUp.database.DataBaseManager;
import com.github.maxopoly.SkilUp.listeners.logging.LoginLogoutListener;

public class SkilUp extends ACivMod {
	private static SkilUp plugin;
	private static SkilUpManager manager;
	private CommandHandler commandHandler;
	private static DataBaseManager dbm;

	@Override
	public void onEnable() {
		super.onEnable();
		commandHandler = new CommandHandler();
		plugin = this;
		ConfigParser cp = new ConfigParser(this);
		manager = cp.parseConfig();
		dbm = cp.getDBManager();
		plugin.getServer().getPluginManager().registerEvents(new LoginLogoutListener(this), this);
	}

	@Override
	public void onDisable() {

	}

	protected String getPluginName() {
		return "SkilUp";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		return commandHandler.execute(sender, cmd, args);
	}

	public static SkilUpManager getManager() {
		return manager;
	}

	public static SkilUp getPlugin() {
		return plugin;
	}
	
	public static DataBaseManager getDataBaseManager() {
		return dbm;
	}

}
