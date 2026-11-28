package roidrole.expandedheight.mixins.minecraft;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilChunkLoader.class)
public abstract class MixinAnvilChunkLoader {
	@ModifyConstant(
		method = "readChunkFromNBT",
		constant = @Constant(intValue = 16)
	)
	private int useWorldHeight(int constant, World worldIn){
		return worldIn.getHeight()/16;
	}

	//Fix crash on lowering world height
	@Definition(
		id = "aextendedblockstorage",
		local = @Local(
			type = ExtendedBlockStorage[].class,
			name = "aextendedblockstorage"
		)
	)
	@Expression("aextendedblockstorage[?]=?")
	@WrapOperation(
		method = "readChunkFromNBT",
		at = @At(
			value = "MIXINEXTRAS:EXPRESSION"
		)
	)
	private void capAtArrayLength(ExtendedBlockStorage[] array, int index, ExtendedBlockStorage value, Operation<Void> original){
		if(index < array.length){
			original.call(array, index, value);
		}
	}
}
