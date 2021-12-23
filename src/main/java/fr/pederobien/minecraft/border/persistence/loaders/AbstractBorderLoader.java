package fr.pederobien.minecraft.border.persistence.loaders;

import fr.pederobien.minecraft.border.impl.BorderConfiguration;
import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
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
