package fr.pederobien.minecraft.border.impl;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.platform.exceptions.PlatformException;

public class BorderAlreadyRegisteredException extends PlatformException {
	private static final long serialVersionUID = 1L;
	private IBorderList list;
	private IBorder border;

	public BorderAlreadyRegisteredException(IBorderList list, IBorder border) {
		super(String.format("The configuration %s is already registered for %s", list.getName(), border.getName()));
		this.list = list;
		this.border = border;
	}

	/**
	 * @return The list involved in this exception.
	 */
	public IBorderList getList() {
		return list;
	}

	/**
	 * @return The already registered border.
	 */
	public IBorder getBorder() {
		return border;
	}
}
