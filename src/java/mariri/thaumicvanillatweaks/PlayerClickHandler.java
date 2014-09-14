package mariri.thaumicvanillatweaks;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import thaumcraft.api.ThaumcraftApiHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerClickHandler {
	
//	public static String BEDTWEAKS_SET_RESPAWN_MESSAGE;
//	public static String BEDTWEAKS_NO_SLEEP_MESSAGE;

	@SubscribeEvent
	public void onPlayerClick(PlayerInteractEvent e){
		if(!e.entityPlayer.worldObj.isRemote && !e.entityPlayer.isSneaking()){
			if(e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
				onRightClickBlock(e);
			}
		}
	}
	
	private void onRightClickBlock(PlayerInteractEvent e){
		World world = e.entityPlayer.worldObj;
		Block block = world.getBlock(e.x, e.y, e.z);// Block.blocksList[world.getBlockId(e.x, e.y, e.z)];
		int meta = world.getBlockMetadata(e.x, e.y, e.z);
		// ベッド補助機能
		if(block == Blocks.bed && canSleep(world, e.x, e.y, e.z, e.entityPlayer)){
			// いつでもリスポーンセット
			if(ThaumcraftApiHelper.isResearchComplete(e.entityPlayer.getCommandSenderName(), ThaumicVanillaTweaks.RESEARCH_SET_RESPAWN)){
	        	ChunkCoordinates respawn = new ChunkCoordinates(e.x, e.y, e.z);
                e.entityPlayer.setSpawnChunk(respawn, false, e.entityPlayer.dimension);
                e.entityPlayer.addChatComponentMessage(new ChatComponentText("Set respawn successful!!"));
			}
			// 寝るの禁止
			if(!ThaumcraftApiHelper.isResearchComplete(e.entityPlayer.getCommandSenderName(), ThaumicVanillaTweaks.RESEARCH_SLEEP)){
                e.entityPlayer.addChatComponentMessage(new ChatComponentText("You come back to haunt research"));
                e.setCanceled(true);
			}else if(!e.entityPlayer.inventory.hasItem(new ItemStack(Blocks.wool).getItem())){
                e.entityPlayer.addChatComponentMessage(new ChatComponentText("You have to replace wool"));
                e.setCanceled(true);
			}else{
				e.entityPlayer.inventory.consumeInventoryItem(new ItemStack(Blocks.wool).getItem());
				e.entityPlayer.onUpdate();
			}
		}
	}
	
	private boolean canSleep(World world, int x, int y, int z, EntityPlayer player){
		boolean result = true;
		result &= world.provider.canRespawnHere();
		result &= world.getBiomeGenForCoords(x, z) != BiomeGenBase.hell;
		result &= world.provider.isSurfaceWorld();
		result &= player.isEntityAlive();
		result &= world.getEntitiesWithinAABB(EntityMob.class,
				AxisAlignedBB.getBoundingBox(x - 8, y - 5, z - 8, x + 8, y + 5, z + 8)).isEmpty();
		return result;
	}
}
