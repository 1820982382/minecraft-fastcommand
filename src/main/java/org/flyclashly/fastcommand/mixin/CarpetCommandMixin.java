package org.flyclashly.fastcommand.mixin;

import carpet.utils.CommandHelper;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommandHelper.class)
public class CarpetCommandMixin {
    @Redirect(
            method = "canUseCommand",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ServerCommandSource;hasPermissionLevel(I)Z")
    )
    private static boolean overridePermissionCheck(ServerCommandSource source, int level) {
        // 将权限等级要求从 2（默认OP）改为 0（所有玩家）
        return source.hasPermissionLevel(0);
    }
}
