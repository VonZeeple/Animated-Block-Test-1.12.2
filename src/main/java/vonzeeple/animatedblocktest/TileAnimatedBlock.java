package vonzeeple.animatedblocktest;

import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class TileAnimatedBlock extends TileEntity implements ITickable {

    private final IAnimationStateMachine asm;

    private static TimeValues.VariableValue rot_speed = new TimeValues.VariableValue(10f );
    public TileAnimatedBlock() {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            asm = ModelLoaderRegistry.loadASM(new ResourceLocation(ExampleMod.MODID, "asms/block/animatedblock.json"), ImmutableMap.<String, ITimeValue>of("rot_speed", rot_speed));


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
        rot_speed.setValue(0.1f);
        if(asm == null){return;}

        if(asm.currentState().equals("default"))
        {
            asm.transition("moving");
        }
    }
}
