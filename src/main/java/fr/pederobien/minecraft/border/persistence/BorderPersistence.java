package fr.pederobien.minecraft.border.persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.pederobien.minecraft.border.impl.BorderConfiguration;
import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraft.border.persistence.loaders.BorderLoaderV10;
import fr.pederobien.minecraftgameplateform.impl.element.persistence.AbstractMinecraftPersistence;
import fr.pederobien.minecraftgameplateform.interfaces.element.persistence.IMinecraftPersistence;
import fr.pederobien.minecraftgameplateform.utils.Plateform;

public class BorderPersistence extends AbstractMinecraftPersistence<IBorderConfiguration> {
	private static final String ROOT_XML_DOCUMENT = "border";

	private BorderPersistence() {
		super(Plateform.ROOT.resolve("Borders"), "DefaultBorder");
		register(new BorderLoaderV10());
	}

	public static IMinecraftPersistence<IBorderConfiguration> getInstance() {
		return new BorderPersistence();
	}

	@Override
	public void saveDefault() {
		set(new BorderConfiguration(getDefault()));
		save();
	}

	@Override
	public boolean save() {
		if (get() == null)
			return false;
		Document doc = newDocument();
		doc.setXmlStandalone(true);

		Element root = createElement(doc, ROOT_XML_DOCUMENT);
		doc.appendChild(root);

		Element version = createElement(doc, VERSION);
		version.appendChild(doc.createTextNode(getVersion().toString()));
		root.appendChild(version);

		Element name = createElement(doc, BorderXmlTag.NAME);
		name.appendChild(doc.createTextNode(get().getName()));
		root.appendChild(name);

		Element world = createElement(doc, BorderXmlTag.WORLD);
		world.appendChild(doc.createTextNode(get().getWorld().getName()));
		root.appendChild(world);

		Element center = createElement(doc, BorderXmlTag.CENTER);
		setAttribute(center, BorderXmlTag.X_COORDINATE, get().getBorderCenter().getX());
		setAttribute(center, BorderXmlTag.Z_COORDINATE, get().getBorderCenter().getZ());
		root.appendChild(center);

		Element initialDiameter = createElement(doc, BorderXmlTag.INITIAL_DIAMETER);
		initialDiameter.appendChild(doc.createTextNode(get().getInitialBorderDiameter().toString()));
		root.appendChild(initialDiameter);

		Element finalDiameter = createElement(doc, BorderXmlTag.FINAL_DIAMETER);
		finalDiameter.appendChild(doc.createTextNode(get().getFinalBorderDiameter().toString()));
		root.appendChild(finalDiameter);

		Element borderSpeed = createElement(doc, BorderXmlTag.BORDER_SPEED);
		borderSpeed.appendChild(doc.createTextNode(get().getBorderSpeed().toString()));
		root.appendChild(borderSpeed);

		Element startTime = createElement(doc, BorderXmlTag.START_TIME);
		startTime.appendChild(doc.createTextNode(get().getStartTime().toString()));
		root.appendChild(startTime);

		saveDocument(doc, get().getName());
		return true;
	}
}
