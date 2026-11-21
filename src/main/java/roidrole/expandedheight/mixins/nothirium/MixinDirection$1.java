package roidrole.expandedheight.mixins.nothirium;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "meldexun.nothirium.util.Direction$1")
public abstract class MixinDirection$1 {
	@ModifyConstant(
		method = "isFaceCulled",
		constant = @Constant(intValue = 16),
		remap = false
	)
	private int considerWorldHeight(int constant){
		return Minecraft.getMinecraft().world.getHeight()/16;
	}
}
