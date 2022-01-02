package fr.pederobien.minecraft.border.commands.border;

import java.time.LocalTime;
import java.util.StringJoiner;
import java.util.function.BiConsumer;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.managers.WorldManager;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceDetailsNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceNodeFactory;

public class DetailsBorderNode implements ICodeSender {
	private PersistenceDetailsNode<IBorder> detailsNode;

	protected DetailsBorderNode(PersistenceNodeFactory<IBorder> factory) {
		// Action in order to display the details of the border
		BiConsumer<CommandSender, IBorder> onDetails = (sender, border) -> {
			sendSuccessful(sender, EBorderCode.BORDER__DETAILS_BORDER__ON_DETAILS, getDetails(sender, border));
		};

		// Creating the node that displays the details of the current border.
		detailsNode = factory.detailsNode(onDetails).build(EBorderCode.BORDER__DETAILS_BORDER__EXPLANATION);

		// Node available if and only if the current border is not null
		detailsNode.setAvailable(() -> factory.getPersistence().get() != null);
	}

	/**
	 * @return The node that display the details of the border.
	 */
	public PersistenceDetailsNode<IBorder> getNode() {
		return detailsNode;
	}

	/**
	 * Get the details of a border. Each parameter name are translated according to the sender nationality.
	 * 
	 * @param sender The sender used to translate parameter name.
	 * @param border The border whose the details should be returned.
	 * 
	 * @return The border details.
	 */
	public String getDetails(CommandSender sender, IBorder border) {
		String name = getMessage(sender, EBorderCode.BORDER_NAME, border.getName());
		String world = getMessage(sender, EBorderCode.BORDER_WORLD, WorldManager.getWorldNameNormalised(border.getWorld().get()));
		String center = getMessage(sender, EBorderCode.BORDER_CENTER, DisplayHelper.toString(border.getCenter().get()));
		String initialDiameter = getMessage(sender, EBorderCode.BORDER_INITIAL_DIAMETER, border.getInitialDiameter());
		String finalDiameter = getMessage(sender, EBorderCode.BORDER_FINAL_DIAMETER, border.getFinalDiameter());
		String speed = getMessage(sender, EBorderCode.BORDER_SPEED, border.getSpeed());
		String startTime = getMessage(sender, EBorderCode.BORDER_START_TIME, DisplayHelper.toString(border.getStartTime().get(), true));
		String moveTime = getMessage(sender, EBorderCode.BORDER_MOVE_TIME, DisplayHelper.toString(border.getMoveTime(), true));
		LocalTime end = border.getStartTime().get().plusSeconds(border.getMoveTime().toSecondOfDay());
		String endTime = getMessage(sender, EBorderCode.BORDER_END_TIME, DisplayHelper.toString(end, false));

		StringJoiner joiner = new StringJoiner("\n");
		joiner.add(name).add(world).add(center).add(initialDiameter).add(finalDiameter).add(speed).add(startTime).add(moveTime).add(endTime);
		return joiner.toString();
	}
}
