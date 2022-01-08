package io.github.FireTamer.common.configs;


/**
 * For now this class is just a poorly done example of how MatyLib wants configs done
 * It has multiple errors, probably due to the example using parchment mappings
 * Figure it out later
 */

public class ClientConfig {} /**extends BaseTOMLConfig {

    public static final TOMLConfigBuilder BUILDER = new TOMLConfigBuilder();
    public static ForgeConfigSpec spec;

    public static ForgeConfigSpec.ConfigValue<Boolean> disableExperimentalSettingsScreen;

    //@formatter:off
    public static void register() {
        BUILDER.push("machinaConfig");

        disableExperimentalSettingsScreen = config("If the experimentals settings screen should be disabled.",
                "disableExperimentalSettingsScreen", true);

        DBB_Main.ANNOTATION_PROCESSOR.getModules().forEach((id, module) ->
                module.initConfig(ModConfig.Type.CLIENT, BUILDER)
        );


        BUILDER.pop();
        spec = BUILDER.build();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.spec, DBB_Main.MOD_ID + "/client.toml");
    }

    private static <T> ForgeConfigSpec.ConfigValue<T> config(String comment, String path, T defaultValue) {
        return BUILDER.config(comment, path, defaultValue, true);
    }

}**/
