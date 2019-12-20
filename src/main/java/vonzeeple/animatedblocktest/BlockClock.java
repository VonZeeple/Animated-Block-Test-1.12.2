package vonzeeple.animatedblocktest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

public class BlockClock extends Block {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockClock() {
        super(Material.WOOD);
    }


    //--------------------------------------
    //BlockState related code
    @Override
    public ExtendedBlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty<?>[] { Properties.StaticProperty , FACING}, new IUnlistedProperty<?>[] { Properties.AnimationProperty });
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (facing.getAxis().isHorizontal() )
        {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else
        {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
            }

            return this.getDefaultState();
        }
    }
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(FACING)).getIndex();
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
        return new TileClockBlock();
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
