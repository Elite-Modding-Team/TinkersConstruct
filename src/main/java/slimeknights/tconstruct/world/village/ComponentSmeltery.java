package slimeknights.tconstruct.world.village;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.List;
import java.util.Random;

public class ComponentSmeltery extends StructureVillagePieces.House1 {

    private int averageGroundLevel = -1;

    public ComponentSmeltery() {}

    public ComponentSmeltery(Start villagePiece, int componentType, Random rand, StructureBoundingBox sbb, EnumFacing coordBaseMode) {
        super();
        this.setCoordBaseMode(coordBaseMode);
        this.boundingBox = sbb;
    }

    public static ComponentSmeltery buildComponent(Start villagePiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        StructureBoundingBox sbb = StructureBoundingBox.getComponentToAddBoundingBox(
                p1, p2, p3, 0, 0, 0, 9, 3, 7, facing
        );
        return canVillageGoDeeper(sbb) && StructureComponent.findIntersecting(pieces, sbb) == null
                ? new ComponentSmeltery(villagePiece, p5, random, sbb, facing)
                : null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox sbb) {

        if(this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);

            if(this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 2, 0);
        }

        IBlockState stoneBricks = Blocks.STONEBRICK.getDefaultState();

        this.fillWithBlocks(world, sbb, 1, 0, 0, 7, 0, 6, stoneBricks, stoneBricks, false); //Base
        this.fillWithBlocks(world, sbb, 0, 0, 1, 0, 0, 5, stoneBricks, stoneBricks, false);
        this.fillWithBlocks(world, sbb, 8, 0, 1, 8, 0, 5, stoneBricks, stoneBricks, false);
        this.fillWithAir(world, sbb, 0, 1, 0, 9, 3, 7);

        IBlockState searedBricks = TinkerSmeltery.searedBlock.getStateFromMeta(3);

        this.fillWithBlocks(world, sbb, 2, 0, 1, 6, 2, 5, searedBricks, searedBricks, false); //Basin
        this.fillWithAir(world, sbb, 3, 1, 2, 5, 2, 4);

        IBlockState castingTable = TinkerSmeltery.castingBlock.getDefaultState();
        IBlockState castingBasin = TinkerSmeltery.castingBlock.getStateFromMeta(1);

        this.setBlockState(world, castingTable, 1, 1, 2, sbb);
        this.setBlockState(world, castingBasin, 1, 1, 4, sbb);
        this.setBlockState(world, castingTable, 7, 1, 2, sbb);
        this.setBlockState(world, castingBasin, 7, 1, 4, sbb);

        for(int l = 1; l < 6; ++l) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.clearCurrentPositionBlocksUpwards(world, i1, 9, l, sbb);
                this.replaceAirAndLiquidDownwards(world, stoneBricks, i1, -1, l, sbb);
            }
        }

        for(int l = 0; l < 7; ++l) {
            for(int i1 = 1; i1 < 8; ++i1) {
                this.clearCurrentPositionBlocksUpwards(world, i1, 9, l, sbb);
                this.replaceAirAndLiquidDownwards(world, stoneBricks, i1, -1, l, sbb);
            }
        }

        return true;

    }

}