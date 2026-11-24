package roidrole.expandedheight.mixins.fluidlogged_api;

import git.jbredwards.fluidlogged_api.api.capability.IFluidStateCapability;
import git.jbredwards.fluidlogged_api.api.capability.IFluidStateContainer;
import git.jbredwards.fluidlogged_api.mod.common.message.SMessageSyncFluidStates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SMessageSyncFluidStates.class)
public class MixinSMessageSyncFluidStates {
	@Redirect(
		method = "<init>(IIILgit/jbredwards/fluidlogged_api/api/capability/IFluidStateCapability;)V",
		at = @At(
			value = "INVOKE",
			target = "Lgit/jbredwards/fluidlogged_api/api/capability/IFluidStateCapability;getContainer(I)Lgit/jbredwards/fluidlogged_api/api/capability/IFluidStateContainer;"
		),
		remap = false
	)
	private IFluidStateContainer useParameterHeight(IFluidStateCapability instance, int i, int chunkXIn, int indexYIn){
		return instance.getContainer(indexYIn);
	}
}
