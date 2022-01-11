# Presentation

This project can be used by minecraft developers and minecraft players in order to generate border configuration files. It proposes one command : <code>./border</code> in order to create a new border configuration.

# Download

On Windows there is a restriction regarding the maximal length of a path, that's why it is preferable to clone the [minecraft-platform](https://github.com/Pierre-Emmanuel41/minecraft-game-plateform/blob/master/README.md) and run its <code>deploy.bat</code> file before cloning this project.

Then, to download this project on your computer you can use the following command line :

```git
git clone https://github.com/Pierre-Emmanuel41/minecraft-game-plateform.git
```

Finally, to add the project as maven dependency on your maven project :

```xml
<dependency>
	<groupId>fr.pederobien.minecraft</groupId>
	<artifactId>border</artifactId>
	<version>1.0_MC_1.13.2-SNAPSHOT</version>
</dependency>
```

To see the functionalities provided by this plugin, please have a look to [this presentation](https://github.com/Pierre-Emmanuel41/minecraft-border/blob/1.0_MC_1.13.2-SNAPSHOT/Presentation.md)
