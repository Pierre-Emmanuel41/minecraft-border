package fr.pederobien.minecraft.border.commands.borders;

import java.util.function.Supplier;

import fr.pederobien.minecraft.border.BorderPlugin;
import fr.pederobien.minecraft.border.commands.border.BorderCommandTree;
import fr.pederobien.minecraft.border.interfaces.IBorderConfigurable;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.minecraft.commandtree.impl.MinecraftCodeRootNode;
import fr.pederobien.minecraft.commandtree.interfaces.IMinecraftCodeRootNode;

public class BordersCommandTree {
	private IMinecraftCodeRootNode root;
	private Supplier<IBorderConfigurable> configurable;
	private BorderCommandTree borderTree;
	private AddBordersNode addNode;
	private RemoveBordersNode removeNode;
	private ListBordersNode listNode;
	private ReloadBordersNode reloadNode;
	private DetailsBordersNode detailsNode;

	public BordersCommandTree(Supplier<IBorderConfigurable> configurable) {
		this.configurable = configurable;

		root = new MinecraftCodeRootNode("borders", EBordersCode.BORDERS__EXPLANATION, () -> configurable.get() != null);

		borderTree = new BorderCommandTree(BorderPlugin.getPersistence());
		root.add(addNode = new AddBordersNode(() -> getBorders()));
		root.add(removeNode = new RemoveBordersNode(() -> getBorders()));
		root.add(listNode = new ListBordersNode(() -> getBorders()));
		root.add(reloadNode = new ReloadBordersNode(() -> getBorders()));
		root.add(detailsNode = new DetailsBordersNode(() -> getBorders(), borderTree.getDetailsNode()));
	}

	/**
	 * @return The root of this command tree.
	 */
	public IMinecraftCodeRootNode getRoot() {
		return root;
	}

	/**
	 * @return The border configurable managed by this command tree.
	 */
	public IBorderList getBorders() {
		IBorderConfigurable borders = configurable.get();
		return borders == null ? null : borders.getBorders();
	}

	/**
	 * @return The node that adds borders to the list.
	 */
	public AddBordersNode getAddNode() {
		return addNode;
	}

	/**
	 * @return The node that removes borders from the list.
	 */
	public RemoveBordersNode getRemoveNode() {
		return removeNode;
	}

	/**
	 * @return The node that display the border name associated to a world.
	 */
	public ListBordersNode getListNode() {
		return listNode;
	}

	/**
	 * @return The node that reloads borders from the list.
	 */
	public ReloadBordersNode getReloadNode() {
		return reloadNode;
	}

	/**
	 * @return The node that displays the characteristics of one or several borders from the list.
	 */
	public DetailsBordersNode getDetailsNode() {
		return detailsNode;
	}
}
