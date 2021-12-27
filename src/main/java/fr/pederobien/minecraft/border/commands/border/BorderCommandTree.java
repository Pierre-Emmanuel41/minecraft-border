package fr.pederobien.minecraft.border.commands.border;

import fr.pederobien.minecraft.border.impl.EBorderCode;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.commandtree.impl.MinecraftCodeRootNode;
import fr.pederobien.minecraft.commandtree.interfaces.IMinecraftCodeNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceDeleteNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceDetailsNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceListNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceLoadNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceNewNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceNodeFactory;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceRenameNode;
import fr.pederobien.minecraft.platform.commands.persistence.PersistenceSaveNode;
import fr.pederobien.minecraft.platform.interfaces.IPlatformPersistence;

public class BorderCommandTree {
	private IMinecraftCodeNode root;
	private PersistenceNodeFactory<IBorder> factory;
	private PersistenceNewNode<IBorder> newNode;
	private PersistenceDetailsNode<IBorder> detailsNode;
	private PersistenceRenameNode renameNode;
	private PersistenceDeleteNode deleteNode;
	private PersistenceLoadNode loadNode;
	private PersistenceListNode listNode;
	private PersistenceSaveNode saveNode;
	private WorldBorderNode worldNode;
	private InitialDiameterBorderNode initialDiameterNode;
	private CenterBorderNode centerNode;
	private FinalDiameterBorderNode finalDiameterNode;
	private StartTimeBorderNode startTimeNode;
	private SpeedBorderNode speedNode;
	private MoveTimeBorderNode moveTimeNode;

	public BorderCommandTree(IPlatformPersistence<IBorder> persistence) {
		root = new MinecraftCodeRootNode("border", EBorderCode.BORDER__EXPLANATION, () -> true);

		factory = new PersistenceNodeFactory<IBorder>(persistence);
		root.add(newNode = new NewBorderNode(factory).getNode());
		root.add(detailsNode = new DetailsBorderNode(factory).getNode());
		root.add(renameNode = new RenameBorderNode(factory).getNode());
		root.add(deleteNode = new DeleteBorderNode(factory).getNode());
		root.add(loadNode = new LoadBorderNode(factory).getNode());
		root.add(listNode = new ListBorderNode(factory).getNode());
		root.add(saveNode = new SaveBorderNode(factory).getNode());
		root.add(worldNode = new WorldBorderNode(getBorder()));
		root.add(initialDiameterNode = new InitialDiameterBorderNode(getBorder()));
		root.add(centerNode = new CenterBorderNode(getBorder()));
		root.add(finalDiameterNode = new FinalDiameterBorderNode(getBorder()));
		root.add(startTimeNode = new StartTimeBorderNode(getBorder()));
		root.add(speedNode = new SpeedBorderNode(getBorder()));
		root.add(moveTimeNode = new MoveTimeBorderNode(getBorder()));
	}

	/**
	 * @return The root of this command tree.
	 */
	public IMinecraftCodeNode getRoot() {
		return root;
	}

	/**
	 * @return The last created border or null of no border has been created.
	 */
	public IBorder getBorder() {
		return factory.getPersistence().get();
	}

	/**
	 * Set the border manipulated by this tree.
	 * 
	 * @param border The new border.
	 */
	public void setBorder(IBorder border) {
		factory.getPersistence().set(border);
	}

	/**
	 * @return The node that creates a new border to configure.
	 */
	public PersistenceNewNode<IBorder> getNewNode() {
		return newNode;
	}

	/**
	 * @return The node that displays the details of the current border.
	 */
	public PersistenceDetailsNode<IBorder> getDetailsNode() {
		return detailsNode;
	}

	/**
	 * @return The node that renames border.
	 */
	public PersistenceRenameNode getRenameNode() {
		return renameNode;
	}

	/**
	 * @return The node that deletes border files.
	 */
	public PersistenceDeleteNode getDeleteNode() {
		return deleteNode;
	}

	/**
	 * @return The node that loads borders.
	 */
	public PersistenceLoadNode getLoadNode() {
		return loadNode;
	}

	/**
	 * @return The node that displays the list of border files.
	 */
	public PersistenceListNode getListNode() {
		return listNode;
	}

	/**
	 * @return The node that saves borders.
	 */
	public PersistenceSaveNode getSaveNode() {
		return saveNode;
	}

	/**
	 * @return The node that modifies the world of the current border.
	 */
	public WorldBorderNode getWorldNode() {
		return worldNode;
	}

	/**
	 * @return The node that modifies the initial diameter of the current border.
	 */
	public InitialDiameterBorderNode getInitialDiameterNode() {
		return initialDiameterNode;
	}

	/**
	 * @return The node that modifies the center of the current border.
	 */
	public CenterBorderNode getCenterNode() {
		return centerNode;
	}

	/**
	 * @return The node that modifies the final diameter of the current border.
	 */
	public FinalDiameterBorderNode getFinalDiameterNode() {
		return finalDiameterNode;
	}

	/**
	 * @return The node that modifies the start time of the current border.
	 */
	public StartTimeBorderNode getStartTimeNode() {
		return startTimeNode;
	}

	/**
	 * @return The node that modify the speed of the current border.
	 */
	public SpeedBorderNode getSpeedNode() {
		return speedNode;
	}

	/**
	 * @return The node that modifies the move time of the current border.
	 */
	public MoveTimeBorderNode getMoveTimeNode() {
		return moveTimeNode;
	}
}
