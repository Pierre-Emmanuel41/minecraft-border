package fr.pederobien.minecraft.border.persistence;

public enum BorderXmlTag {
	NAME("name"), WORLD("world"), CENTER("center"), X_COORDINATE("x"), Z_COORDINATE("z"), INITIAL_DIAMETER("initialdiameter"), FINAL_DIAMETER("finaldiameter"),
	BORDER_SPEED("borderspeed"), START_TIME("starttime");

	private String name;

	private BorderXmlTag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
