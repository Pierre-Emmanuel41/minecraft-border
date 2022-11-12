package fr.pederobien.minecraft.border.impl;

import java.time.LocalTime;
import java.util.function.Consumer;

import fr.pederobien.minecraft.border.commands.border.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderConfigurable;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.dictionary.impl.MinecraftMessageEvent.MinecraftMessageEventBuilder;
import fr.pederobien.minecraft.dictionary.impl.PlayerGroup;
import fr.pederobien.minecraft.game.event.GamePausePostEvent;
import fr.pederobien.minecraft.game.event.GameResumePostEvent;
import fr.pederobien.minecraft.game.event.GameStopPostEvent;
import fr.pederobien.minecraft.game.impl.time.CountDown;
import fr.pederobien.minecraft.game.interfaces.IGame;
import fr.pederobien.minecraft.game.interfaces.time.ICountDown;
import fr.pederobien.minecraft.game.interfaces.time.IObsTimeLine;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.MessageManager.DisplayOption;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.Platform;
import fr.pederobien.minecraft.platform.event.ConfigurableValueChangeEvent;
import fr.pederobien.utils.event.EventHandler;
import fr.pederobien.utils.event.EventManager;
import fr.pederobien.utils.event.IEventListener;

public class BorderTimeLineObserver implements IObsTimeLine, IEventListener, ICodeSender {
	private IBorder border;
	private Consumer<Integer> countDownAction;
	private Consumer<LocalTime> onTimeAction;
	private ICountDown countDown;
	private LocalTime nextTime;
	private boolean isMoving;

	/**
	 * Creates a time line observer associated to the given border. It is registered as time line observer for the
	 * {@link IBorder#getStartTime()} value.
	 * 
	 * @param border The border for this observer. It contains informations in order to observe the time line.
	 * @param game   the game associated to this border.
	 */
	public BorderTimeLineObserver(IBorder border, IGame game) {
		this.border = border;
		nextTime = border.getStartTime().get();

		String worldName = WorldManager.getWorldNameNormalised(border.getWorld().get());

		// Action to perform during the count down
		countDownAction = count -> {
			MinecraftMessageEventBuilder builder = eventBuilder(EBorderCode.BORDER__MOVING_BORDER_COUNT_DOWN);
			builder.withDisplayOption(DisplayOption.TITLE).withColor(EColor.GOLD);
			builder.withGroup(PlayerGroup.of(worldName, player -> player.getWorld().equals(border.getWorld().get())));
			send(builder.build(worldName, count));
		};

		// Action to perform when the count down is over.
		onTimeAction = time -> {
			MinecraftMessageEventBuilder builder = eventBuilder(EBorderCode.BORDER__MOVING_BORDER);
			builder.withGroup(PlayerGroup.of(worldName, player -> player.getWorld().equals(border.getWorld().get())));
			border.getWorldBorder().setSize(border.getFinalDiameter().get(), (long) (border.getWorldBorder().getSize() / border.getSpeed().get()));
			send(builder.withColor(EColor.DARK_RED).build(worldName));
			isMoving = true;
		};

		LocalTime startTime = border.getStartTime().get();
		LocalTime realStartTime = startTime.equals(LocalTime.MIN) ? LocalTime.of(0, 0, 1) : startTime;
		countDown = new CountDown(startTime.toSecondOfDay() < 5 ? startTime.toSecondOfDay() : 5, countDownAction, onTimeAction);
		Platform.get(game.getPlugin()).getTimeLine().register(realStartTime, this);

		EventManager.registerListener(this);
	}

	@Override
	public ICountDown getCountDown() {
		return countDown;
	}

	@Override
	public LocalTime getNextTime() {
		return nextTime;
	}

	@EventHandler
	private void onGameStop(GameStopPostEvent event) {
		if (!(event.getGame() instanceof IBorderConfigurable) || !((IBorderConfigurable) event.getGame()).getBorders().toList().contains(border))
			return;

		if (nextTime != null)
			Platform.get(event.getGame().getPlugin()).getTimeLine().unregister(nextTime, this);

		EventManager.unregisterListener(this);
	}

	@EventHandler
	private void onGamePause(GamePausePostEvent event) {
		if (!(event.getGame() instanceof IBorderConfigurable) || !((IBorderConfigurable) event.getGame()).getBorders().toList().contains(border) || !isMoving)
			return;

		border.getWorldBorder().setSize(border.getWorldBorder().getSize());
	}

	@EventHandler
	private void onGameResume(GameResumePostEvent event) {
		if (!(event.getGame() instanceof IBorderConfigurable) || !((IBorderConfigurable) event.getGame()).getBorders().toList().contains(border) || !isMoving)
			return;

		updateSpeed();
	}

	@EventHandler
	private void onCenterChange(ConfigurableValueChangeEvent event) {
		if (!event.getConfigurable().equals(border.getCenter()))
			return;

		border.getWorldBorder().setCenter(border.getCenter().get().getLocation());
	}

	@EventHandler
	private void onSpeedChange(ConfigurableValueChangeEvent event) {
		if (!event.getConfigurable().equals(border.getSpeed()) || !isMoving)
			return;

		updateSpeed();
	}

	@EventHandler
	private void onInitialDiameterChange(ConfigurableValueChangeEvent event) {
		if (!event.getConfigurable().equals(border.getInitialDiameter()))
			return;

		if (!isMoving || border.getInitialDiameter().get() < (Integer) event.getOldValue())
			border.getWorldBorder().setSize(border.getInitialDiameter().get());
	}

	private double getDistance(double currentDiameter) {
		return Math.abs(currentDiameter - (double) border.getFinalDiameter().get());
	}

	private void updateSpeed() {
		double second = border.getMoveTime().toSecondOfDay() * (getDistance(border.getWorldBorder().getSize()) / getDistance(border.getInitialDiameter().get()));
		border.getWorldBorder().setSize(border.getFinalDiameter().get(), (long) second);
	}
}
