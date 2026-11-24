package roidrole.expandedheight;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
	modid = Tags.MOD_ID,
	name = Tags.MOD_NAME,
	version = Tags.VERSION,
	dependencies = "required:configanytime;required:mixinbooter"
)
@Mod.EventBusSubscriber
public class ExpandedHeight {
	public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_ID);
}