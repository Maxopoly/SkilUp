package com.github.maxopoly.SkilUp.rewards;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.SkilUp.SkilUp;
import com.github.maxopoly.SkilUp.skills.Skill;

public class DirectItemReward extends AbstractReward {
	private ItemStack is;

	public DirectItemReward(Skill skill, int requiredLevel,	double chance, String info, ItemStack itemRepresentation,
			String name, ItemStack drop) {
		super(skill, requiredLevel, chance, info,
				itemRepresentation, name);
		this.is = drop;
	}

	/**
	 * Gives the item to the player given as first argument and drops it at his
	 * location if it cant be given
	 */
	public void applyEffect(Object... data) {
		Player p = (Player) data[0];
		if (rollForApplying()) {
			SkilUp.getPlugin().debug(
					name + " gave " + p.getUniqueId() + " " + is.toString());
			HashMap<Integer, ItemStack> notGiven = p.getInventory().addItem(
					is.clone());
			for (ItemStack i : notGiven.values()) {
				p.getLocation().getWorld()
						.dropItemNaturally(p.getLocation(), i);
			}
		}
	}

	/**
	 * @return Which item stack is given by this instance
	 */
	public ItemStack getDrop() {
		return is;
	}
	
	public void listenerTriggered(Event e, Player p) {
		
	}
}
