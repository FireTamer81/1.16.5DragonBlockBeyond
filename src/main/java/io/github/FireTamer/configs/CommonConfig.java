package io.github.FireTamer.configs;


/**
 * For now this class is just a poorly done example of how MatyLib wants configs done
 * It has multiple errors, probably due to the example using parchment mappings
 * Figure it out later
 */

public class CommonConfig {} /**extends BaseTOMLConfig {

    public static final TOMLConfigBuilder BUILDER = new TOMLConfigBuilder();
    public static ForgeConfigSpec spec;

    public static ForgeConfigSpec.ConfigValue<Integer> minPlanets;
    public static ForgeConfigSpec.ConfigValue<Integer> maxPlanets;

    //@formatter:off
    public static void register() {
        BUILDER.push("machinaConfig");

        minPlanets = config("Minimum planets in the starchart.", "minimumPlanets", 5);
        maxPlanets = config("Maximum planets in the starchart.", "maximumPlanets", 10);

        Machina.ANNOTATION_PROCESSOR.getModules().forEach((id, module) ->
                module.initConfig(Type.COMMON, BUILDER)
        );

        BUILDER.pop();
        spec = BUILDER.build();

        ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.spec, MACHINA + "/common.toml");
    }

    private static <T> ConfigValue<T> config(String comment, String path, T defaultValue) {
        return BUILDER.config(comment, path, defaultValue, true);
    }
}**/
