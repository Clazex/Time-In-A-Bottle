package lumien.randomthings.handler;

import org.apache.logging.log4j.Level;

import lumien.randomthings.RandomThings;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RTEventHandler
{
	public static int clientAnimationCounter;

	@SubscribeEvent
	public void tick(TickEvent tickEvent)
	{
		if ((tickEvent.type == TickEvent.Type.CLIENT))
		{
			clientAnimationCounter++;
		}
	}
}
