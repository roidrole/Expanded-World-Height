package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.gen.ChunkGeneratorFlat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(ChunkGeneratorFlat.class)
public abstract class MixinChunkGeneratorFlat {
	@ModifyConstant(
		method = "<init>",
		constant = @Constant(intValue = 256)
	)
	private static int useOverWorldHeight(int constant){
		//Take the height of the overworld as that is the only superflat world that is actually flat
		return ConfigManager.getOverworldHeight();
	}
}
