package lumien.randomthings.config;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfiguration
{
	Configuration configuration;

	public void preInit(FMLPreInitializationEvent event)
	{
		configuration = new Configuration(event.getSuggestedConfigurationFile());
		configuration.load();
		
		if (configuration.hasChanged())
		{
			configuration.save();
		}
	}
}
