package roidrole.expandedheight.mixins.celeritas;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.taumc.celeritas.impl.render.terrain.VintageRenderSectionManager;

@Mixin(VintageRenderSectionManager.class)
public abstract class MixinVintageRenderSectionManager {
	@ModifyConstant(
		method = "create",
		constant = @Constant(intValue = 16),
		remap = false
	)
	private static int useWorldHeight(int constant, @Local(argsOnly = true) WorldClient world){
		return world.getHeight()/16;
	}
}
