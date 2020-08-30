package fr.pederobien.minecraftborder.impl;

import java.time.LocalTime;
import java.util.StringJoiner;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.exceptions.worldstructure.WorldNotFoundException;
import fr.pederobien.minecraftgameplateform.impl.element.AbstractNominable;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.IObservableTimeLine;
import fr.pederobien.minecraftmanagers.WorldManager;

public class BorderConfiguration extends AbstractNominable implements IBorderConfiguration {
	private static final World DEFAULT_WORLD = WorldManager.OVERWORLD;
	private static final Integer DEFAULT_INITIAL_DIAMETER = 2000;
	private static final Integer DEFAULT_FINAL_DIAMETER = 30;
	private static final Double DEFAULT_BORDER_SPEED = 1.0;
	private static final LocalTime DEFAULT_START_TIME = LocalTime.of(2, 0, 0);

	private World world;
	private Block center;
	private Integer initialDiameter, finalDiameter;
	private Double borderSpeed;
	private LocalTime startTime;
	private IObservableTimeLine timeLine;
	private boolean isMoving;

	public BorderConfiguration(String name) {
		super(name);
	}

	@Override
	public int compareTo(IBorderConfiguration o) {
		return getStartTime().compareTo(o.getStartTime());
	}

	@Override
	public World getWorld() {
		return world == null ? DEFAULT_WORLD : world;
	}

	@Override
	public Block getBorderCenter() {
		return center == null ? WorldManager.getHighestBlockYAt(getWorld(), 0, 0) : center;
	}

	@Override
	public Integer getInitialBorderDiameter() {
		return initialDiameter == null ? DEFAULT_INITIAL_DIAMETER : initialDiameter;
	}

	@Override
	public Integer getFinalBorderDiameter() {
		return finalDiameter == null ? DEFAULT_FINAL_DIAMETER : finalDiameter;
	}

	@Override
	public Double getBorderSpeed() {
		return borderSpeed == null ? DEFAULT_BORDER_SPEED : borderSpeed;
	}

	@Override
	public LocalTime getStartTime() {
		return startTime == null ? DEFAULT_START_TIME : startTime;
	}

	@Override
	public LocalTime getMoveTime() {
		return LocalTime.of(0, 0, 0).plusSeconds(new Double(getDistance() / getBorderSpeed()).longValue());
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
	public void apply(IObservableTimeLine timeLine) {
		this.timeLine = timeLine;
		getWorldBorder().setCenter(getBorderCenter().getLocation());
		getWorldBorder().setSize(getInitialBorderDiameter());
		timeLine.addObserver(getStartTime(), this);
	}

	@Override
	public void pause() {
		getWorldBorder().setSize(getWorldBorder().getSize());
	}

	@Override
	public void relaunched() {
		if (isMoving())
			moveBorder(new Double(getWorldBorder().getSize()).longValue());
	}

	@Override
	public void reset() {
		getWorldBorder().reset();
		if (timeLine != null)
			timeLine.removeObserver(getStartTime(), this);
	}

	@Override
	public void timeChanged(LocalTime time) {
		moveBorder(getInitialBorderDiameter().longValue());
		isMoving = true;
	}

	@Override
	public void setWorld(String worldName) {
		World worldTemp = WorldManager.getWorld(worldName);
		if (worldTemp == null)
			throw new WorldNotFoundException(worldName);
		this.world = worldTemp;
	}

	@Override
	public void setBorderCenter(int x, int z) {
		this.center = WorldManager.getHighestBlockYAt(getWorld(), x, z);
	}

	@Override
	public void setInitialBorderDiameter(int initialDiameter) {
		this.initialDiameter = initialDiameter;
	}

	@Override
	public void setFinalBorderDiameter(int finalDiameter) {
		this.finalDiameter = finalDiameter;
	}

	@Override
	public void setBorderSpeed(double borderSpeed) {
		this.borderSpeed = borderSpeed;
	}

	@Override
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public void setMoveTime(LocalTime timeToMoveTheBorder) {
		setBorderSpeed(getDistance() / ((Integer) timeToMoveTheBorder.toSecondOfDay()).doubleValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof IBorderConfiguration))
			return false;

		IBorderConfiguration other = (IBorderConfiguration) obj;
		return getName().equals(other.getName());
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner("\n");
		joiner.add("Name : " + getName());
		joiner.add("World : " + display(world, getWorld().getName()));
		joiner.add("Center : " + display(center, DisplayHelper.toString(getBorderCenter())));
		joiner.add("Initial diameter : " + display(initialDiameter, getInitialBorderDiameter() + " blocks"));
		joiner.add("Final diameter : " + display(finalDiameter, getFinalBorderDiameter() + " blocks"));
		joiner.add("Speed : " + display(borderSpeed, DisplayHelper.format(getBorderSpeed()) + " block/s"));
		joiner.add("Start time : " + display(startTime, DisplayHelper.toString(getStartTime(), true)));
		joiner.add("Move time : " + DisplayHelper.toString(getMoveTime(), true));
		joiner.add("End time : " + getStartTime().plusSeconds(getMoveTime().toSecondOfDay()));
		return joiner.toString();
	}

	private String display(Object object, String display) {
		return display.concat(object == null ? " (default value)" : "");
	}

	private Double getDistance() {
		return (double) Math.abs(getInitialBorderDiameter() - getFinalBorderDiameter());
	}

	private void moveBorder(long currentDiameter) {
		getWorld().getWorldBorder().setSize(getFinalBorderDiameter(), currentDiameter / getBorderSpeed().longValue());
	}
}
