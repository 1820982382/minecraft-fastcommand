package org.flyclashly.fastcommand.commands;

import com.mojang.brigadier.CommandDispatcher;
import org.flyclashly.fastcommand.TpaManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class TpaResponseCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        // /y 接受请求
        dispatcher.register(literal("y")
                .executes(context -> {
                    ServerPlayerEntity target = context.getSource().getPlayer();
                    TpaManager.TpaRequest request = TpaManager.getRequest(target);

                    if (request == null) {
                        target.sendMessage(Text.literal("§c你没有待处理的传送请求。"));
                        return 0;
                    }

                    request.sender.teleport(target.getServerWorld(), target.getX(), target.getY(), target.getZ(), target.getYaw(), target.getPitch());
                    request.sender.sendMessage(Text.literal("§a传送成功！"));
                    target.sendMessage(Text.literal("§a已接受传送请求。"));
                    TpaManager.removeRequest(target);
                    return 1;
                })
        );

        // /n 拒绝请求
        dispatcher.register(literal("n")
                .executes(context -> {
                    ServerPlayerEntity target = context.getSource().getPlayer();
                    TpaManager.TpaRequest request = TpaManager.getRequest(target);

                    if (request == null) {
                        target.sendMessage(Text.literal("§c你没有待处理的传送请求。"));
                        return 0;
                    }

                    request.sender.sendMessage(Text.literal("§c你的传送请求被拒绝。"));
                    target.sendMessage(Text.literal("§a已拒绝传送请求。"));
                    TpaManager.removeRequest(target);
                    return 1;
                })
        );
    }
}