/**
 There are a few things that I need to get done for this machine, but at this point I am a bit burned out with it.
 So, I will probably come back to it at some point to finish it up, and this is for notes on what I plan to do
**/

/**
 This is what I have currently:
    The main block has a tileEntity, Container, & Screen.
    The Screen has buttons and a really neat "Terminal" that is meant to display actions like a computer terminal would
    The Tile Entity has the majority of slot functionality because that's how the tutorial I watched did it
    The Container doesn't do much other than add the slots defined in the tile entity and player inventory.

    Finally, a block I call a "Shape Filler" which will probably be a common thing for other machines large than 1 block.
    They have an int value that determines the voxel shape each instance uses, and that same value has been used for
    right click-functions that tie into the block class of the "Core block" that do things like add a specified item into the inventory and open the screen.
**/

/**
 Things that need to be worked on:
    The screen needs to be able to tie into the container and tile entity for things like displaying terminal messages
 based on inputting items into slots and buttons performing actions like setting off the mix animations and such.

    The actual texture needs to fit to the new two slot system for input and output (will make the logic stuff easier to understand)
        A slot for inputting paint cans and dyes, and a slot that will output the finished product after.

    If I can ever get the communication between container, tile, and screen down I can do something along the lines of
 storing an int value for the 3 rgb values, and whenever a can is mixed it will reduce those values based on the mixed rgb
 value which can be refilled by inserting dyes or something.

    Of course, the machine model needs to be finished

    An actual paint can block and brush item that applies color values to the blocks.

    Making the "Shape Filler" block's onRemove method work properly (not taking more blocks than just the machine with them)

    And many more things, basically just work on it until it's actually good.
 **/
package io.github.firetamer.dbb.modules.machines.paint_mixer;