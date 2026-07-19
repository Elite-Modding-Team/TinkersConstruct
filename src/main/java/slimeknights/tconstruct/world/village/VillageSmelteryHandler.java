package slimeknights.tconstruct.world.village;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import slimeknights.tconstruct.TConstruct;

import java.util.List;
import java.util.Random;

public class VillageSmelteryHandler implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(ComponentSmeltery.class, 9, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        MapGenStructureIO.registerStructureComponent(ComponentSmeltery.class, TConstruct.modID + ":SmelteryStructure");
        return ComponentSmeltery.class;
    }

    @Override
    public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing p4, int p5) {
        return ComponentSmeltery.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }

}