package io.github.firetamer.dbb.modules.gui_module;

import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.keybind.BaseKeyBinding;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;

import io.github.firetamer.dbb.modules.gui_module.test_screen.TestScreen;
import net.minecraft.client.Minecraft;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.gui_module.player_screen.PlayerScreen;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Module(id = @RL(modid = DragonBlockBeyond.MOD_ID, path = "gui_testing_module"))
public class GuiModule extends ModuleHelper implements IModule {

    /*
    * These will be used to open the various GUIs I will be making in this module
    * KeyBinds
    */

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        registerKeyBinds(event);

        //This here adds textures to the player HUD.
        //From here it's just getting some sort of statistic like health and rendering a ar or something through this.
        //Also, thanks Maty
        /**
        OverlayRegistry.registerOverlayBottom("hi", (gui, matrixStack, partialTicks, width, height) -> {
            Minecraft.getInstance().getTextureManager().bind(PlayerScreen.GEAR_LOCATION);
            gui.blit(matrixStack, 0, 0, 0, 0, 97, 95);
        });
        **/
    }

    //public final static BaseKeyBinding PLAYER_GUI_KEY = create("example_key", KeyEvent.VK_Z, mc -> mc.setScreen(new PlayerScreen()), false);
    public final static BaseKeyBinding PLAYER_GUI_KEY = create("example_key", KeyEvent.VK_Z, mc -> mc.setScreen(new TestScreen()), false);

    public void registerKeyBinds(final FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(PLAYER_GUI_KEY);
    }

    private static BaseKeyBinding create(String name, int key, Consumer<Minecraft> toRun, boolean checkEveryTick) {
        return new BaseKeyBinding("key." + DragonBlockBeyond.MOD_ID + "." + name, key, "key.category." + DragonBlockBeyond.MOD_ID, mc -> {
            if (mc.screen == null && mc.level != null) {
                toRun.accept(mc);
            }
        }, checkEveryTick);
    }
}
