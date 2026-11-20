package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal {

	@Shadow
	private WorldClient world;

	@ModifyConstant(
		method = "getRenderChunkOffset",
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeight(int constant){
		return this.world.getHeight();
	}
}
