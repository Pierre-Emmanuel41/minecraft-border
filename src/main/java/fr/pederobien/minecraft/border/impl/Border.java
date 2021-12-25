package fr.pederobien.minecraft.border.impl;

import java.time.LocalTime;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraft.border.event.BorderCenterChangePostEvent;
import fr.pederobien.minecraft.border.event.BorderFinalDiameterChangePostEvent;
import fr.pederobien.minecraft.border.event.BorderInitialDiameterChangePostEvent;
import fr.pederobien.minecraft.border.event.BorderNameChangePostEvent;
import fr.pederobien.minecraft.border.event.BorderSpeedChangePostEvent;
import fr.pederobien.minecraft.border.event.BorderStartTimeChangePostEvent;
import fr.pederobien.minecraft.border.event.BorderWorldChangePostEvent;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.persistence.BorderPersistence;
import fr.pederobien.minecraft.game.platform.impl.PlatformPersistence;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.utils.event.EventManager;

public class Border implements IBorder {
	private static final World DEFAULT_WORLD = WorldManager.OVERWORLD;
	private static final Integer DEFAULT_INITIAL_DIAMETER = 2000;
	private static final Integer DEFAULT_FINAL_DIAMETER = 30;
	private static final Double DEFAULT_BORDER_SPEED = 1.0;
	private static final LocalTime DEFAULT_START_TIME = LocalTime.of(2, 0, 0);

	private String name;
	private World world;
	private Block center;
	private Integer initialDiameter, finalDiameter;
	private Double speed;
	private LocalTime startTime;
	private boolean isMoving;

	/**
	 * Creates a new border configuration.
	 * 
	 * @param name The configuration's name.
	 */
	public Border(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (this.name == name)
			return;

		String oldName = this.name;
		this.name = name;
		EventManager.callEvent(new BorderNameChangePostEvent(this, oldName));
	}

	@Override
	public World getWorld() {
		return world == null ? DEFAULT_WORLD : world;
	}

	@Override
	public void setWorld(World world) {
		if (getWorld().equals(world))
			return;

		World oldWorld = this.world;
		this.world = world;
		EventManager.callEvent(new BorderWorldChangePostEvent(this, oldWorld));
	}

	@Override
	public Block getCenter() {
		return center == null ? WorldManager.getHighestBlockYAt(getWorld(), 0, 0) : center;
	}

	@Override
	public void setCenter(Block center) {
		if (getCenter().equals(center))
			return;

		Block oldCenter = this.center;
		this.center = center;
		EventManager.callEvent(new BorderCenterChangePostEvent(this, oldCenter));
	}

	@Override
	public Integer getInitialDiameter() {
		return initialDiameter == null ? DEFAULT_INITIAL_DIAMETER : initialDiameter;
	}

	@Override
	public void setInitialDiameter(int initialDiameter) {
		if (getInitialDiameter() == this.initialDiameter)
			return;

		int oldInitialDiameter = this.initialDiameter;
		this.initialDiameter = initialDiameter;
		EventManager.callEvent(new BorderInitialDiameterChangePostEvent(this, oldInitialDiameter));
	}

	@Override
	public Integer getFinalDiameter() {
		return finalDiameter == null ? DEFAULT_FINAL_DIAMETER : finalDiameter;
	}

	@Override
	public void setFinalDiameter(int finalDiameter) {
		if (getFinalDiameter() == finalDiameter)
			return;

		int oldFinalDiameter = this.finalDiameter;
		this.finalDiameter = finalDiameter;
		EventManager.callEvent(new BorderFinalDiameterChangePostEvent(this, oldFinalDiameter));
	}

	@Override
	public Double getSpeed() {
		return speed == null ? DEFAULT_BORDER_SPEED : speed;
	}

	@Override
	public void setSpeed(double speed) {
		if (getSpeed() == speed)
			return;

		double oldSpeed = this.speed;
		LocalTime oldMoveTime = getMoveTime();
		this.speed = speed;
		EventManager.callEvent(new BorderSpeedChangePostEvent(this, oldSpeed, oldMoveTime));
	}

	@Override
	public LocalTime getStartTime() {
		return startTime == null ? DEFAULT_START_TIME : startTime;
	}

	@Override
	public void setStartTime(LocalTime startTime) {
		if (getStartTime().equals(startTime))
			return;

		LocalTime oldStartTime = this.startTime;
		this.startTime = startTime;
		EventManager.callEvent(new BorderStartTimeChangePostEvent(this, oldStartTime));
	}

	@Override
	public LocalTime getMoveTime() {
		return LocalTime.MIN.plusSeconds(new Double(getDistance() / getSpeed()).longValue());
	}

	@Override
	public void setMoveTime(LocalTime moveTime) {
		setSpeed(getDistance() / ((Integer) moveTime.toSecondOfDay()).doubleValue());
	}

	@Override
	public boolean isMoving() {
		return isMoving;
	}

	@Override
	public WorldBorder getWorldBorder() {
		return getWorld().getWorldBorder();
	}

	@Override
	public boolean reload() {
		PlatformPersistence<IBorder> persistence = new BorderPersistence().getPersistence();
		if (!persistence.deserialize(getName()))
			return false;

		setName(persistence.get().getName());
		setWorld(persistence.get().getWorld());
		setCenter(persistence.get().getCenter());
		setInitialDiameter(persistence.get().getInitialDiameter());
		setFinalDiameter(persistence.get().getFinalDiameter());
		setSpeed(persistence.get().getSpeed());
		setStartTime(persistence.get().getStartTime());
		return true;
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

	private Double getDistance() {
		return (double) Math.abs(getInitialDiameter() - getFinalDiameter());
	}
}
