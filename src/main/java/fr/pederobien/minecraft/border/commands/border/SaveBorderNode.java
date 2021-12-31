package fr.pederobien.minecraft.border.commands.border;

import java.util.function.BiConsumer;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceNodeFactory;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceSaveNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceSaveNode.PersistenceSaveNodeBuilder;

public class SaveBorderNode implements ICodeSender {
	private PersistenceSaveNode saveNode;

	protected SaveBorderNode(PersistenceNodeFactory<IBorder> factory) {
		// Action when the border serialization fails
		BiConsumer<CommandSender, IBorder> onFailToSerialize = (sender, border) -> {
			send(eventBuilder(sender, EBorderCode.BORDER__SAVE_BORDER__FAIL_TO_SAVE, border.getName()));
		};
		PersistenceSaveNodeBuilder<IBorder> builder = factory.saveNode(onFailToSerialize);

		// Action when the border is serialized
		BiConsumer<CommandSender, IBorder> onSerialized = (sender, border) -> {
			sendSuccessful(sender, EBorderCode.BORDER__SAVE_BORDER__BORDER_SAVED, border.getName());
		};
		builder.onSerialized(onSerialized);

		// Creating the node that save borders
		saveNode = builder.build(EBorderCode.BORDER__SAVE_BORDER__EXPLANATION);

		// Node available if and only if the current border is not null
		saveNode.setAvailable(() -> factory.getPersistence().get() != null);
	}

	/**
	 * @return The node that saves the border characteristics.
	 */
	public PersistenceSaveNode getNode() {
		return saveNode;
	}
}
