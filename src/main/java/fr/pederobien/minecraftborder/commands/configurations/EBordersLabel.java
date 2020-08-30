package fr.pederobien.minecraftborder.commands.configurations;

import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public enum EBordersLabel implements ILabel {
	BORDERS("borders"), ADD("add"), REMOVE("remove"), LIST("list"), DETAILS("details"), RELOAD("reload");

	private String label;

	private EBordersLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
