package fr.pederobien.minecraft.border.entries;

import java.time.LocalTime;

import org.bukkit.entity.Player;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.platform.Platform;

public class BorderSizeCountDownEntry extends BorderSizeEntry {
	private boolean isCountDownEquals0;

	/**
	 * Creates an entry in order to display the following border information: "&lt;count down&gt; | &lt;size&gt;".
	 * 
	 * @param score   The line number of this entry in the player objective.
	 * @param border  The border used to get informations to display.
	 * @param pattern A string used to format the world border size.
	 */
	public BorderSizeCountDownEntry(int score, IBorder configuration, String pattern) {
		super(score, configuration, pattern);
		isCountDownEquals0 = false;
	}

	private LocalTime countDown;

	@Override
	protected String updateCurrentValue(Player player) {
		String current = "";
		if (!isCountDownEquals0) {
			int seconds = Platform.get(getObjective().getPlugin()).getTimeLine().getTimeTask().getGameTime().toSecondOfDay();
			countDown = getBorder().getStartTime().minusSeconds(seconds);
			if (!countDown.equals(LocalTime.of(0, 0, 0)))
				current += DisplayHelper.toString(countDown, false) + " | ";
			else
				isCountDownEquals0 = true;
		}
		current += super.updateCurrentValue(player);
		return current;
	}

	@Override
	public BorderSizeCountDownEntry setDisplayHalfSize(boolean displayHalfSize) {
		return (BorderSizeCountDownEntry) super.setDisplayHalfSize(displayHalfSize);
	}
}
