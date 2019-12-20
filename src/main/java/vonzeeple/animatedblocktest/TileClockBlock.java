package vonzeeple.animatedblocktest;

import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class TileClockBlock extends TileEntity implements ITickable {

    private final IAnimationStateMachine asm;
    private TimeValues.VariableValue daytime = new TimeValues.VariableValue(0f );

    public TileClockBlock() {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            asm = ModelLoaderRegistry.loadASM(new ResourceLocation(ExampleMod.MODID, "asms/block/clock_model_asm.json"), ImmutableMap.<String, ITimeValue>of("time", daytime));
        } else asm = null;
    }


    @Override
    public boolean hasFastRenderer()
    {
        return false;
    }
    //--------------------
    //Capabilities related code

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return getCapability(capability, facing) != null;
    }

    //--------------------------------
    //NBT related code
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {

        if(asm == null){return;}

        daytime.setValue((this.getWorld().getWorldTime()*1f/24000)%1);
        //daytime.setValue((this.time)%1);
        if(asm.currentState().equals("default"))
        {
            asm.transition("moving");
        }
    }
}
