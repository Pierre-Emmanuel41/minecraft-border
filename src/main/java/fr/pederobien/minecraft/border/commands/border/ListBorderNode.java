package fr.pederobien.minecraft.border.commands.border;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.bukkit.command.CommandSender;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.interfaces.ICodeSender;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceListNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceListNode.PersistenceListNodeBuilder;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceNodeFactory;

public class ListBorderNode implements ICodeSender {
	private PersistenceListNode listNode;

	protected ListBorderNode(PersistenceNodeFactory<IBorder> factory) {
		// Action When there is no border file on the server
		Consumer<CommandSender> onNoElement = sender -> sendSuccessful(sender, EBorderCode.BORDER__LIST_BORDER__NO_ELEMENT);
		PersistenceListNodeBuilder builder = factory.listNode(onNoElement);

		// Action when there is one border file on the server
		BiConsumer<CommandSender, String> onOneElement = (sender, border) -> sendSuccessful(sender, EBorderCode.BORDER__LIST_BORDER__ONE_ELEMENT, border);
		builder.onOneElement(onOneElement);

		// Action when there are several border files on the server
		BiConsumer<CommandSender, List<String>> onSeveralElements = (sender, borders) -> {
			StringJoiner joiner = new StringJoiner("\n");
			for (String border : borders)
				joiner.add(border);

			sendSuccessful(sender, EBorderCode.BORDER__LIST_BORDER__SEVERAL_ELEMENTS, joiner);
		};
		builder.onSeveralElements(onSeveralElements);

		// Creating the node that display the list of border files
		listNode = builder.build(EBorderCode.BORDER__LIST_BORDER__EXPLANATION);

		// Always available
		listNode.setAvailable(() -> true);
	}

	/**
	 * @return The node that displays the list of border files.
	 */
	public PersistenceListNode getNode() {
		return listNode;
	}
}
