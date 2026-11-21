package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiOverlayDebug.class)
public abstract class GuiOverlayDebugMixin {
	@Shadow
	@Final
	private Minecraft mc;

	@ModifyConstant(
		method = "call",
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeight(int constant){
		return this.mc.world.getHeight();
	}
}
