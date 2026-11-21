package roidrole.expandedheight.mixins.nothirium;

import meldexun.nothirium.mc.renderer.ChunkRenderManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChunkRenderManager.class)
public abstract class MixinChunkRenderManager {
	@ModifyConstant(
		method = "totalSections",
		constant = @Constant(intValue = 16),
		remap = false
	)
	private static int useWorldHeight(int constant){
		return Minecraft.getMinecraft().world.getHeight()/16;
	}
}
