package roidrole.expandedheight.mixins.minecraft;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.command.CommandFill;
import net.minecraft.command.ICommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CommandFill.class)
public abstract class MixinCommandFill {
	@ModifyConstant(
		method = "execute",
		constant = @Constant(intValue = 256)
	)
	private int useWorldHeight(int original, @Local(argsOnly = true) ICommandSender sender){
		return sender.getEntityWorld().getHeight();
	}
}
