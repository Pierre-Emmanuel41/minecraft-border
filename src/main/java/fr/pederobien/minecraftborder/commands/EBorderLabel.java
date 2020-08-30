package fr.pederobien.minecraftborder.commands;

import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public enum EBorderLabel implements ILabel {
	WORLD("world"), CENTER("center"), INITIAL_DIAMETER("initialDiameter"), FINAL_DIAMETER("finalDiameter"), SPEED("speed"), INITIAL_TIME("initialTime"),
	START_TIME("startTime"), MOVE_TIME("moveTime");

	private String label;

	private EBorderLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
