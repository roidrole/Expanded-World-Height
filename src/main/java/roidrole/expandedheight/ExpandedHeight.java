package roidrole.expandedheight;

import git.jbredwards.fluidlogged_api.api.capability.IFluidStateCapability;
import git.jbredwards.fluidlogged_api.mod.asm.iface.IHardcodedCapability;
import git.jbredwards.fluidlogged_api.mod.common.capability.FluidStateCapabilityWrapped;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Field;


@Mod(
	modid = Tags.MOD_ID,
	name = Tags.MOD_NAME,
	version = Tags.VERSION,
	dependencies = "required:configanytime;required:mixinbooter"
)
public class ExpandedHeight {
	@Mod.EventHandler
	public static void onPreInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(ExpandedHeight.class);
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) throws NoSuchFieldException, IllegalAccessException {
		if(event.side == Side.CLIENT){
			return;
		}
		if(event.player.world.getTotalWorldTime() % 0x2F != 0){
			return;
		}
		Chunk chunkIn = event.player.world.getChunk((int)event.player.posX, (int)event.player.posZ);
		IFluidStateCapability capability = chunkIn.getCapability(IFluidStateCapability.CAPABILITY, null);
		Field wrapped = FluidStateCapabilityWrapped.class.getDeclaredField("wrapped");
		wrapped.setAccessible(true);
		IHardcodedCapability hardCodedCap = (IHardcodedCapability) wrapped.get(capability);
		System.out.println(hardCodedCap.getFluidStateCapability().getClass());
	}
}