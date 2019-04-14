# Ping-Pong-Project

Overview of goals and background:

1. Currently creating main game engine
	1. Done:
		-Graphics processing
		-User input
		-Tile system to make levels
		-Font system
	2. Unfinished:
		-Player character
		-Animation
		-Level Loading
		-Collision/Physics
		-Multiplayer
		-Menus
		
2. How it works
	1. Packages:
			1. game:
				1. Game.java
					-Main game loop
					-Graphics engine init
					-Render function
					-Main function
				2. InputHandler.java
					-Listens for keypresses
					-Can assign buttons (currently WASD and Arrow Keys)
					-Add functionality to presses
			2. gfx:
				1. Colors.java
					-Sets limit on color use, 8 bit
					-Gets RGB values
				2. Font.java
					-Grabs bottom 2 rows from sprite_sheet.png
					-Assigns specific tiles to text
				3. Screen.java
					-Renders game screen
					-Handles color info from sprite_sheet.png
					-Graphics processing
				4. SpriteSheet.java
					-Uses sprite_sheet.png like minecraft uses textures
					-Sets bounds (256x256)
			3. level:
				1. Level.java
					-Generates level from selected tiles
					-Use this to make maps/levels
			4. level.tiles:
				1. BasicTile.java
					-Code outlining a basic tile
				2. Tile.java
					-Abstract class defining tile types (solid, liquid, etc)
			5. res:
				1. Resource folder, contains sprite_sheet.java
				
This framework/engine was originally intended to be used to make
multiplayer RPGs, but we can alter it to be used for multiplayer
pong game.
