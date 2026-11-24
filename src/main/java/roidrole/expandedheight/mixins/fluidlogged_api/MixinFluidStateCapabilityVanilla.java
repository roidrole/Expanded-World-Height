package roidrole.expandedheight.mixins.fluidlogged_api;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import git.jbredwards.fluidlogged_api.mod.common.capability.FluidStateCapabilityVanilla;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FluidStateCapabilityVanilla.class)
public abstract class MixinFluidStateCapabilityVanilla {
	@ModifyReturnValue(
		method = "serializeY",
		at = @At("TAIL"),
		remap = false
	)
	private int maskVanillaHeight(int original){
		//Add a vanilla mask to avoid overflow of higher coordinates
		return original & 0xFF00;
	}
}
