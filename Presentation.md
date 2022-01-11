# Presentation

### 1) Minecraft features

As a minecraft player, there is one functionality proposed by this plugin : The command "./border". This command allows server operators to create border configuration files according to their needs. This command proposes the following arguments :

border (minecraft command)  
&ensp;center - To set the center of a border  
&ensp;delete - To delete a border file  
&ensp;details - To display border's characteristics  
&ensp;finalDiameter - To set the final diameter of a border  
&ensp;help - To get help about the other argument  
&ensp;initialDiameter - To set the initial diameter of a border  
&ensp;list - To display border file names  
&ensp;load - To load a border to configure  
&ensp;moveTime - To set the time it takes to move the border from its initial diameter to its final diameter  
&ensp;new - To create a new border to configure  
&ensp;rename - To rename a border  
&ensp;save - To save characteristics of a border  
&ensp;speed - To set the speed of a border  
&ensp;startTime - To set the time after which the border moves from its initial diameter to its final diameter  
&ensp;world - To define the world in which the border is applied  

Once the border has been configured according to user's needs, running the command <code>./border save</code> will generate the following file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<root>
	<version>1.0</version>
	<name>BorderTest</name>
	<world>world_nether</world>
	<center x="0" y="63" z="0"/>
	<initialdiameter>1500</initialdiameter>
	<finaldiameter>30</finaldiameter>
	<borderspeed>1.5</borderspeed>
	<starttime>01:00</starttime>
</root>
```

The version attribute is automatically updated and generated according to the algorithm version that serialize and deserialize the configuration files. It should always be present in the file otherwise it cannot be parsed by the plugin.

### 2) Developpers features

First, The command described above is modelled by the <code>BorderCommandTree</code> class and is available from the <code>BorderPlugin</code> as static attribute. The root of this tree is set as TabCompleter and CommandExecutor for the minecraft <code>border</code> command.

Second, a border is modelled by the <code>IBorder</code> interface. Each modification of a parameter will throw a <code>ConfigurableValueChangeEvent</code>.

Third, a game can be played in several dimension like in the Overworld, in the Nether or in the End. That is why, if a game has borders in several worlds, then it should implements the <code>IBorderConfigurable</code> interface in order to propose an access its <code>IBorderList</code>. This list is responsible to store border associated to a specific world. This means that if a border associated to the overworld is already present in the border list and the user tries to add another border associated to the overworld, then the previous border is removed. With this interface comes another command tree : <code>BordersCommandTree</code> which provides already implemented minecraft command line argument in order to modify the list of borders associated to a game :

borders - To add/remove/reload a border  
&ensp;add - To add borders to a game  
&ensp;details - To display characteristics of one or several borders for a game  
&ensp;list - To display the name of border associated to a world  
&ensp;reload - To reload a border file  
&ensp;remove - To remove borders from a game

Finally, there are scoreboard entries in order to display border characteristics on player screen. They can be found in the <code>border.entries</code> package. And the class <code>BorderTimeLineObserver</code> is responsible to move a border from its initial diameter to its final diameter with a count down and send messages translated in the player's language.