package roidrole.expandedheight.mixins.fbp;

import dev.redstudio.fbp.FBP;
import dev.redstudio.fbp.renderer.light.LightUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LightUtil.class)
@SideOnly(Side.CLIENT)
public abstract class MixinLightUtil {
	@ModifyConstant(
		method = "getCombinedLight(III)I",
		constant = @Constant(intValue = 255),
		remap = false
	)
	private static int useWorldHeightMinusOne(int constant){
		return FBP.MC.player.world.getHeight() - 1;
	}
	@ModifyConstant(
		method = "getCombinedLight(III)I",
		constant = @Constant(intValue = 256),
		remap = false
	)
	private static int useWorldHeight(int constant){
		return FBP.MC.player.world.getHeight();
	}
}
