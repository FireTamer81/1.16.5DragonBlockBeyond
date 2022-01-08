package io.github.firetamer.dbb.init;

import net.minecraftforge.fml.loading.FMLEnvironment;

public class CommandInit {

	public static boolean isDevEnvironment() {
        return !FMLEnvironment.production;
    }

    public static class PermissionLevels {

        public static final int ALL_PLAYERS = 0;
        public static final int NO_COMMANDS = 1;
        public static final int GIVE_CLEAR = 2;
        public static final int BAN_KICK_OP = 3;
        public static final int STOP_THE_SERVER = 4;

    }
}
