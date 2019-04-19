# Ping-Pong-Project

RUNNING THE PONG.BAT FILE:
1. Press the windows key + R at the same time
2. Browse and select the pong.bat file downloaded.
3. Press open which will paste the full bat file path into the run box.
4. Press ok, and the bat file will open in a terminal and run.






HOW TO RUN: Run the Game.java (in game package), then click on game screen to activate movement



Overview of goals and background:

1. Currently creating main game engine

	1. Done:
		-Graphics processing
		-User input
		-Tile system to make levels
		-Font system
		-Player character
		-Scalable sizes
		-Animated entities
		-Collision (needs tweaking)
		-Level Loading
		-Importable levels from png
		-Animated tiles
		-Username displayed
	2. Unfinished:
		-Physics
		-Multiplayer
		-Menus
		
2. How it works (packages)

			1. game:
				Game.java
					-Main game loop
					-Graphics engine init
					-Render function
					-Main function
				InputHandler.java
					-Listens for keypresses
					-Can assign buttons (currently WASD and Arrow Keys)
					-Add functionality to presses
			2. gfx:
				Colors.java
					-Sets limit on color use, 8 bit
					-Gets RGB values
				Font.java
					-Grabs bottom 2 rows from sprite_sheet.png
					-Assigns specific tiles to text
				Screen.java
					-Renders game screen
					-Handles color info from sprite_sheet.png
					-Graphics processing
				SpriteSheet.java
					-Uses sprite_sheet.png like minecraft uses textures
					-Sets bounds (256x256)
			3. level:
				Level.java
					-Loads png from res/levels
					-Converts png to array of nums from pixels
					-Color of pixel determines type of tile
					-Builds map
					-Use this to make maps/levels
			4. level.tiles:
				BasicTile.java
					-Code outlining a basic tile
				Tile.java
					-Abstract class defining tile types (solid, liquid, etc)
				BasicSolidTile.java
					-Abstract class defining what a solid tile is
			5. entities:
				Entity.java
					-
				Mob.java
					-
				Player.java
					-	
			6. res:
					-Contains sprite_sheet.png
			7. res.Levels
					-Contains levels folder with maps
				
				
This framework/engine was originally intended to be used to make
multiplayer RPGs, but we can alter it to be used for multiplayer
pong game.
