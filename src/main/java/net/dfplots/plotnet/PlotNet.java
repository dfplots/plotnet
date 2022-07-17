package net.dfplots.plotnet;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.OptionalInt;

public class PlotNet {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("plotnet");

	private static boolean sent = false;
	private static Integer id = null;

	public static OptionalInt getID(String ip) {
		String[] parts = ip.split("\\.");
		if (parts.length == 3 && parts[1].equals("mcdiamondfire") && parts[2].equals("net")) {
			try {
				return OptionalInt.of(Integer.parseInt(parts[0])); // for ie node1.mcdiamondfire.net
			} catch (NumberFormatException ignored) {
				return OptionalInt.empty();
			}
		}
		return OptionalInt.empty();
	}

	public static void logConnection(String ip) {
		getID(ip).ifPresent(foundID -> id = foundID);
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

	public static int guessNode(int id) {
		if (id <= 11330) return 1;

		if (id <= 22639) return 2;

		if (id <= 23939) return 1;

		if (id <= 24939) return 2;

		if (id <= 26303) return 1;

		if (id <= 27579) return 2;

		if (id <= 41738) return 3;

		if (id <= 41920) return 2;

		if (id <= 42250) return 1;

		if (id <= 42346) return 2;

		if (id < 50000) return 4;

		if (id < 60000) return 5;

		if (id < 70000) return 6;

		if (id < 80000) return 7;

		// should never reach here until they add node8!
		return 1;
	}
}
