package roidrole.expandedheight.mixins.rtg;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import rtg.world.gen.ChunkGeneratorRTG;

@Mixin(ChunkGeneratorRTG.class)
public abstract class MixinChunkGeneratorRTG {
	@Shadow(remap = false)
	@Final
	private World world;

	@ModifyConstant(
		method = "generateTerrain",
		constant = @Constant(intValue = 256),
		remap = false
	)
	private int useWorldHeight(int constant){
		return this.world.getHeight();
	}
}
