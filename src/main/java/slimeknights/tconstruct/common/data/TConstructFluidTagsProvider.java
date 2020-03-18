package slimeknights.tconstruct.common.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.common.Tags;
import slimeknights.tconstruct.fluids.TinkerFluids;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class TConstructFluidTagsProvider extends FluidTagsProvider {

  private Set<ResourceLocation> filter = null;

  public TConstructFluidTagsProvider(DataGenerator generatorIn) {
    super(generatorIn);
  }

  @Override
  public void registerTags() {
    super.registerTags();

    this.filter = this.tagToBuilder.keySet().stream().map(Tag::getId).collect(Collectors.toSet());

    this.getBuilder(Tags.Fluids.SLIME).add(Tags.Fluids.BLUE_SLIME, Tags.Fluids.PINK_SLIME);
    this.getBuilder(Tags.Fluids.BLUE_SLIME).add(TinkerFluids.blue_slime_fluid.get(), TinkerFluids.blue_slime_fluid_flowing.get(), TinkerFluids.purple_slime_fluid.get(), TinkerFluids.blue_slime_fluid_flowing.get());
    this.getBuilder(Tags.Fluids.PINK_SLIME).add(TinkerFluids.purple_slime_fluid.get(), TinkerFluids.blue_slime_fluid_flowing.get());
  }

  @Override
  protected Path makePath(ResourceLocation id) {
    return this.filter != null && this.filter.contains(id) ? null : super.makePath(id); //We don't want to save vanilla tags.
  }

  @Override
  public String getName() {
    return "Tinkers Construct Fluid Tags";
  }

}