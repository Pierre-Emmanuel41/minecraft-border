# Presentation

### 1) Minecraft features

As a minecraft player, there is one functionality proposed by this plugin : The command "./border". This command allows server operators to create border configuration files according to their needs. This command proposes the following arguments :

<code>border</code> (minecraft command)</br>
&ensp;<code>center</code> - To set the center of a border.</br>
&ensp;<code>delete</code> - To delete a border file.</br>
&ensp;<code>details</code> - To display border's characteristics.</br>
&ensp;<code>finalDiameter</code> - To set the final diameter of a border.</br>
&ensp;<code>help</code> - To get help about the other argument.</br>
&ensp;<code>initialDiameter</code> - To set the initial diameter of a border.</br>
&ensp;<code>list</code> - To display border file names.</br>
&ensp;<code>load</code> - To load a border to configure.</br>
&ensp;<code>moveTime</code> - To set the time it takes to move the border from its initial diameter to its final diameter.</br>
&ensp;<code>new</code> - To create a new border to configure.</br>
&ensp;<code>rename</code> - To rename a border.</br>
&ensp;<code>save</code> - To save characteristics of a border.</br>
&ensp;<code>speed</code> - To set the speed of a border.</br>
&ensp;<code>startTime</code> - To set the time after which the border moves from its initial diameter to its final diameter.</br>
&ensp;<code>world</code> - To define the world in which the border is applied.</br>

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

<code>borders</code> - To add/remove/reload a border.</br>
&ensp;<code>add</code> - To add borders to a game.</br>
&ensp;<code>details</code> - To display characteristics of one or several borders for a game.</br>
&ensp;<code>list</code> - To display the name of border associated to a world.</br>
&ensp;<code>reload</code> - To reload a border file.</br>
&ensp;<code>remove</code> - To remove borders from a game.</br>

Finally, there are scoreboard entries in order to display border characteristics on player screen. They can be found in the <code>border.entries</code> package. And the class <code>BorderTimeLineObserver</code> is responsible to move a border from its initial diameter to its final diameter with a count down and send messages translated in the player's language.