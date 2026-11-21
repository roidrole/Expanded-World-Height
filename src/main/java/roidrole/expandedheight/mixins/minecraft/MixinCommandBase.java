package roidrole.expandedheight.mixins.minecraft;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CommandBase.class)
public abstract class MixinCommandBase {
	//Assumptions:
	// - all ICommandSender follow the nonnull contract
	// - Most things that need to parse a blockpos are worlds
	// - It is safer to parse with the current world than otherwise (might be false since default world is overworld...)
	@ModifyConstant(
		method = "parseBlockPos",
		constant = @Constant(
			intValue = 256
		)
	)
	private static int actualHeight(int constant, @Local(argsOnly = true) ICommandSender sender){
		return sender.getEntityWorld().getHeight();
	}
}
