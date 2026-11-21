package roidrole.expandedheight.mixins.nothirium;

import meldexun.nothirium.mc.renderer.chunk.RenderChunk;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RenderChunk.class)
public abstract class MixinRenderChunk {
	@ModifyConstant(
		method = "markDirty",
		constant = @Constant(intValue = 16),
		remap = false
	)
	private int useWorldHeight(int constant){
		return Minecraft.getMinecraft().world.getHeight()/16;
	}
}
