package fr.pederobien.minecraft.border.commands.border;

import java.util.function.Supplier;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.impl.MinecraftCodeNode;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;

public class BorderNode extends MinecraftCodeNode {
	private Supplier<IBorder> border;

	/**
	 * Create a minecraft node defined by a label, which correspond to its name, and an explanation.
	 * 
	 * @param border      The border associated to this node.
	 * @param label       The name of the node.
	 * @param explanation The explanation of the node.
	 */
	protected BorderNode(Supplier<IBorder> border, String label, IMinecraftCode explanation) {
		super(label, explanation);
		this.border = border;
		setAvailable(() -> getBorder() != null);
	}

	/**
	 * @return The border assoiated to this node.
	 */
	protected IBorder getBorder() {
		return border.get();
	}
}
