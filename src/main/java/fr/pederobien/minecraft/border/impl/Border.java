package fr.pederobien.minecraft.border.impl;

import java.time.LocalTime;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraft.border.event.BorderNameChangePostEvent;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.impl.Configurable;
import fr.pederobien.minecraft.platform.interfaces.IConfigurable;
import fr.pederobien.minecraft.platform.interfaces.IPlatformPersistence;
import fr.pederobien.utils.event.EventManager;

public class Border implements IBorder {
	private String name;
	private IConfigurable<World> world;
	private IConfigurable<Block> center;
	private IConfigurable<Integer> initialDiameter, finalDiameter;
	private IConfigurable<Double> speed;
	private IConfigurable<LocalTime> startTime;
	private boolean isMoving;

	/**
	 * Creates a new border configuration.
	 * 
	 * @param name The configuration's name.
	 */
	public Border(String name) {
		this.name = name;

		world = new Configurable<World>("world", WorldManager.OVERWORLD);
		center = new Configurable<Block>("center", WorldManager.getHighestBlockYAt(getWorld().get(), 0, 0));
		initialDiameter = new Configurable<Integer>("initialDiameter", 2000);
		finalDiameter = new Configurable<Integer>("finalDiameter", 30);
		speed = new Configurable<Double>("speed", 1.0);
		startTime = new Configurable<LocalTime>("startTime", LocalTime.of(2, 0, 0));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (this.name.equals(name))
			return;

		String oldName = this.name;
		this.name = name;
		EventManager.callEvent(new BorderNameChangePostEvent(this, oldName));
	}

	@Override
	public IConfigurable<World> getWorld() {
		return world;
	}

	@Override
	public IConfigurable<Block> getCenter() {
		return center;
	}

	@Override
	public IConfigurable<Integer> getInitialDiameter() {
		return initialDiameter;
	}

	@Override
	public IConfigurable<Integer> getFinalDiameter() {
		return finalDiameter;
	}

	@Override
	public IConfigurable<Double> getSpeed() {
		return speed;
	}

	@Override
	public IConfigurable<LocalTime> getStartTime() {
		return startTime;
	}

	@Override
	public LocalTime getMoveTime() {
		return LocalTime.MIN.plusSeconds(Double.valueOf(getDistance() / getSpeed().get()).longValue());
	}

	@Override
	public void setMoveTime(LocalTime moveTime) {
		getSpeed().set(getDistance() / ((Integer) moveTime.toSecondOfDay()).doubleValue());
	}

	@Override
	public boolean isMoving() {
		return isMoving;
	}

	@Override
	public WorldBorder getWorldBorder() {
		return getWorld().get().getWorldBorder();
	}

	@Override
	public boolean reload(IPlatformPersistence<IBorder> persistence) {
		return persistence.deserialize(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof IBorder))
			return false;

		IBorder other = (IBorder) obj;
		return name.equals(other.getName());
	}

	@Override
	public String toString() {
		return name;
	}

	private int getDistance() {
		return Math.abs(getInitialDiameter().get() - getFinalDiameter().get());
	}
}
