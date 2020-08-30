package fr.pederobien.minecraftborder.persistence.loaders;

import fr.pederobien.minecraftborder.impl.BorderConfiguration;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.persistence.impl.xml.AbstractXmlPersistenceLoader;

public abstract class AbstractBorderLoader extends AbstractXmlPersistenceLoader<IBorderConfiguration> {

	protected AbstractBorderLoader(Double version) {
		super(version);
	}

	@Override
	protected IBorderConfiguration create() {
		return new BorderConfiguration("DefaultBorder");
	}
}
