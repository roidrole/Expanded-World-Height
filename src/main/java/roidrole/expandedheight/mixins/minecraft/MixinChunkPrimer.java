package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.chunk.ChunkPrimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import roidrole.expandedheight.ConfigManager;

@Mixin(ChunkPrimer.class)
public abstract class MixinChunkPrimer {
	@ModifyConstant(
		method = "<init>",
		constant = @Constant(
			intValue = 65536
		)
	)
	private int useActualSize(int original){
		return ConfigManager.primerSize;
	}

	@ModifyConstant(
		method = {"getBlockIndex", "findGroundBlockIdx"},
		constant = {
			@Constant(intValue = 8),
			@Constant(intValue = 12)
		}
	)
	private static int yOffset(int original){
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
