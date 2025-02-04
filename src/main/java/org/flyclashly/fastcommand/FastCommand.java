package org.flyclashly.fastcommand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.flyclashly.fastcommand.commands.TpaCommand;
import org.flyclashly.fastcommand.commands.TpaResponseCommand;

public class FastCommand implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            TpaCommand.register(dispatcher);
            TpaResponseCommand.register(dispatcher);
        });
    }
}