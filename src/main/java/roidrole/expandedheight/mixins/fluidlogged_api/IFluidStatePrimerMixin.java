package roidrole.expandedheight.mixins.fluidlogged_api;

import git.jbredwards.fluidlogged_api.api.world.IFluidStatePrimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(IFluidStatePrimer.class)
public abstract class IFluidStatePrimerMixin {
	@ModifyConstant(
		method = "<init>",
		constant = @Constant(intValue = 65536),
		remap = false
	)
	private int fixPrimerSize(int constant){
		return ConfigManager.primerVolume;
	}
}
