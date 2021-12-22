package io.github.FireTamer.modules.guiTestingModule;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import io.github.FireTamer.DBB_Main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@Module(id = @RL(modid = DBB_Main.MOD_ID, path = "gui_testing_module"))
public class guiTestingModule extends ModuleHelper implements IModule {






    /******************************************************************************************************************/
    //These will be used to open the various GUIs I will be making in this module
    //KeyBinds
    /******************************************************************************************************************/

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        registerKeyBinds(event);
    }

    public static KeyBinding exampleKey;

    public static void registerKeyBinds(final FMLClientSetupEvent event) {
        exampleKey = create("example_key", KeyEvent.VK_DEAD_TILDE);

        ClientRegistry.registerKeyBinding(exampleKey);
    }

    private static KeyBinding create(String name, int key) {
        return new KeyBinding("key." + DBB_Main.MOD_ID + "." + name, key, "key.category." + DBB_Main.MOD_ID);
    }
}
