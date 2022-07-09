package net.dfplots.plotnet

import net.minecraft.client.MinecraftClient

// For support join https://discord.gg/v6v4pMv

var sent = false

@Suppress("unused")
fun logConnection(ip: String) {
    println(ip)
}
fun gameTick() {
    val player = MinecraftClient.getInstance()?.player;

    if (player != null && !sent) {
        player.sendCommand("help")

        sent = true
    }
}

