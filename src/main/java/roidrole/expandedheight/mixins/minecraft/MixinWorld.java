package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(World.class)
public abstract class MixinWorld {
	@Shadow
	public abstract int getHeight();

	@ModifyConstant(
		method = {
			"isAreaLoaded(IIIIIIZ)Z",
			"isOutsideBuildHeight",
			"getLight(Lnet/minecraft/util/math/BlockPos;)I",
			"getLight(Lnet/minecraft/util/math/BlockPos;Z)I"
		},
		constant = @Constant(intValue = 256)
	)
	private int useActualHeight(int original){
		return this.getHeight();
	}

	@ModifyConstant(
		method = {
			"canBlockFreezeBody",
			"canSnowAtBody"
		},
		constant = @Constant(intValue = 256),
		remap = false
	)
	private int useActualHeightNoRemap(int original){
		return this.getHeight();
	}

	@ModifyConstant(
		method = {
			"getLight(Lnet/minecraft/util/math/BlockPos;)I",
			"getLight(Lnet/minecraft/util/math/BlockPos;Z)I"
		},
		constant = @Constant(
			intValue = 255
		)
	)
	private int useActualHeightMinusOne(int original){
		return this.getHeight() - 1;
	}

}
