package vonzeeple.animatedblocktest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
@Mod.EventBusSubscriber
public class ExampleMod
{
    public static final String MODID = "animatedblocktest";
    public static final String NAME = "Animated Block Test";
    public static final String VERSION = "0.1";

    public static Logger logger;


    public static Block animatedBlock;
    public static Block clockBlock;

    @Mod.Instance(MODID)
    public static ExampleMod instance;

    @SidedProxy
    public static CommonProxy proxy;
    public static abstract class CommonProxy
    {
        public void preInit(FMLPreInitializationEvent event)
        {

        }
    }
    public static class ServerProxy extends CommonProxy{
        @Override
        public void preInit(FMLPreInitializationEvent event)
        {

        }
    }
    public static class ClientProxy extends CommonProxy{
        @Override
        public void preInit(FMLPreInitializationEvent event)
        {

        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                animatedBlock = new BlockAnimatedBlock().setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
                        .setUnlocalizedName("animatedblock")
                        .setRegistryName(ExampleMod.MODID+":animatedblock"),
                clockBlock = new BlockClock().setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
                        .setUnlocalizedName("blockclock")
                        .setRegistryName(ExampleMod.MODID+":blockclock")
                );

        GameRegistry.registerTileEntity(TileAnimatedBlock.class, new ResourceLocation(MODID,"tileanimatedblock"));
        GameRegistry.registerTileEntity(TileClockBlock.class, new ResourceLocation(MODID,"tileclockblock"));

      }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register( new ItemBlock(animatedBlock).setRegistryName(animatedBlock.getRegistryName()));
        event.getRegistry().register( new ItemBlock(clockBlock).setRegistryName(clockBlock.getRegistryName()));
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileAnimatedBlock.class, new AnimationTESR<TileAnimatedBlock>());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(animatedBlock), 0, new ModelResourceLocation( Item.getItemFromBlock(animatedBlock).getRegistryName(), "inventory"));

        ClientRegistry.bindTileEntitySpecialRenderer(TileClockBlock.class, new AnimationTESR<TileClockBlock>());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(clockBlock), 0, new ModelResourceLocation( Item.getItemFromBlock(clockBlock).getRegistryName(), "inventory"));

    }



}



