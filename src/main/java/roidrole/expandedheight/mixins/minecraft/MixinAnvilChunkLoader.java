package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilChunkLoader.class)
public abstract class MixinAnvilChunkLoader {
	@ModifyConstant(
		method = "readChunkFromNBT",
		constant = @Constant(intValue = 16)
	)
	private int useWorldHeight(int constant, World worldIn){
		return worldIn.getHeight()/16;
	}
}
