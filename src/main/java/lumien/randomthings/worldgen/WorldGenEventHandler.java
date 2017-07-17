package lumien.randomthings.worldgen;

import java.util.Random;

import lumien.randomthings.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGenEventHandler
{
	WorldGenPatches glowingMushRoomGen = new WorldGenPatches(ModBlocks.glowingMushroom);

	@SubscribeEvent
	public void mushroomGen(DecorateBiomeEvent.Decorate event)
	{
		if (event.getType() == EventType.SHROOM && event.getRand().nextInt(4) == 0)
		{
			BlockPos chunkPos = event.getPos();
			Random random = event.getRand();
			int offX = random.nextInt(16) + 8;
			int offZ = random.nextInt(16) + 8;
			int offY = event.getWorld().getHeight(chunkPos.add(offX, 0, offZ)).getY() - 4;

			if (offY > 0)
			{
				int posY = random.nextInt(offY);
				glowingMushRoomGen.generate(event.getWorld(), random, chunkPos.add(offX, posY, offZ));
			}

		}
	}
}