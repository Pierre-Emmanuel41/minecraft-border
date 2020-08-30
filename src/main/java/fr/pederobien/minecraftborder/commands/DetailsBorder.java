package fr.pederobien.minecraftborder.commands;

import fr.pederobien.minecraftborder.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonDetails;

public class DetailsBorder extends CommonDetails<IBorderConfiguration> {

	protected DetailsBorder() {
		super(EBorderMessageCode.DETAILS_BORDER__EXPLANATION, EBorderMessageCode.DETAILS_BORDER__ON_DETAILS);
	}
}
