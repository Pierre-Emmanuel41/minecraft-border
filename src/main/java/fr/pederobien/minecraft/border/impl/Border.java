package fr.pederobien.minecraft.border.impl;

import java.time.LocalTime;
import java.util.StringJoiner;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraft.border.event.BorderNameChangePostEvent;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
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
		this.world = world;
	}

	@Override
	public Block getCenter() {
		return center == null ? WorldManager.getHighestBlockYAt(getWorld(), 0, 0) : center;
	}

	@Override
	public void setCenter(Block center) {
		if (getCenter().equals(center))
			return;

		this.center = center;
	}

	@Override
	public Integer getInitialDiameter() {
		return initialDiameter == null ? DEFAULT_INITIAL_DIAMETER : initialDiameter;
	}

	@Override
	public void setInitialDiameter(int initialDiameter) {
		if (getInitialDiameter() == this.initialDiameter)
			return;

		this.initialDiameter = initialDiameter;
	}

	@Override
	public Integer getFinalDiameter() {
		return finalDiameter == null ? DEFAULT_FINAL_DIAMETER : finalDiameter;
	}

	@Override
	public void setFinalDiameter(int finalDiameter) {
		if (getFinalDiameter() == finalDiameter)
			return;

		this.finalDiameter = finalDiameter;
	}

	@Override
	public Double getSpeed() {
		return speed == null ? DEFAULT_BORDER_SPEED : speed;
	}

	@Override
	public void setSpeed(double speed) {
		if (getSpeed() == speed)
			return;

		this.speed = speed;
	}

	@Override
	public LocalTime getStartTime() {
		return startTime == null ? DEFAULT_START_TIME : startTime;
	}

	@Override
	public void setStartTime(LocalTime startTime) {
		if (getStartTime().equals(startTime))
			return;

		this.startTime = startTime;
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
	public void reset() {
		getWorldBorder().reset();
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
		StringJoiner joiner = new StringJoiner("\n");
		joiner.add("Name : " + getName());
		joiner.add("World : " + display(world, getWorld().getName()));
		joiner.add("Center : " + display(center, DisplayHelper.toString(getCenter())));
		joiner.add("Initial diameter : " + display(initialDiameter, getInitialDiameter() + " blocks"));
		joiner.add("Final diameter : " + display(finalDiameter, getFinalDiameter() + " blocks"));
		joiner.add("Speed : " + display(speed, DisplayHelper.format(getSpeed()) + " block/s"));
		joiner.add("Start time : " + display(startTime, DisplayHelper.toString(getStartTime(), true)));
		joiner.add("Move time : " + DisplayHelper.toString(getMoveTime(), true));
		joiner.add("End time : " + getStartTime().plusSeconds(getMoveTime().toSecondOfDay()));
		return joiner.toString();
	}

	private String display(Object object, String display) {
		return display.concat(object == null ? " (default value)" : "");
	}

	private Double getDistance() {
		return (double) Math.abs(getInitialDiameter() - getFinalDiameter());
	}
}
