package fr.pederobien.minecraft.border.impl;

import java.time.LocalTime;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderConfigurable;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.dictionary.impl.MinecraftMessageEvent.MinecraftMessageEventBuilder;
import fr.pederobien.minecraft.dictionary.impl.PlayerGroup;
import fr.pederobien.minecraft.game.event.GamePausePostEvent;
import fr.pederobien.minecraft.game.event.GameResumePostEvent;
import fr.pederobien.minecraft.game.event.GameStartPostEvent;
import fr.pederobien.minecraft.game.event.GameStopPostEvent;
import fr.pederobien.minecraft.game.impl.time.CountDown;
import fr.pederobien.minecraft.game.interfaces.time.ICountDown;
import fr.pederobien.minecraft.game.interfaces.time.IObsTimeLine;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.MessageManager.DisplayOption;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.Platform;
import fr.pederobien.utils.event.EventHandler;
import fr.pederobien.utils.event.EventManager;
import fr.pederobien.utils.event.IEventListener;

public class BorderTimeLineObserver implements IObsTimeLine, IEventListener, ICodeSender {
	private IBorder border;
	private ICountDown countDown;
	private LocalTime nextTime;
	private boolean isMoving;

	/**
	 * Creates a time line observer associated to the given border. When a game starts, it is registered as time line observer for the
	 * {@link IBorder#getStartTime()} value. The first <code>LocalTime</code> parameter refers to the absolute time (since the
	 * beginning of the game) at which the count down is over, the second <code>LocalTime</code> parameter refers to the next time at
	 * which this observer should be notified.
	 * 
	 * @param border       The border for this observer. It contains informations in order to observe the time line.
	 * @param initialValue The initial count down value.
	 * @param onTimeAction The action to perform when the count down is over.
	 */
	public BorderTimeLineObserver(IBorder border, int initialValue, Function<LocalTime, LocalTime> onTimeAction) {
		this.border = border;
		nextTime = border.getStartTime().get();

		String worldName = WorldManager.getWorldNameNormalised(border.getWorld().get());

		// Action to perform during the count down
		Consumer<Integer> countDownAction = count -> {
			MinecraftMessageEventBuilder builder = eventBuilder(EBorderCode.BORDER__MOVING_BORDER_COUNT_DOWN);
			builder.withDisplayOption(DisplayOption.TITLE).withColor(EColor.GOLD);
			builder.withGroup(PlayerGroup.of(worldName, player -> player.getWorld().equals(border.getWorld().get())));
			send(builder.build(worldName, count));
		};

		// Action to perform when the count down is over.
		Consumer<LocalTime> internalOnTimeAction = time -> {
			MinecraftMessageEventBuilder builder = eventBuilder(EBorderCode.BORDER__MOVING_BORDER);
			builder.withGroup(PlayerGroup.of(worldName, player -> player.getWorld().equals(border.getWorld().get())));
			border.getWorldBorder().setSize(border.getFinalDiameter().get(), (long) border.getWorldBorder().getSize() / border.getSpeed().get().longValue());
			send(builder.withColor(EColor.DARK_RED).build(worldName));
			nextTime = onTimeAction.apply(time);
			isMoving = true;
		};

		countDown = new CountDown(initialValue, countDownAction, internalOnTimeAction);

		EventManager.registerListener(this);
	}

	/**
	 * Creates a time line observer associated to this border. When a game starts, it is registered as time line observer for the
	 * {@link IBorder#getStartTime()} value. And then will never be notified.
	 * 
	 * @param border       The border associated to this observer.
	 * @param initialValue The initial count down value.
	 */
	public BorderTimeLineObserver(IBorder border, int initialValue) {
		this(border, initialValue, time -> null);
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
	private void onGameStart(GameStartPostEvent event) {
		if (!(event.getGame() instanceof IBorderConfigurable) || !((IBorderConfigurable) event.getGame()).getBorders().toList().contains(border))
			return;

		if (nextTime != null)
			Platform.get(event.getGame().getPlugin()).getTimeLine().register(nextTime, this);
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

		double second = border.getMoveTime().toSecondOfDay() * (getDistance(border.getWorldBorder().getSize()) / getDistance(border.getInitialDiameter().get()));
		border.getWorldBorder().setSize(border.getFinalDiameter().get(), (long) second);
	}

	private double getDistance(double currentDiameter) {
		return Math.abs(currentDiameter - (double) border.getFinalDiameter().get());
	}
}
