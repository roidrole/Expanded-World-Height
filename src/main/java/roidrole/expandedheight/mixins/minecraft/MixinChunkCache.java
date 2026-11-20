package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChunkCache.class)
public abstract class MixinChunkCache {

	@Shadow
	protected World world;

	@ModifyConstant(
		method = {"getBlockState", "isSideSolid"},
		constant = @Constant(
			intValue = 256
		),
		remap = false
	)
	private int actualHeight(int constant){
		return this.world.getHeight();
	}

	@SideOnly(Side.CLIENT)
	@ModifyConstant(
		method = {"getLightFor", "getLightForExt"},
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeightClient(int constant){
		return this.world.getHeight();
	}
}
