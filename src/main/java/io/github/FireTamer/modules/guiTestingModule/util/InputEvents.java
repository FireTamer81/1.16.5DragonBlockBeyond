package io.github.FireTamer.modules.guiTestingModule.util;

import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.guiTestingModule.GuiModule;
import io.github.FireTamer.modules.guiTestingModule.objects.playerScreen.PlayerScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = DBB_Main.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
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
        if (mc.screen == null && GuiModule.exampleKey.isDown()) {
            mc.setScreen(new PlayerScreen());
        }
    }

}
