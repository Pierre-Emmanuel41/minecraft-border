package fr.pederobien.minecraft.border.commands.borders;

import java.util.function.Supplier;

import com.google.common.base.Function;

import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.commandtree.impl.MinecraftCodeNode;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;

public class BordersNode extends MinecraftCodeNode {
	private Supplier<IBorderList> list;

	/**
	 * Create a borders node defined by a label, which correspond to its name, and an explanation.
	 * 
	 * @param list        The list of borders associated to this node.
	 * @param label       The name of the node.
	 * @param explanation The explanation of the node.
	 * @param isAvailable True if this node is available, false otherwise.
	 */
	protected BordersNode(Supplier<IBorderList> list, String label, IMinecraftCode explanation, Function<IBorderList, Boolean> isAvailable) {
		super(label, explanation, () -> isAvailable.apply(list.get()));
		this.list = list;
	}

	/**
	 * Create a minecraft node defined by a label, which correspond to its name, and an explanation.
	 * 
	 * @param list        The list of borders associated to this node.
	 * @param label       The name of the node.
	 * @param explanation The explanation of the node.
	 */
	protected BordersNode(Supplier<IBorderList> list, String label, IMinecraftCode explanation) {
		super(label, explanation);
		this.list = list;
	}

	/**
	 * @return The list of borders associated to this node.
	 */
	public IBorderList getList() {
		return list.get();
	}
}
