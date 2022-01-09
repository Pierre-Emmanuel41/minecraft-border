package fr.pederobien.minecraft.border.event;

import fr.pederobien.minecraft.border.interfaces.IBorderList;

public class BorderListEvent extends ProjectBorderEvent {
	private IBorderList list;

	/**
	 * Creates a border list event.
	 * 
	 * @param list The list source involved in this event.
	 */
	public BorderListEvent(IBorderList list) {
		this.list = list;
	}

	/**
	 * @return The list involved in this event.
	 */
	public IBorderList getList() {
		return list;
	}
}
