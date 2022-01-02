package fr.pederobien.minecraft.border.persistence.loaders;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.persistence.BorderXmlTag;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.persistence.impl.xml.AbstractXmlSerializer;

public abstract class AbstractBorderSerializer extends AbstractXmlSerializer<IBorder> {

	protected AbstractBorderSerializer(Double version) {
		super(version);
	}

	/**
	 * Set the configuration's name.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setName(IBorder element, Element root) {
		Node name = getElementsByTagName(root, BorderXmlTag.NAME).item(0);
		element.setName(name.getChildNodes().item(0).getNodeValue());
	}

	/**
	 * Set the configuration's world.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setWorld(IBorder element, Element root) {
		Node world = getElementsByTagName(root, BorderXmlTag.WORLD).item(0);
		element.getWorld().set(WorldManager.getWorld(world.getChildNodes().item(0).getNodeValue()));
	}

	/**
	 * Set the configuration's center.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setCenter(IBorder element, Element root) {
		Element center = (Element) getElementsByTagName(root, BorderXmlTag.CENTER).item(0);
		int x = getIntAttribute(center, BorderXmlTag.X_COORDINATE);
		int y = getIntAttribute(center, BorderXmlTag.Y_COORDINATE);
		int z = getIntAttribute(center, BorderXmlTag.Z_COORDINATE);
		element.getCenter().set(WorldManager.getBlockAt(element.getWorld().get(), x, y, z));
	}

	/**
	 * Set the configuration's initial diameter.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setInitialDiameter(IBorder element, Element root) {
		Node initialDiameter = getElementsByTagName(root, BorderXmlTag.INITIAL_DIAMETER).item(0);
		element.getInitialDiameter().set(getIntNodeValue(initialDiameter.getChildNodes().item(0)));
	}

	/**
	 * Set the configuration's final diameter.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setFinalDiameter(IBorder element, Element root) {
		Node finalDiameter = getElementsByTagName(root, BorderXmlTag.FINAL_DIAMETER).item(0);
		element.getFinalDiameter().set(getIntNodeValue(finalDiameter.getChildNodes().item(0)));
	}

	/**
	 * Set the configuration's speed.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setSpeed(IBorder element, Element root) {
		Node speed = getElementsByTagName(root, BorderXmlTag.BORDER_SPEED).item(0);
		element.getSpeed().set(getDoubleNodeValue(speed.getChildNodes().item(0)));
	}

	/**
	 * Set the configuration's start time.
	 * 
	 * @param element The element to update.
	 * @param root    The node that correspond to the root of an XML document.
	 */
	public void setStartTime(IBorder element, Element root) {
		Node startTime = getElementsByTagName(root, BorderXmlTag.START_TIME).item(0);
		element.getStartTime().set(getLocalTimeNodeValue(startTime.getChildNodes().item(0)));
	}
}
