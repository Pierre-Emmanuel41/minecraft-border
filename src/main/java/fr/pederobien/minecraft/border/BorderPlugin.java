package fr.pederobien.minecraft.border;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.minecraft.border.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.commands.IParentCommand;
import fr.pederobien.minecraftgameplateform.utils.Plateform;

public class BorderPlugin extends JavaPlugin {
	private static Plugin plugin;
	private static IParentCommand<IBorderConfiguration> borderCommand;

	/**
	 * @return The plugin associated to this border plugin.
	 */
	public static Plugin get() {
		return plugin;
	}

	/**
	 * @return The current border configuration for this plugin.
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
		String[] dictionaries = new String[] { "Border.xml", "Borders.xml" };
		// Registering French dictionaries
		registerDictionary("French", dictionaries);

		// Registering English dictionaries
		registerDictionary("English", dictionaries);
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
