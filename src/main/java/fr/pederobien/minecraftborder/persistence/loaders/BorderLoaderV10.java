package fr.pederobien.minecraftborder.persistence.loaders;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftborder.persistence.BorderXmlTag;
import fr.pederobien.persistence.interfaces.xml.IXmlPersistenceLoader;

public class BorderLoaderV10 extends AbstractBorderLoader {

	public BorderLoaderV10() {
		super(1.0);
	}

	@Override
	public IXmlPersistenceLoader<IBorderConfiguration> load(Element root) {
		createNewElement();

		// Getting configuration's name
		Node name = getElementsByTagName(root, BorderXmlTag.NAME).item(0);
		get().setName(name.getChildNodes().item(0).getNodeValue());

		// Getting world's name
		Node world = getElementsByTagName(root, BorderXmlTag.WORLD).item(0);
		get().setWorld(world.getChildNodes().item(0).getNodeValue());

		// Getting center's coordinates
		Element center = (Element) getElementsByTagName(root, BorderXmlTag.CENTER).item(0);
		get().setBorderCenter(getIntAttribute(center, BorderXmlTag.X_COORDINATE), getIntAttribute(center, BorderXmlTag.Z_COORDINATE));

		// Getting initial diameter
		Node initialDiameter = getElementsByTagName(root, BorderXmlTag.INITIAL_DIAMETER).item(0);
		get().setInitialBorderDiameter(getIntNodeValue(initialDiameter.getChildNodes().item(0)));

		// Getting final diameter
		Node finalDiameter = getElementsByTagName(root, BorderXmlTag.FINAL_DIAMETER).item(0);
		get().setFinalBorderDiameter(getIntNodeValue(finalDiameter.getChildNodes().item(0)));

		// Getting border speed
		Node borderSpeed = getElementsByTagName(root, BorderXmlTag.BORDER_SPEED).item(0);
		get().setBorderSpeed(getDoubleNodeValue(borderSpeed.getChildNodes().item(0)));

		// Getting start time
		Node startTime = getElementsByTagName(root, BorderXmlTag.START_TIME).item(0);
		get().setStartTime(getLocalTimeNodeValue(startTime.getChildNodes().item(0)));
		return this;
	}
}
