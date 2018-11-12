# froggerhop-game
This application written in Java is a game similar to popular mobile game 'Crossy Road' in which your character is attempting to cross busy roads/rivers in order to reach the other side.

Create a project for this application, and call it FroggerHop. We’re going to set up the Slick
library for game development, which uses LWJGL (Light-Weight Java Gaming Library) for its
graphics and input. These instructions are for systems using Eclipse, but the steps should be
similar for other IDEs.

(1) Download the 'Slick.zip' file. Extract the archive and store the Slick 2D libraries somewhere on your system.

(2) Create a new folder named lib in your project. To import the libraries (make them available
    in your project), right click the folder and press “Import”. Select “File System” from the
    dialogue, and browse to the folder you downloaded earlier.
    From here, select all of the .jar files, and .dll files (Windows), .dylib files (OSX), or .so
    files (Linux), and click “Finish”.

(3) Right click each of the .jar files in the Project Explorer and press “Build Path → Add to
    build path”. You should see a new icon appear in the Project Explorer called “Referenced
    Libraries”.
  
(4) Right click 'lwjgl.jar' from the Referenced Libraries folder of your project. Press “Properties
    → Native Library → External Folder”. Navigate to your lib folder, and press OK, then
    “Apply and Close”.
  
(5) You should now have successfully imported all the libraries for Slick.

Now, getting the Slick application to work...

(1) Download the 'src.zip' file. Extract the archive and add the files to your project's src directory.

(2) Download all files within the 'asset' folder. Create a new folder called "assets" in your project.
    Import all the downloaded files to this assets folder.
    
(3) Run the program and enjoy the game!

Please let me know if you have any issues setting the application up, so I can alter the instructions.
