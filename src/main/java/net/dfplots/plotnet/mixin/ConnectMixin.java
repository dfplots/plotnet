package net.dfplots.plotnet.mixin;

import net.dfplots.plotnet.PlotNet;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.OptionalInt;

@Mixin(MultiplayerScreen.class)
// Mixins HAVE to be written in java due to constraints in the mixin system.
public class ConnectMixin {
    @Inject(at = @At("HEAD"), method = "connect(Lnet/minecraft/client/network/ServerInfo;)V")
    private void connect(ServerInfo serverInfo, CallbackInfo info) {
        PlotNet.logConnection(serverInfo.address);

        OptionalInt id = PlotNet.getID(serverInfo.address);
        if (id.isPresent()) {
            serverInfo.address = String.format("node%d.mcdiamondfire.net", PlotNet.guessNode(id.getAsInt()));
        }
    }
}
