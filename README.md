# emuhub
## About
Emuhub is a little project of mine to replace the program Emulationstation.

As I use different emulators depending on what or how I want to play I wanted a simple frontend which enabled me to choose which kind of emulator I want to use.

## Setup
If you are using Windows as your operating system you can use the archive on the releases page. Unzip it into a new folder called 'emuhub' preferably in you homepath/userdir.

You can also get the IDEA module directly from this repository.

To be able to use a gamepad in Emuhub you need to add the jinput dlls (which are available on github) into the folder 'natives'. 

### Configuring systems
systems.json is used to to get the informations on which systems are supposedto be displayed, and which commands are supported for each system.

If you start the program without this file it will be generated automatically with an example inside.

The release archive contains a systems.json with some systems configured.

The contents:
- systems: is an array that contains all the systems you want Emuhub to display.
- systems - id: is the system id.
- systems - name: is the humanly readable name of the system.
- systems - romFolderPath: contains the path to the roms for the system. %HOMEPATH% can be used here.
- systems - fileExtensions: contains the fileextensions which are supported. The contents of this array are used when the romfolder is being scanned, only files with an extension which is contained in here are being listed in Emuhub.
- systems - commands: is an array which contains your commands to start your emulators.
- systems - commands - name: is the humanly readable name of the command. It should contain the emulator name. This is displayed in Emuhub.
- systems - commands - command: is the command line command.
- systems - commands - arguments: is an array that may contain command line arguments. The romfilepath is being added by Emuhub.

### Configuring games:
gamesinfos.json conatins additional information for your games. If you have no problem with your games being displayed with the romname then you don't need to configure this file.

If you want the displayed game name to be something different you can add your game here to give it an alternative name.

The contents:
- gamesInfos: is an array which contains additional information for your games.
- gamesInfos - name: is the humanly readable name of the game. This is being displayed in Emuhub.
- gamesInfos - romName: is the name of your rom (as contained in you romfolder).

### Adding images
When you first run Emuhub images are being created in the folder 'images'.

You can replace those with images of your own.

Recommended resolutions:
- system selection images: 500x500
- system header images: 1920x200
- game images: 500x500
- command images 500x500

### Using Emuhub
After starting Emuhub a splash screen is being displayed.

Next is the systems screen. Your configured systems are displayed here. You can switch through your systems with your keyboard or your gamepad. Select a system to go to the games screen.

Gamepad controls:
- left or A: go to previous system
- right or D: go to next system
- Enter or Space: select system
- ESC or Backspace: close Emuhub

Keyboard controls:
- left (dpad): go to previous system
- right (dpad): go to next system
- A: select system
- B: close Emuhub

Next is the games screen. Your games are displayed here. You can switch through your games with your keyboard or your gamepad. Select a game to go to the commands screen.

Gamepad controls:
- left or A: go to previous game
- page up: skip 10 games upwards
- right or D: go to next game
- page down: skip 10 games downwards
- Enter or Space: select game
- ESC or Backspace: go back to the systems screen

Keyboard controls:
- left (dpad): go to previous game
- up: skip 10 games upwards
- right (dpad): go to next game
- down: skip 10 games downwards
- A: select game
- B: go back to the systems screen

Next is the commands screen. Your emulators are displayed here. You can switch through your emulators with your keyboard or your gamepad. Select an emulator to go to start it's command.

Gamepad controls:
- left or A: go to previous game
- right or D: go to next game
- Enter or Space: select game
- ESC or Backspace: go back to the systems screen

Keyboard controls:
- left (dpad): go to previous emulator
- right (dpad): go to next emulator
- A: start emulator
- B: go back to the games screen
