package org.flyclashly.fastcommand.commands;


import com.mojang.brigadier.CommandDispatcher;
import org.flyclashly.fastcommand.TpaManager;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class TpaCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("tpa")
                .then(argument("target", EntityArgumentType.player())
                        .executes(context -> {
                            ServerPlayerEntity sender = context.getSource().getPlayer();
                            ServerPlayerEntity target = EntityArgumentType.getPlayer(context, "target");

                            TpaManager.addRequest(sender, target);
                            target.sendMessage(Text.literal("§a" + sender.getName().getString() + " 请求传送到你身边。输入 /y 接受或 /n 拒绝。"));
                            sender.sendMessage(Text.literal("§a已向 " + target.getName().getString() + " 发送传送请求。"));

                            return 1;
                        }))
        );
    }
}