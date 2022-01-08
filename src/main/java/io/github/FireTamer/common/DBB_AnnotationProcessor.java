package io.github.FireTamer.common;

import net.minecraftforge.eventbus.api.IEventBus;

public class DBB_AnnotationProcessor extends com.matyrobbrt.lib.registry.annotation.AnnotationProcessor {
    /**
     * Creates a new {@link AnnotationProcessor} which will be used in order to
     * process annotations. It is recommended to store this statically somewhere.
     *
     * @param modid the mod id to process the annotations for
     */
    public DBB_AnnotationProcessor(String modid) {
        super(modid);
    }


    @Override
    public void register(IEventBus modBus) {
        super.register(modBus);
    }
}
