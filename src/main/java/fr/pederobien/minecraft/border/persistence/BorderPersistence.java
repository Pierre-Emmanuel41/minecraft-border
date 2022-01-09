package fr.pederobien.minecraft.border.persistence;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import fr.pederobien.minecraft.border.impl.Border;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.persistence.loaders.BorderSerializerV10;
import fr.pederobien.minecraft.platform.impl.PlatformPersistence;
import fr.pederobien.minecraft.platform.interfaces.IPlatformPersistence;
import fr.pederobien.persistence.impl.Persistences;
import fr.pederobien.persistence.impl.xml.XmlPersistence;

public class BorderPersistence {
	private static final Path BORDER = Paths.get("Border");
	private PlatformPersistence<IBorder> persistence;

	public BorderPersistence() {
		XmlPersistence<IBorder> xmlPersistence = Persistences.xmlPersistence();
		xmlPersistence.register(xmlPersistence.adapt(new BorderSerializerV10()));

		// Action to save default border
		Consumer<IBorder> writeDefault = border -> {
			// Do nothing
		};
		persistence = new PlatformPersistence<IBorder>(BORDER, name -> new Border(name), xmlPersistence, writeDefault);
	}

	/**
	 * @return The platform persistence.
	 */
	public IPlatformPersistence<IBorder> getPersistence() {
		return persistence;
	}
}
