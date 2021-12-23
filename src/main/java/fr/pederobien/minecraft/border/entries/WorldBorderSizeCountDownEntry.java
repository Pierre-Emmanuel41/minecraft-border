package fr.pederobien.minecraft.border.entries;

import java.time.LocalTime;

import org.bukkit.entity.Player;

import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.utils.Plateform;

public class WorldBorderSizeCountDownEntry extends WorldBorderSizeEntry {
	private boolean isCountDownEquals0;

	public WorldBorderSizeCountDownEntry(int score, IBorderConfiguration configuration, String pattern) {
		super(score, configuration, pattern);
		isCountDownEquals0 = false;
	}

	private LocalTime countDown;

	@Override
	protected String updateCurrentValue(Player player) {
		String current = "";
		if (!isCountDownEquals0) {
			countDown = getConfiguration().getStartTime().minusSeconds(Plateform.getTimeTask().getGameTime().toSecondOfDay());
			if (!countDown.equals(LocalTime.of(0, 0, 0)))
				current += DisplayHelper.toString(countDown, false) + " | ";
			else
				isCountDownEquals0 = true;
		}
		current += super.updateCurrentValue(player);
		return current;
	}

	@Override
	public WorldBorderSizeCountDownEntry setDisplayHalfSize(boolean displayHalfSize) {
		return (WorldBorderSizeCountDownEntry) super.setDisplayHalfSize(displayHalfSize);
	}
}
