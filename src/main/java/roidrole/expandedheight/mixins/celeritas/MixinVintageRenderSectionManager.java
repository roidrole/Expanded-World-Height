package roidrole.expandedheight.mixins.celeritas;

import net.minecraft.client.multiplayer.WorldClient;
import org.embeddedt.embeddium.impl.render.chunk.vertex.format.ChunkVertexType;
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
	private static int useWorldHeight(int constant, ChunkVertexType vertexType, WorldClient world){
		return world.getHeight()/16;
	}
}
