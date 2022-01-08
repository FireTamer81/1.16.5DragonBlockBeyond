package io.github.FireTamer.modules.strongBlockFeature;

/**
 * Currently these blocks cause a lot of lag. I have mitigated some of it by having the "hasTileEntity" method be based on a blockstate property.
 * So now the tileEntity can be "turned on or off". I just need to figure out how to store the health value without NBT value in case I want to phase out tileEntities.
 *
 * Other than that, I need to setup the side culling so all sides are not always rendered. Probably another blockstate property, maybe an enum one.
 *
 * Right now all of the blockstate properties are controlled by a use method in the block class, without any real triggers other than a right-click.
 * I need to set that stuff up to be on another system.
 *
 * I want to add a block or item with a nice GUI to either make and customize the blocks, or just customize them. (Probably the former)
 * However, this will probably wait until I figure out which library I want to use and how to use it for awesome GUIs.
 *
 * Block Color is now determined by the "BlockColors" class and uses a hex value.
 * Right now it is based on an Enum BLockStateProperty and switch statement, but I might be able to make an integer value that is stored in a
 * blockProperty (less lag) that can be changed by an item holding NBT data for a new int to have infinite colors.
 *
 * Make an block with a GUI to choose either 1 of the 16 colors (or a completely custom one) that can take an item and press a button to add the NBT value like a
 * paint mixer or something.
 */