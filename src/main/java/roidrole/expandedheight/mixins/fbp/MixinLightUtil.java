package roidrole.expandedheight.mixins.fbp;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Pseudo
@Mixin(targets = {
	//Classes as string because class file 69 breaks runClient
	"dev.redstudio.fbp.renderer.light.LightUtil",
	"com.fbp.renderer.light.LightUtil"
})
@SideOnly(Side.CLIENT)
public abstract class MixinLightUtil {
	@ModifyConstant(
		method = "getCombinedLight(III)I",
		constant = @Constant(intValue = 255),
		remap = false
	)
	private static int useWorldHeightMinusOne(int constant){
		return Minecraft.getMinecraft().world.getHeight() - 1;
	}
	@ModifyConstant(
		method = "getCombinedLight(III)I",
		constant = @Constant(intValue = 256),
		remap = false
	)
	private static int useWorldHeight(int constant){
		return Minecraft.getMinecraft().world.getHeight();
	}
}
