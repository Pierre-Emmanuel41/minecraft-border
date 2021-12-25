package fr.pederobien.minecraft.border.commands.configurations;

import fr.pederobien.minecraft.border.interfaces.IBorderConfigurable;
import fr.pederobien.minecraft.commandtree.impl.MinecraftCodeRootNode;
import fr.pederobien.minecraft.commandtree.interfaces.IMinecraftCodeNode;

public class BordersCommandTree {
	private IMinecraftCodeNode root;
	private IBorderConfigurable borderConfigurable;
	private AddBordersNode addNode;
	private RemoveBordersNode removeNode;
	private ListBordersNode listNode;
	private ReloadBordersNode reloadNode;
	private DetailsBordersNode detailsNode;

	public BordersCommandTree(IBorderConfigurable borderConfigurable) {
		this.borderConfigurable = borderConfigurable;

		root = new MinecraftCodeRootNode("borders", EBordersCode.BORDERS__EXPLANATION, () -> borderConfigurable.getBorderList() != null);
		root.add(addNode = new AddBordersNode(borderConfigurable.getBorderList()));
		root.add(removeNode = new RemoveBordersNode(borderConfigurable.getBorderList()));
		root.add(listNode = new ListBordersNode(borderConfigurable.getBorderList()));
		root.add(reloadNode = new ReloadBordersNode(borderConfigurable.getBorderList()));
		root.add(detailsNode = new DetailsBordersNode(borderConfigurable.getBorderList()));
	}

	/**
	 * @return The border configurable managed by this command tree.
	 */
	public IBorderConfigurable getBorderConfigurable() {
		return borderConfigurable;
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
