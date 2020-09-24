package fr.pederobien.minecraftborder;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.commands.IParentCommand;
import fr.pederobien.minecraftgameplateform.utils.Plateform;

public class BorderPlugin extends JavaPlugin {
	private static Plugin plugin;
	private static IParentCommand<IBorderConfiguration> borderCommand;

	/**
	 * @return The plugin associated to this area plugin.
	 */
	public static Plugin get() {
		return plugin;
	}

	/**
	 * @return The current hunger game configuration for this plugin.
	 */
	public static IBorderConfiguration getCurrentBorder() {
		return borderCommand.getParent().get();
	}

	@Override
	public void onEnable() {
		Plateform.getPluginHelper().register(this);
		plugin = this;

		borderCommand = new BorderCommand(this);
		registerDictionaries();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	private void registerDictionaries() {
		// Registering French dictionaries
		registerDictionary("French", "Border.xml", "Borders.xml");

		// Registering English dictionaries
		registerDictionary("English", "Border.xml", "Borders.xml");
	}

	private void registerDictionary(String parent, String... dictionaryNames) {
		Path jarPath = Plateform.ROOT.getParent().resolve(getName().concat(".jar"));
		String dictionariesFolder = "resources/dictionaries/".concat(parent).concat("/");
		for (String name : dictionaryNames)
			registerDictionary(Plateform.getDefaultDictionaryParser(dictionariesFolder.concat(name)), jarPath);
	}

	private void registerDictionary(IDictionaryParser parser, Path jarPath) {
		try {
			Plateform.getNotificationCenter().getDictionaryContext().register(parser, jarPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
