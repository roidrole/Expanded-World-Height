package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ViewFrustum.class)
public abstract class MixinViewFrustrum {
	@Shadow
	@Final
	protected World world;

	@ModifyConstant(
		method = "setCountChunksXYZ",
		constant = @Constant(
			intValue = 16
		)
	)
	private int actualHeight(int constant){
		return this.world.getHeight()/16;
	}
}
