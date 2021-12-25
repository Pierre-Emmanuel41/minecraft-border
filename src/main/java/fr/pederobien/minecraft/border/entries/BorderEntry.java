package fr.pederobien.minecraft.border.entries;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.scoreboards.impl.CodeEntry;

public abstract class BorderEntry extends CodeEntry {
	private IBorder border;

	/**
	 * Create an entry to display informations about the given border.
	 * 
	 * @param score  The line number of this entry.
	 * @param border The border used to get informations to display.
	 */
	protected BorderEntry(int score, IBorder border) {
		super(score);
		this.border = border;
	}

	/**
	 * @return The border associated to this entry.
	 */
	public IBorder getBorder() {
		return border;
	}
}
