package fr.pederobien.minecraft.border.event;

import fr.pederobien.minecraft.border.interfaces.IBorder;

public class BorderEvent extends ProjectBorderEvent {
	private IBorder border;

	/**
	 * Creates a border event
	 * 
	 * @param border The border source involved in this event.
	 */
	public BorderEvent(IBorder border) {
		this.border = border;
	}

	/**
	 * @return The border involved in this event.
	 */
	public IBorder getBorder() {
		return border;
	}
}
