package fr.pederobien.minecraft.border;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.dictionary.impl.JarXmlDictionaryParser;
import fr.pederobien.minecraft.border.commands.border.BorderCommandTree;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.persistence.BorderPersistence;
import fr.pederobien.minecraft.dictionary.impl.MinecraftDictionaryContext;
import fr.pederobien.minecraft.platform.interfaces.IPlatformPersistence;
import fr.pederobien.utils.AsyncConsole;

public class BorderPlugin extends JavaPlugin {
	private static final String DICTIONARY_FOLDER = "resources/dictionaries/";

	private static Plugin instance;
	private static IPlatformPersistence<IBorder> persistence;
	private static BorderCommandTree borderTree;

	/**
	 * @return The plugin associated to this border plugin.
	 */
	public static Plugin instance() {
		return instance;
	}

	/**
	 * @return The persistence that serialize and deserialize configurations.
	 */
	public static IPlatformPersistence<IBorder> getPersistence() {
		return persistence;
	}

	/**
	 * Get the tree to modify the characteristics of a border.
	 * 
	 * @return The border tree associated to this plugin.
	 */
	public static BorderCommandTree getBorderTree() {
		return borderTree;
	}

	@Override
	public void onEnable() {
		registerDictionaries();

		instance = this;
		persistence = new BorderPersistence().getPersistence();
		borderTree = new BorderCommandTree(getPersistence());
		registerTabExecutors();
	}

	private void registerDictionaries() {
		JarXmlDictionaryParser dictionaryParser = new JarXmlDictionaryParser(getFile().toPath());

		MinecraftDictionaryContext context = MinecraftDictionaryContext.instance();
		String[] dictionaries = new String[] { "English.xml", "French.xml" };
		for (String dictionary : dictionaries)
			try {
				context.register(dictionaryParser.parse(DICTIONARY_FOLDER.concat(dictionary)));
			} catch (Exception e) {
				AsyncConsole.print(e);
				for (StackTraceElement element : e.getStackTrace())
					AsyncConsole.print(element);
			}
	}

	private void registerTabExecutors() {
		PluginCommand border = getCommand(borderTree.getRoot().getLabel());
		border.setTabCompleter(borderTree.getRoot());
		border.setExecutor(borderTree.getRoot());
	}
}
