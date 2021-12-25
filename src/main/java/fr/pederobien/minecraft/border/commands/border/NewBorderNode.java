package fr.pederobien.minecraft.border.commands.border;

import java.util.function.Consumer;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.commands.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.game.platform.commands.persistence.PersistenceNewNode;
import fr.pederobien.minecraft.game.platform.commands.persistence.PersistenceNewNode.PersistenceNewNodeBuilder;
import fr.pederobien.minecraft.game.platform.commands.persistence.PersistenceNodeFactory;

public class NewBorderNode implements ICodeSender {
	private PersistenceNewNode<IBorder> newNode;

	protected NewBorderNode(PersistenceNodeFactory<IBorder> factory) {
		// Action when the name is missing
		Consumer<CommandSender> onNameIsMissing = sender -> {
			send(eventBuilder(EBorderCode.BORDER__NEW_BORDER__NAME_IS_MISSING).build());
		};
		PersistenceNewNodeBuilder<IBorder> builder = factory.newNodeBuilder(onNameIsMissing);

		// Action when the name is already used.
		builder.onNameAlreadyTaken((sender, oldName, newName) -> send(eventBuilder(sender, EBorderCode.BORDER__NEW_BORDER__NAME_ALREADY_USED, oldName, newName)));

		// Action when the border is created.
		builder.onCreated((sender, border) -> send(eventBuilder(sender, EBorderCode.BORDER__NEW_BORDER__BORDER_CREATED, border.getName())));

		// Creating the node that creates borders.
		newNode = builder.build(EBorderCode.BORDER__NEW_BORDER__EXPLANATION);

		// Node always available.
		newNode.setAvailable(() -> true);
	}

	/**
	 * @return The node that creates borders.
	 */
	public PersistenceNewNode<IBorder> getNode() {
		return newNode;
	}
}
