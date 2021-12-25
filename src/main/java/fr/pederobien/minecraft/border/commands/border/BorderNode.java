package fr.pederobien.minecraft.border.commands.border;

import com.google.common.base.Function;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.impl.MinecraftCodeNode;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;

public class BorderNode extends MinecraftCodeNode {
	private IBorder border;

	/**
	 * Create a border node defined by a label, which correspond to its name, and an explanation.
	 * 
	 * @param border      The border associated to this node.
	 * @param label       The name of the node.
	 * @param explanation The explanation of the node.
	 * @param isAvailable True if this node is available, false otherwise.
	 */
	protected BorderNode(IBorder border, String label, IMinecraftCode explanation, Function<IBorder, Boolean> isAvailable) {
		super(label, explanation, () -> isAvailable.apply(border));
		this.border = border;
	}

	/**
	 * Create a minecraft node defined by a label, which correspond to its name, and an explanation.
	 * 
	 * @param border      The border associated to this node.
	 * @param label       The name of the node.
	 * @param explanation The explanation of the node.
	 */
	protected BorderNode(IBorder border, String label, IMinecraftCode explanation) {
		super(label, explanation);
		this.border = border;
	}

	/**
	 * @return The border assoiated to this node.
	 */
	protected IBorder getBorder() {
		return border;
	}
}
