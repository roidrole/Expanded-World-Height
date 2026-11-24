package roidrole.expandedheight.mixins.celeritas;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.taumc.celeritas.impl.compat.fluidlogged.FluidStateStorage;

@Mixin(FluidStateStorage.class)
public abstract class FluidStateStorageMixin {
	@ModifyVariable(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lorg/apache/commons/lang3/mutable/MutableInt;<init>()V"
		),
		remap = false,
		argsOnly = true,
		index = 2
	)
	private int maksY(int yIn){
		return yIn & 0xFF;
	}
}
