package lumien.randomthings;

import org.apache.logging.log4j.Logger;

import lumien.randomthings.config.ModConfiguration;
import lumien.randomthings.entitys.ModEntitys;
import lumien.randomthings.handler.RTEventHandler;
import lumien.randomthings.item.ModItems;
import lumien.randomthings.lib.RTCreativeTab;
import lumien.randomthings.lib.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = "[1.12,1.13)", dependencies = "after:jei@[4.7.11.100,);")
public class RandomThings
{
	@Instance(Reference.MOD_ID)
	public static RandomThings instance;

	@SidedProxy(clientSide = "lumien.randomthings.client.ClientProxy", serverSide = "lumien.randomthings.CommonProxy")
	public static CommonProxy proxy;

	public RTCreativeTab creativeTab;

	public Logger logger;

	public ModConfiguration configuration;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		creativeTab = new RTCreativeTab();
		logger = event.getModLog();

		configuration = new ModConfiguration();
		configuration.preInit(event);

		ModItems.load(event);
		ModEntitys.init();
		proxy.registerModels();

		RTEventHandler eventHandler = new RTEventHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.registerRenderers();
	}
}
