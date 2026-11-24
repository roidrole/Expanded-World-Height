package roidrole.expandedheight.mixins.rltweaker;

import com.charles445.rltweaker.hook.HookWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(HookWorld.class)
public abstract class MixinHookWorld {
	@ModifyConstant(
		method = "getCollisionBoxes",
		constant = @Constant(intValue = 255),
		remap = false
	)
	private static int useWorldHeight(int constant, World world){
		return world.getHeight() - 1;
	}
}
