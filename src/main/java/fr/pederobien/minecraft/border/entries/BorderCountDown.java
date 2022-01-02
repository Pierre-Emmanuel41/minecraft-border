package fr.pederobien.minecraft.border.entries;

import org.bukkit.entity.Player;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.Platform;

public class BorderCountDown extends BorderEntry {

	/**
	 * Creates an entry in order to display the count down before moving the border.
	 * 
	 * @param score  The line number of this entry.
	 * @param border The border used to get informations to display.
	 */
	public BorderCountDown(int score, IBorder border) {
		super(score, border);
	}

	@Override
	public String getBefore() {
		return WorldManager.getWorldNameNormalised(getBorder().getWorld().get()) + " : ";
	}

	@Override
	protected String updateCurrentValue(Player player) {
		int seconds = Platform.get(getObjective().getPlugin()).getTimeLine().getTimeTask().getGameTime().toSecondOfDay();
		return DisplayHelper.toString(getBorder().getStartTime().get().minusSeconds(seconds), false);
	}
}
