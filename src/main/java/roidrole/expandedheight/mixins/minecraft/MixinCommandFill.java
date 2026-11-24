package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.command.CommandFill;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CommandFill.class)
public abstract class MixinCommandFill {
	@ModifyConstant(
		method = "execute",
		constant = @Constant(intValue = 256)
	)
	private int useWorldHeight(int original, MinecraftServer server, ICommandSender sender){
		return sender.getEntityWorld().getHeight();
	}
}
