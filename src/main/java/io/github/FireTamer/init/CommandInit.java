package io.github.FireTamer.init;

public class CommandInit {


    /**
     * Another example for MatyLib (or just ordinary) commands.
     * For now all of this will be commented out as well as the register stuff in the main class
     */


    /**
    private static final ArrayList<BaseCommand> commands = new ArrayList<>();

    public static void registerCommands(final RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();

        commands.add(new AddTraitCommand(PermissionLevels.GIVE_CLEAR, true));
        commands.add(new ListTraitsCommand(PermissionLevels.GIVE_CLEAR, true));
        commands.add(new RemoveTraitCommand(PermissionLevels.GIVE_CLEAR, true));

        commands.add(new DebugCommand(4, isDevEnvironment()));
        commands.add(new GoToPlanetCommand(4, true));

        commands.forEach(command -> {
            if (command.isEnabled()) {
                LiteralArgumentBuilder<CommandSource> builder = literal(command.getName());
                builder.requires(sender -> sender.hasPermission(command.getPermissionLevel()));
                command.build(builder);
                if (command instanceof PlanetTraitsCommand) {
                    dispatcher.register(literal(Machina.MOD_ID).then(literal("planet_traits").then(builder)));
                } else {
                    dispatcher.register(literal(Machina.MOD_ID).then(builder));
                }
            }
        });

    }

    private static boolean isDevEnvironment() {
        return !FMLEnvironment.production;
    }

    public static class PermissionLevels {

        public static final int ALL_PLAYERS = 0;
        public static final int NO_COMMANDS = 1;
        public static final int GIVE_CLEAR = 2;
        public static final int BAN_KICK_OP = 3;
        public static final int STOP_THE_SERVER = 4;

    }
     **/
}
