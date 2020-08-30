package fr.pederobien.minecraftborder.entries;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.utils.Plateform;
import fr.pederobien.minecraftmanagers.WorldManager;

public class WorldBorderCountDown extends BorderConfigurationEntry {

	public WorldBorderCountDown(int score, IBorderConfiguration configuration) {
		super(score, configuration);
	}

	@Override
	public String getBefore() {
		return WorldManager.getWorldNameNormalised(getConfiguration().getWorld()) + " : ";
	}

	@Override
	protected String updateCurrentValue(Player arg0) {
		return DisplayHelper.toString(getConfiguration().getStartTime().minusSeconds(Plateform.getTimeTask().getGameTime().toSecondOfDay()), false);
	}
}
