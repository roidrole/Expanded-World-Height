package roidrole.expandedheight.mixins.tfc;

import net.dries007.tfc.world.classic.CustomChunkPrimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(CustomChunkPrimer.class)
public abstract class MixinCustomChunkPrimer {
	@ModifyConstant(
		method = "<init>",
		constant = @Constant(
			intValue = 65536
		),
		remap = false
	)
	private int useActualSize(int original){
		return ConfigManager.primerVolume;
	}

	@ModifyConstant(
		method = {
			"findGroundBlockIdx",
			"getBlockState",
			"setBlockState"
		},
		constant = {
			@Constant(intValue = 8),
			@Constant(intValue = 12)
		}
	)
	private static int yOffset(int original){
		return original + ConfigManager.primerYBits - 8;
	}

	@ModifyConstant(
		method = "isEmpty",
		constant = {
			@Constant(intValue = 8),
			@Constant(intValue = 12)
		},
		remap = false
	)
	private static int yOffsetNoRemap(int original){
		return original + ConfigManager.primerYBits - 8;
	}

	@ModifyConstant(
		method = "findGroundBlockIdx",
		constant = @Constant(intValue = 256)
	)
	private int useActualHeight(int constant){
		return ConfigManager.primerHeight;
	}

	@ModifyConstant(
		method = "findGroundBlockIdx",
		constant = @Constant(intValue = 255)
	)
	private int useActualHeight2(int constant){
		return ConfigManager.primerHeight - 1;
	}
}
