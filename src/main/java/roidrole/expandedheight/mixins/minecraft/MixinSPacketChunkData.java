package roidrole.expandedheight.mixins.minecraft;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SPacketChunkData.class)
public class MixinSPacketChunkData {
	@ModifyConstant(
		method = "<init>(Lnet/minecraft/world/chunk/Chunk;I)V",
		constant = @Constant(
			intValue = 65535
		)
	)
	private int useActualHeight(int original, @Local(argsOnly = true) Chunk chunkIn){
		return chunkIn.getWorld().getHeight() * 256 - 1;
	}
}
