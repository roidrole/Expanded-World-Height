package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(WorldProvider.class)
public abstract class MixinWorldProvider {
	@Unique
	private int expandedheight_height;

	@Inject(
		method = "setDimension",
		at = @At("TAIL"),
		remap = false
	)
	private void setHeight(int dim, CallbackInfo ci){
		this.expandedheight_height = ConfigManager.worldHeight.get(dim);
	}

	/**
	 * @author roidrole
	 * @reason Use custom height
	 */
	@Overwrite(remap = false)
	public int getHeight() {
		return expandedheight_height;
	}
}
