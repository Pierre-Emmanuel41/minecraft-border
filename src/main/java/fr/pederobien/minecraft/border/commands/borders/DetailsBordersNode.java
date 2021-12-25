package fr.pederobien.minecraft.border.commands.borders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.game.impl.DisplayHelper;
import fr.pederobien.minecraft.managers.WorldManager;

public class DetailsBordersNode extends BordersNode {

	/**
	 * Creates a node that display the characteristics of one or several borders from the given list.
	 * 
	 * @param list The list associated to this node.
	 */
	protected DetailsBordersNode(IBorderList list) {
		super(list, "details", EBordersCode.BORDERS__DETAILS__EXPLANATION, l -> l != null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<IBorder> borders = args.length == 0 ? getList().toList() : new ArrayList<IBorder>();

		for (String border : args) {
			Optional<IBorder> optBorder = getList().getBorder(border);
			if (!optBorder.isPresent()) {
				send(eventBuilder(sender, EBordersCode.BORDERS__DETAILS_BORDERS__BORDER_NOT_REGISTERED, getList().getName(), border));
				return false;
			}

			borders.add(optBorder.get());
		}

		StringJoiner joiner = new StringJoiner("-------------\n", DEFAULT_PREFIX, DEFAULT_SUFFIX);

		for (IBorder border : borders) {
			// Step 1: Creating translation of each parameter name
			String name = getMessage(sender, EBorderCode.BORDER_NAME, border.getName());
			String world = getMessage(sender, EBorderCode.BORDER_WORLD, WorldManager.getWorldNameNormalised(border.getWorld()));
			String center = getMessage(sender, EBorderCode.BORDER_CENTER, DisplayHelper.toString(border.getCenter()));
			String initialDiameter = getMessage(sender, EBorderCode.BORDER_INITIAL_DIAMETER, border.getInitialDiameter());
			String finalDiameter = getMessage(sender, EBorderCode.BORDER_FINAL_DIAMETER, border.getFinalDiameter());
			String speed = getMessage(sender, EBorderCode.BORDER_SPEED, border.getSpeed());
			String startTime = getMessage(sender, EBorderCode.BORDER_START_TIME, DisplayHelper.toString(border.getStartTime(), false));
			String moveTime = getMessage(sender, EBorderCode.BORDER_MOVE_TIME, DisplayHelper.toString(border.getMoveTime(), false));
			LocalTime end = border.getStartTime().plusSeconds(border.getMoveTime().toSecondOfDay());
			String endTime = getMessage(sender, EBorderCode.BORDER_END_TIME, DisplayHelper.toString(end, false));

			// Step 2: Joining with a new line
			StringJoiner borderJoiner = new StringJoiner("\n");
			borderJoiner.add(name).add(world).add(center).add(initialDiameter).add(finalDiameter).add(speed).add(startTime).add(moveTime).add(endTime);
			joiner.add(borderJoiner.toString());
		}

		sender.sendMessage(joiner.toString());
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> alreadyMentionned = asList(args);
		return filter(getList().stream().map(border -> border.getName()).filter(name -> !alreadyMentionned.contains(name)), args);
	}
}
