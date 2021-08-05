package com.teammoeg.weatherforecast.capability;

import com.teammoeg.weatherforecast.WeatherForecast;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TempForecastCapabilityProvider implements ICapabilityProvider {

    private ITempForecastCapability capability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    // Helper method
    public static LazyOptional<ITempForecastCapability> getCapability(@Nullable World world) {
        if (world != null) {
            return world.getCapability(CAPABILITY);
        } else return LazyOptional.empty();
    }

    @Nonnull
    ITempForecastCapability getOrCreateCapability() {
        if (capability == null) {
            this.capability = new TempForecastCapability(0, 0, 0, false, false);
        }
        return this.capability;
    }

    @CapabilityInject(ITempForecastCapability.class)
    public static Capability<ITempForecastCapability> CAPABILITY = null;
    public static final ResourceLocation KEY = new ResourceLocation(WeatherForecast.MODID, "temperature_forecast");

    public static void setup() {
        CapabilityManager.INSTANCE.register(ITempForecastCapability.class, new Capability.IStorage<ITempForecastCapability>() {
            public INBT writeNBT(Capability<ITempForecastCapability> capability, ITempForecastCapability instance, Direction side) {
                return null;
            }

            public void readNBT(Capability<ITempForecastCapability> capability, ITempForecastCapability instance, Direction side, INBT nbt) {

            }
        }, () -> null);
    }
}
