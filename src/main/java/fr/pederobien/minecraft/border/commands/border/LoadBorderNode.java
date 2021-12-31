package fr.pederobien.minecraft.border.commands.border;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceLoadNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceLoadNode.PersistenceLoadNodeBuilder;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceNodeFactory;

public class LoadBorderNode implements ICodeSender {
	private PersistenceLoadNode loadNode;

	protected LoadBorderNode(PersistenceNodeFactory<IBorder> factory) {
		// Action to perform when the border file name to delete is missing.
		Consumer<CommandSender> onNameIsMissing = sender -> {
			send(eventBuilder(sender, EBorderCode.BORDER__LOAD_BORDER__NAME_IS_MISSING).build());
		};
		PersistenceLoadNodeBuilder<IBorder> builder = factory.loadNode(onNameIsMissing);

		// Action to perform when a border is loaded.
		BiConsumer<CommandSender, IBorder> onLoaded = (sender, border) -> {
			sendSuccessful(sender, EBorderCode.BORDER__LOAD_BORDER__BORDER_LOADED, border.getName());
		};
		builder.onLoaded(onLoaded);

		// Creating the node that loads border.
		loadNode = builder.build(EBorderCode.BORDER__LOAD_BORDER__EXPLANATION);

		// Always available
		loadNode.setAvailable(() -> true);
	}

	/**
	 * @return The persistence that load borders
	 */
	public PersistenceLoadNode getNode() {
		return loadNode;
	}
}
