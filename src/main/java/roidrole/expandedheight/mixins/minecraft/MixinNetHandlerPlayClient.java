package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient {
	@Shadow
	public WorldClient world;

	@ModifyConstant(
		method = "handleChunkData",
		constant = @Constant(
			intValue = 256
		)
	)
	private int actualHeight(int constant){
		return this.world.getHeight();
	}
}
