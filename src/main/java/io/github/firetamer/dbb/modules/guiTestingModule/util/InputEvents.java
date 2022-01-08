package io.github.firetamer.dbb.modules.guiTestingModule.util;

import net.minecraft.client.Minecraft;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.modules.guiTestingModule.GuiModule;
import io.github.firetamer.dbb.modules.guiTestingModule.playerScreen.PlayerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = DragonBlockBeyond.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null) { return; }

        onInput(mc, event.getKey(), event.getAction());
    }

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null) { return; }

        onInput(mc, event.getButton(), event.getAction());
    }

    private static void onInput(Minecraft mc, int key, int action) {
        if (mc.screen == null && GuiModule.PLAYER_GUI_KEY.isDown()) {
            mc.setScreen(new PlayerScreen());
        }
    }

}
