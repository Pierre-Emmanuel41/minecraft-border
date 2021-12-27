package fr.pederobien.minecraft.border.persistence;

import java.nio.file.Paths;

import fr.pederobien.minecraft.border.impl.Border;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.persistence.loaders.BorderSerializerV10;
import fr.pederobien.minecraft.platform.impl.PlatformPersistence;
import fr.pederobien.persistence.impl.Persistences;
import fr.pederobien.persistence.impl.xml.XmlPersistence;

public class BorderPersistence {
	private PlatformPersistence<IBorder> persistence;

	public BorderPersistence() {
		XmlPersistence<IBorder> xmlPersistence = Persistences.xmlPersistence();
		xmlPersistence.register(xmlPersistence.adapt(new BorderSerializerV10()));
		persistence = new PlatformPersistence<IBorder>(Paths.get("Border"), name -> new Border(name), xmlPersistence);
	}

	/**
	 * @return The platform persistence.
	 */
	public PlatformPersistence<IBorder> getPersistence() {
		return persistence;
	}
}
