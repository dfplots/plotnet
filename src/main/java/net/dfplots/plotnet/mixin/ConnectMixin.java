package net.dfplots.plotnet.mixin;

import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dfplots.plotnet.PlotNetKt;

@Mixin(MultiplayerScreen.class)
// Mixins HAVE to be written in java due to constraints in the mixin system.
public class ConnectMixin {
    @Inject(at = @At("HEAD"), method = "connect(Lnet/minecraft/client/network/ServerInfo;)V")
    private void init(ServerInfo serverInfo, CallbackInfo info) {
        PlotNetKt.logConnection(serverInfo.address);
    }
}
