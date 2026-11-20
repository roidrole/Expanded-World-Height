package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.debug.DebugRendererChunkBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DebugRendererChunkBorder.class)
public abstract class MixinDebugRendererChunkBorder {

	@Shadow
	@Final
	private Minecraft minecraft;

	@ModifyConstant(
		method = "render",
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeight(int constant){
		return this.minecraft.world.getHeight();
	}
}
