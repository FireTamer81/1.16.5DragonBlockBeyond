package io.github.firetamer.dbb.common.configs;


/**
 * For now this class is just a poorly done example of how MatyLib wants configs done
 * It has multiple errors, probably due to the example using parchment mappings
 * Figure it out later
 */

public class ServerConfig {} /**extends BaseTOMLConfig {

    public static final TOMLConfigBuilder BUILDER = new TOMLConfigBuilder();
    public static ForgeConfigSpec spec;

    //@formatter:off
    public static void register() {
        BUILDER.push("machinaConfig");

        Machina.ANNOTATION_PROCESSOR.getModules().forEach((id, module) ->
                module.initConfig(Type.SERVER, BUILDER)
        );

        BUILDER.pop();
        spec = BUILDER.build();

        ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.spec, "machina-server.toml");
    }

    @SuppressWarnings("unused")
    private static <T> ConfigValue<T> config(String comment, String path, T defaultValue) {
        return BUILDER.config(comment, path, defaultValue, true);
    }

}**/
