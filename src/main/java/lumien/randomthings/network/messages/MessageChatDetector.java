package lumien.randomthings.network.messages;

import io.netty.buffer.ByteBuf;
import lumien.randomthings.network.IRTMessage;
import lumien.randomthings.network.MessageUtil;
import lumien.randomthings.tileentity.TileEntityChatDetector;
import lumien.randomthings.tileentity.TileEntityOnlineDetector;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChatDetector implements IRTMessage
{
	String chatMessage;
	boolean consume;
	
	BlockPos pos;

	public MessageChatDetector()
	{

	}

	public MessageChatDetector(String chatMessage,boolean consume, BlockPos pos)
	{
		this.chatMessage = chatMessage;
		this.pos = pos;
		this.consume = consume;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		ByteBufUtils.writeUTF8String(buffer, chatMessage);
		buffer.writeBoolean(consume);

		MessageUtil.writeBlockPos(pos, buffer);
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		chatMessage = ByteBufUtils.readUTF8String(buffer);
		consume = buffer.readBoolean();
		
		pos = MessageUtil.readBlockPos(buffer);
	}

	@Override
	public void onMessage(MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		World worldObj = player.worldObj;
		if (worldObj.getTileEntity(pos) instanceof TileEntityChatDetector && pos.distanceSq(player.getPosition()) < 100)
		{
			TileEntityChatDetector od = (TileEntityChatDetector) worldObj.getTileEntity(pos);
			od.setChatMessage(chatMessage);
			od.setConsume(consume);
		}
	}

}