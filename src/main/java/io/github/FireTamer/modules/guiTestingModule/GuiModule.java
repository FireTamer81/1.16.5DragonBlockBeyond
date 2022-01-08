package io.github.FireTamer.modules.guiTestingModule;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.keybind.BaseKeyBinding;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.guiTestingModule.playerScreen.PlayerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;
import java.util.function.Consumer;

@Module(id = @RL(modid = DBB_Main.MOD_ID, path = "gui_testing_module"))
public class GuiModule extends ModuleHelper implements IModule {

    /*
    * These will be used to open the various GUIs I will be making in this module
    * KeyBinds
    */

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        registerKeyBinds(event);
    }

    public final static BaseKeyBinding PLAYER_GUI_KEY = create("example_key", KeyEvent.VK_Z, mc -> mc.setScreen(new PlayerScreen()), false);

    public void registerKeyBinds(final FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(PLAYER_GUI_KEY);
    }

    private static BaseKeyBinding create(String name, int key, Consumer<Minecraft> toRun, boolean checkEveryTick) {
        return new BaseKeyBinding("key." + DBB_Main.MOD_ID + "." + name, key, "key.category." + DBB_Main.MOD_ID, mc -> {
            if (mc.screen == null && mc.level != null) {
                toRun.accept(mc);
            }
        }, checkEveryTick);
    }
}
