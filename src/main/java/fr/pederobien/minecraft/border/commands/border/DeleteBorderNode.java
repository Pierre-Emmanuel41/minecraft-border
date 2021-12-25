package fr.pederobien.minecraft.border.commands.border;

import java.util.function.Consumer;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.commands.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.game.platform.commands.persistence.PersistenceDeleteNode;
import fr.pederobien.minecraft.game.platform.commands.persistence.PersistenceDeleteNode.PersistenceDeleteNodeBuilder;
import fr.pederobien.minecraft.game.platform.commands.persistence.PersistenceNodeFactory;

public class DeleteBorderNode implements ICodeSender {
	private PersistenceDeleteNode deleteNode;

	protected DeleteBorderNode(PersistenceNodeFactory<IBorder> factory) {
		// Action to perform when the border file name is missing.
		Consumer<CommandSender> onNameIsMissing = sender -> {
			send(eventBuilder(sender, EBorderCode.BORDER__DELETE_BORDER__NAME_IS_MISSING).build());
		};
		PersistenceDeleteNodeBuilder builder = factory.deleteNode(onNameIsMissing);

		// Action to perform when the border file is deleted.
		builder.onDeleted((sender, name) -> send(eventBuilder(sender, EBorderCode.BORDER__DELETE_BORDER__BORDER_DELETED, name)));

		// Creating node that delete border files.
		deleteNode = builder.build(EBorderCode.BORDER__DELETE_BORDER__EXPLANATION);

		// Always available
		deleteNode.setAvailable(() -> true);
	}

	/**
	 * @return The node that delete border files.
	 */
	public PersistenceDeleteNode getNode() {
		return deleteNode;
	}
}
