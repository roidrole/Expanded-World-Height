package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(WorldProvider.class)
public abstract class MixinWorldProvider {
	@Shadow(remap = false)
	public abstract int getDimension();

	@Unique
	private int expandedheight_height;

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void setHeight(CallbackInfo ci){
		this.expandedheight_height = ConfigManager.worldHeight.get(this.getDimension());
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
