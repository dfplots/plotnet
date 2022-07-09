package net.dfplots.plotnet.mixin;

import net.dfplots.plotnet.PlotNet;
import net.minecraft.client.network.MultiplayerServerListPinger;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

@Mixin(MultiplayerServerListPinger.class)
// Mixins HAVE to be written in java due to constraints in the mixin system.
public class PingMixin {

    // store the original addresses before they are overwritten to allow for correct ping
    private static final Map<ServerInfo, String> originalAddress;

    static {
        originalAddress = new HashMap<>();
    }

    @Inject(at = @At("HEAD"), method = "add")
    private void pingHead(final ServerInfo entry, final Runnable saver, CallbackInfo callbackInfo) {
        if (PlotNet.getID(entry.address).isPresent()) {
            originalAddress.put(entry, entry.address);
            entry.address = "mcdiamondfire.net";
        }
    }

    @Inject(at = @At("TAIL"), method = "add")
    private void pingTail(final ServerInfo entry, final Runnable saver, CallbackInfo callbackInfo) {
        if (originalAddress.containsKey(entry)) {
            entry.address = originalAddress.remove(entry);
            System.out.println(entry.address);
        }
    }
}
