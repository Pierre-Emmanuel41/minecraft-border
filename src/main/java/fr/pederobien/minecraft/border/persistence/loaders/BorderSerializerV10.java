package fr.pederobien.minecraft.border.persistence.loaders;

import org.w3c.dom.Element;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.persistence.BorderXmlTag;

public class BorderSerializerV10 extends AbstractBorderSerializer {

	public BorderSerializerV10() {
		super(1.0);
	}

	@Override
	public boolean deserialize(IBorder element, Element root) {
		// Getting configuration's name
		setName(element, root);

		// Getting world's name
		setWorld(element, root);

		// Getting center's coordinates
		setCenter(element, root);

		// Getting initial diameter
		setInitialDiameter(element, root);

		// Getting final diameter
		setFinalDiameter(element, root);

		// Getting border speed
		setSpeed(element, root);

		// Getting start time
		setStartTime(element, root);
		return true;
	}

	@Override
	public boolean serialize(IBorder element, Element root) {
		Element name = createElement(BorderXmlTag.NAME);
		name.appendChild(createTextNode(element.getName()));
		root.appendChild(name);

		Element world = createElement(BorderXmlTag.WORLD);
		world.appendChild(createTextNode(element.getWorld().getName()));
		root.appendChild(world);

		Element center = createElement(BorderXmlTag.CENTER);
		setAttribute(center, BorderXmlTag.X_COORDINATE, element.getCenter().getX());
		setAttribute(center, BorderXmlTag.Y_COORDINATE, element.getCenter().getY());
		setAttribute(center, BorderXmlTag.Z_COORDINATE, element.getCenter().getZ());
		root.appendChild(center);

		Element initialDiameter = createElement(BorderXmlTag.INITIAL_DIAMETER);
		initialDiameter.appendChild(createTextNode(element.getInitialDiameter().toString()));
		root.appendChild(initialDiameter);

		Element finalDiameter = createElement(BorderXmlTag.FINAL_DIAMETER);
		finalDiameter.appendChild(createTextNode(element.getFinalDiameter().toString()));
		root.appendChild(finalDiameter);

		Element speed = createElement(BorderXmlTag.BORDER_SPEED);
		speed.appendChild(createTextNode(element.getSpeed().toString()));
		root.appendChild(speed);

		Element startTime = createElement(BorderXmlTag.START_TIME);
		startTime.appendChild(createTextNode(element.getStartTime().toString()));
		root.appendChild(startTime);
		return true;
	}
}
