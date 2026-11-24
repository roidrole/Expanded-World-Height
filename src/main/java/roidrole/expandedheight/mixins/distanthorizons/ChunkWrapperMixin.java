package roidrole.expandedheight.mixins.distanthorizons;

import com.seibel.distanthorizons.common.wrappers.chunk.ChunkWrapper;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChunkWrapper.class)
public abstract class ChunkWrapperMixin {
	@ModifyConstant(
		method = "getHeight(Lnet/minecraft/world/chunk/Chunk;)I",
		constant = @Constant(intValue = 255),
		remap = false
	)
	private static int useWorldHeightMinusOne(int constant, Chunk chunk){
		return chunk.getWorld().getHeight() - 1;
	}

	@ModifyConstant(
		method = "getExclusiveMaxBuildHeight(Lnet/minecraft/world/chunk/Chunk;)I",
		constant = @Constant(intValue = 256),
		remap = false
	)
	private static int useWorldHeight(int constant, Chunk chunk){
		return chunk.getWorld().getHeight();
	}
}
