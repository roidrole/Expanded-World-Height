package roidrole.expandedheight.mixins.minecraft;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(value = Chunk.class, priority = 1200)
public abstract class MixinChunk {
	@Shadow
	@Final
	private World world;

	@ModifyConstant(
		method = {"<init>(Lnet/minecraft/world/World;Lnet/minecraft/world/chunk/ChunkPrimer;II)V", "isEmptyBetween"},
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeight(int constant){
		return this.world.getHeight();
	}

	@ModifyConstant(
		method = "<init>(Lnet/minecraft/world/World;II)V",
		constant = @Constant(
			intValue = 16
		)
	)
	private int actualHeight2(int constant, @Local(argsOnly = true) World worldIn){
		return worldIn.getHeight()/16;
	}

	@ModifyConstant(
		method = "relightBlock",
		constant = @Constant(
			intValue = 255
		)
	)
	private int actualHeight3(int constant){
		return ConfigManager.heightMask;
	}

	@ModifyConstant(
		method = "isEmptyBetween",
		constant = @Constant(
			intValue = 255
		)
	)
	private int actualHeight4(int constant){
		return this.world.getHeight() - 1;
	}
}
