package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

//priority > 1000 so it's after Celeritas
@Mixin(value = RenderGlobal.class, priority = 1200)
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

	@ModifyConstant(
		method = "renderEntities",
		constant = @Constant(
			doubleValue = 256.0d
		)
	)
	private double actualHeight(double constant){
		return this.world.getHeight();
	}

	@ModifyConstant(
		method = "setupTerrain",
		constant = @Constant(
			intValue = 248
		)
	)
	private int clampToCorrectHeight(int original){
		return this.world.getHeight() - 8;
	}
}
