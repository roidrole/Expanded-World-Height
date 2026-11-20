package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldClient.class)
public abstract class MixinWorldClient extends World {
	protected MixinWorldClient() {
		super(null, null, null, null, true);
	}

	@ModifyConstant(
		method = "doPreChunk",
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeight(int constant){
		return this.getHeight();
	}
}
