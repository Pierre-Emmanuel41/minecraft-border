package fr.pederobien.minecraft.border.entries;

import java.time.LocalTime;

import org.bukkit.entity.Player;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.platform.Platform;
import fr.pederobien.utils.event.IEventListener;

public class BorderSizeCountDownEntry extends BorderSizeEntry implements IEventListener {

	/**
	 * Creates an entry in order to display the following border information: "&lt;count down&gt; | &lt;size&gt;".
	 * 
	 * @param score   The line number of this entry in the player objective.
	 * @param border  The border used to get informations to display.
	 * @param pattern A string used to format the world border size.
	 */
	public BorderSizeCountDownEntry(int score, IBorder configuration, String pattern) {
		super(score, configuration, pattern);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		Platform platform = Platform.get(getObjective().getPlugin());
		if (platform == null)
			return "";

		LocalTime gameTime = platform.getTimeLine().getTimeTask().getGameTime();
		switch (gameTime.compareTo(getBorder().getStartTime().get())) {
		case -1:
			LocalTime countDown = getBorder().getStartTime().get().minusSeconds(gameTime.toSecondOfDay());
			return String.format("%s | %s", DisplayHelper.toString(countDown, false), super.updateCurrentValue(player));
		default:
			return super.updateCurrentValue(player);
		}
	}

	@Override
	public BorderSizeCountDownEntry setDisplayHalfSize(boolean displayHalfSize) {
		return (BorderSizeCountDownEntry) super.setDisplayHalfSize(displayHalfSize);
	}
}
