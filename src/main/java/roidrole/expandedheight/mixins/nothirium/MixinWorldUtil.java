package roidrole.expandedheight.mixins.nothirium;

import com.llamalad7.mixinextras.sugar.Local;
import meldexun.nothirium.mc.util.WorldUtil;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldUtil.class)
public abstract class MixinWorldUtil {
	@ModifyConstant(
		method = "getSection(Lnet/minecraft/world/World;III)Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;",
		constant = @Constant(intValue = 16),
		remap = false
	)
	private static int useActualHeight(int constant, @Local(argsOnly = true) World world){
		return world.getHeight()/16;
	}
}
