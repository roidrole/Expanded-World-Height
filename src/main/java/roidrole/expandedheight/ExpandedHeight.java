package roidrole.expandedheight;

import git.jbredwards.fluidlogged_api.api.capability.IFluidStateCapability;
import git.jbredwards.fluidlogged_api.api.capability.IFluidStateContainer;
import git.jbredwards.fluidlogged_api.api.util.FluidState;
import git.jbredwards.fluidlogged_api.mod.asm.iface.IHardcodedCapability;
import git.jbredwards.fluidlogged_api.mod.common.capability.FluidStateCapabilityWrapped;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;


@Mod(
	modid = Tags.MOD_ID,
	name = Tags.MOD_NAME,
	version = Tags.VERSION,
	dependencies = "required:configanytime;required:mixinbooter"
)
public class ExpandedHeight {
	public static Logger LOGGER;

	@Mod.EventHandler
	public static void onPreInit(FMLPreInitializationEvent event){
		LOGGER = event.getModLog();
		MinecraftForge.EVENT_BUS.register(ExpandedHeight.class);
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) throws NoSuchFieldException, IllegalAccessException {
		if(event.side == Side.CLIENT){
			return;
		}
		World world = event.player.world;
		if(world.getTotalWorldTime() % 0x2F != 0){
			return;
		}
		BlockPos playerPos = new BlockPos(event.player);
		Chunk chunkIn = world.getChunk(playerPos);
		IFluidStateCapability capability = chunkIn.getCapability(IFluidStateCapability.CAPABILITY, null);
		if(capability == null){
			return;
		}
		Field wrapped = FluidStateCapabilityWrapped.class.getDeclaredField("wrapped");
		wrapped.setAccessible(true);
		IHardcodedCapability hardCodedCap = (IHardcodedCapability) wrapped.get(capability);
		System.out.println(hardCodedCap.getFluidStateCapability().getClass());


		BlockPos posDown = playerPos.offset(EnumFacing.DOWN, 1);
		IFluidStateContainer container = capability.getContainer(posDown.getY());
		ExpandedHeight.LOGGER.info("pos = {}", posDown);
		ExpandedHeight.LOGGER.info("container = {}", container.getClass());
		int serializedY = container.serializeY(posDown.getY());
		ExpandedHeight.LOGGER.info("serializedY = 0b{}", Integer.toBinaryString(serializedY));
		char serializedPos = container.serializePos(posDown);
		ExpandedHeight.LOGGER.info("serializedPos = 0b{}", Integer.toBinaryString(serializedPos));
		boolean hasState = container.hasFluidState(serializedPos);
		ExpandedHeight.LOGGER.info("has fluid state = {}", hasState);
		if(hasState){
			FluidState state = container.getFluidState(serializedPos, FluidState.EMPTY);
			ExpandedHeight.LOGGER.info("block = {}", state.getBlock().delegate.name());
			ExpandedHeight.LOGGER.info("fluid = {}", state.getFluid().getName());
		}

		ExpandedHeight.LOGGER.info("All block pos of this chunk:");
		container.forEach((thisSerializedPos, state) -> {
			ExpandedHeight.LOGGER.info("pos = {}", container.deserializePos(thisSerializedPos));
			ExpandedHeight.LOGGER.info("block = {}", state.getBlock().delegate.name());
			ExpandedHeight.LOGGER.info("fluid = {}", state.getFluid().getName());
		});
	}
}