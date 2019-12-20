package vonzeeple.animatedblocktest;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

public class BlockAnimatedBlock extends Block {

    public BlockAnimatedBlock() {

        super(Material.WOOD);

    }


    //--------------------------------------
    //BlockState related code
    @Override
    public ExtendedBlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty<?>[] { Properties.StaticProperty }, new IUnlistedProperty<?>[] { Properties.AnimationProperty });
    }


    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    //Display the static part of the model
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(Properties.StaticProperty, true);
    }

    //-----------------------------
    //Tile entity related code

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAnimatedBlock();
    }

    //---------------------------------------
    //Block rendering related code

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Only overrode to explain that if your block doesn't have a model that is not animated, it should return EnumBlockRenderType.ENTITYBLOCK_ANIMATED.
     */
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}

