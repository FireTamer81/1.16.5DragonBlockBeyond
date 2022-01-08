package io.github.firetamer.dbb.mixin.client;

import com.mojang.serialization.Lifecycle;
import io.github.firetamer.dbb.DragonBlockBeyond;
import net.minecraft.world.storage.ServerWorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorldInfo.class)
public class ServerWorldInfoMixin {

    @Inject(method = "worldGenSettingsLifecycle()Lcom/mojang/serialization/Lifecycle;", at = @At("HEAD"), cancellable = true)
    private void dbb$noExperimentalScreen(CallbackInfoReturnable<Lifecycle> callback) {
        DragonBlockBeyond.LOGGER.info(
                "Dear Mojang, we know that world gen is experimental and it's not supported, because we (mods) are not supported. We will always love you and you will never love us. (now fuck your experimental screen)");
        callback.setReturnValue(Lifecycle.stable());
    }
}
