package net.dfplots.plotnet.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dfplots.plotnet.PlotNetKt;

@Mixin(ClientPlayerEntity.class)
// Mixins HAVE to be written in java due to constraints in the mixin system.
public class TickMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        PlotNetKt.gameTick();
    }
}
