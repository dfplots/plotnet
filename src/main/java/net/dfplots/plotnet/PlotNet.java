package net.dfplots.plotnet;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PlotNet {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("plotnet");

	private static boolean sent = false;
	private static Integer id = null;

	public static Optional<Integer> getID(String ip) {
		String[] parts = ip.split("\\.");
		if (parts.length == 3 && parts[1].equals("mcdiamondfire") && parts[2].equals("net")) {
			try {
				return Optional.of(Integer.parseInt(parts[0])); // for ie node1.mcdiamondfire.net
			} catch (NumberFormatException ignored) {
				return Optional.empty();
			}
		}
		return Optional.empty();
	}

	public static void logConnection(String ip) {
		Optional<Integer> maybeID = getID(ip);
		maybeID.ifPresent(foundID -> id = foundID);
		sent = false;
	}

	public static void gameTick() {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		if (player != null && !sent) {
			sent = true;

			if (id != null) {
				player.sendCommand(String.format("join %d", id));
			}
		}
	}
}
