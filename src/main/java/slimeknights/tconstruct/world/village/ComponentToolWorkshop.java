package slimeknights.tconstruct.world.village;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.tileentity.TileCraftingStation;
import slimeknights.tconstruct.tools.common.tileentity.TilePatternChest;
import slimeknights.tconstruct.world.village.loot.VillageLoot;

import java.util.List;
import java.util.Random;

// TODO: See if the tables are all facing correctly when they generate
public class ComponentToolWorkshop extends StructureVillagePieces.House1 {

    private int averageGroundLevel = -1;

    public ComponentToolWorkshop() {}

    public ComponentToolWorkshop(Start villagePiece, int componentType, Random rand, StructureBoundingBox sbb, EnumFacing coordBaseMode) {
        super(villagePiece, componentType, rand, sbb, coordBaseMode);
        this.setCoordBaseMode(coordBaseMode);
        this.boundingBox = sbb;
    }

    public static ComponentToolWorkshop buildComponent(Start villagePiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        StructureBoundingBox sbb = StructureBoundingBox.getComponentToAddBoundingBox(
                p1, p2, p3, 0, 0, 0, 7, 6, 7, facing
        );
        return canVillageGoDeeper(sbb) && StructureComponent.findIntersecting(pieces, sbb) == null
                ? new ComponentToolWorkshop(villagePiece, p5, random, sbb, facing)
                : null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox sbb) {

        if(this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);

            if(this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4, 0);
        }

        IBlockState cobblestone = getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
        IBlockState fence = getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
        IBlockState planks = getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
        IBlockState wool = Blocks.WOOL.getDefaultState();

        this.fillWithBlocks(world, sbb, 0, 0, 0, 6, 0, 6, cobblestone, cobblestone, false); // Base
        this.fillWithBlocks(world, sbb, 0, 5, 0, 6, 5, 6, fence, fence, false);
        this.fillWithBlocks(world, sbb, 1, 0, 1, 5, 0, 5, planks, planks, false);
        this.fillWithBlocks(world, sbb, 2, 0, 2, 4, 0, 4, wool, wool, false);

        IBlockState log = getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());

        this.fillWithBlocks(world, sbb, 0, 1, 0, 0, 4, 0, log, log, false); // Edges
        this.fillWithBlocks(world, sbb, 0, 1, 6, 0, 4, 6, log, log, false);
        this.fillWithBlocks(world, sbb, 6, 1, 0, 6, 4, 0, log, log, false);
        this.fillWithBlocks(world, sbb, 6, 1, 6, 6, 4, 6, log, log, false);

        this.fillWithBlocks(world, sbb, 0, 1, 1, 0, 1, 5, planks, planks, false); // Walls
        this.fillWithBlocks(world, sbb, 1, 1, 0, 5, 1, 0, planks, planks, false);
        this.fillWithBlocks(world, sbb, 6, 1, 1, 6, 1, 5, planks, planks, false);
        this.fillWithBlocks(world, sbb, 1, 1, 6, 5, 1, 6, planks, planks, false);

        this.fillWithBlocks(world, sbb, 0, 3, 1, 0, 3, 5, planks, planks, false);
        this.fillWithBlocks(world, sbb, 1, 3, 0, 5, 3, 0, planks, planks, false);
        this.fillWithBlocks(world, sbb, 6, 3, 1, 6, 3, 5, planks, planks, false);
        this.fillWithBlocks(world, sbb, 1, 3, 6, 5, 3, 6, planks, planks, false);

        this.fillWithBlocks(world, sbb, 0, 4, 1, 0, 4, 5, log, log, false);
        this.fillWithBlocks(world, sbb, 1, 4, 0, 5, 4, 0, log, log, false);
        this.fillWithBlocks(world, sbb, 6, 4, 1, 6, 4, 5, log, log, false);
        this.fillWithBlocks(world, sbb, 1, 4, 6, 5, 4, 6, log, log, false);

        this.fillWithAir(world, sbb, 1, 1, 1, 5, 5, 5);
        this.fillWithBlocks(world, sbb, 1, 4, 1, 5, 4, 5, planks, planks, false);

        IBlockState glassPane = Blocks.GLASS_PANE.getDefaultState();

        this.setBlockState(world, glassPane, 1, 2, 0, sbb); // Glass and door
        this.setBlockState(world, planks, 2, 2, 0, sbb);
        this.createVillageDoor(world, sbb, random, 3, 1, 0, this.getCoordBaseMode().rotateY());
        this.setBlockState(world, planks, 4, 2, 0, sbb);
        this.setBlockState(world, glassPane, 5, 2, 0, sbb);

        this.setBlockState(world, glassPane, 1, 2, 6, sbb);
        this.setBlockState(world, glassPane, 2, 2, 6, sbb);
        this.setBlockState(world, planks, 3, 2, 6, sbb);
        this.setBlockState(world, glassPane, 4, 2, 6, sbb);
        this.setBlockState(world, glassPane, 5, 2, 6, sbb);

        this.setBlockState(world, glassPane, 0, 2, 1, sbb);
        this.setBlockState(world, glassPane, 0, 2, 2, sbb);
        this.setBlockState(world, planks, 0, 2, 3, sbb);
        this.setBlockState(world, glassPane, 0, 2, 4, sbb);
        this.setBlockState(world, glassPane, 0, 2, 5, sbb);

        this.setBlockState(world, glassPane, 6, 2, 1, sbb);
        this.setBlockState(world, glassPane, 6, 2, 2, sbb);
        this.setBlockState(world, planks, 6, 2, 3, sbb);
        this.setBlockState(world, glassPane, 6, 2, 4, sbb);
        this.setBlockState(world, glassPane, 6, 2, 5, sbb);

        IBlockState ladder = Blocks.LADDER.getDefaultState()
                .withProperty(BlockLadder.FACING, EnumFacing.SOUTH);

        // Ladders
        this.setBlockState(world, ladder, 3, 1, 5, sbb);
        this.setBlockState(world, ladder, 3, 2, 5, sbb);
        this.setBlockState(world, ladder, 3, 3, 5, sbb);
        this.setBlockState(world, ladder, 3, 4, 5, sbb);

        IBlockState toolStation = TinkerTools.toolTables.getStateFromMeta(3);
        this.setBlockState(world, toolStation, 1, 1, 1, sbb); // Inside

        generateStructurePatternChestContents(world, sbb, random, 1, 1, 2);

        IBlockState partBuilder = TinkerTools.toolTables.getStateFromMeta(2);
        this.setBlockState(world, partBuilder, 1, 1, 3, sbb);

        generateStructureCraftingStationContents(world, sbb, random, 1, 1, 4);

        IBlockState stencilTable = TinkerTools.toolTables.getStateFromMeta(1);
        this.setBlockState(world, stencilTable, 1, 1, 5, sbb);

        this.generateChest(world, sbb, random, 4, 1, 5, VillageLoot.WORKSHOP_PARTS);

        IBlockState piston = Blocks.PISTON.getDefaultState()
                .withProperty(BlockPistonBase.FACING, EnumFacing.SOUTH);

        this.setBlockState(world, piston, 5, 1, 5, sbb);

        for(int l = 0; l < 7; ++l) {
            for(int i1 = 0; i1 < 7; ++i1) {
                this.clearCurrentPositionBlocksUpwards(world, i1, 9, l, sbb);
                this.replaceAirAndLiquidDownwards(world, cobblestone, i1, -1, l, sbb);
            }
        }

        this.spawnVillagers(world, sbb, 3, 1, 3, 1);

        return true;

    }

    protected void generateStructureCraftingStationContents(World world, StructureBoundingBox sbb, Random random, int x, int y, int z) {

        BlockPos pos = new BlockPos(
                this.getXWithOffset(x, z),
                this.getYWithOffset(y),
                this.getZWithOffset(x, z)
        );

        if(!sbb.isVecInside(pos) || world.getBlockState(pos).getBlock() == Blocks.CHEST) {
            return;
        }

        IBlockState craftingStation = TinkerTools.toolTables.getDefaultState();
        world.setBlockState(pos, craftingStation);

        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TileCraftingStation) {

            TileCraftingStation tileStation = (TileCraftingStation) tile;

            LootTable table = world.getLootTableManager()
                    .getLootTableFromLocation(VillageLoot.WORKSHOP_PARTS);

            LootContext ctx = new LootContext.Builder((WorldServer) world)
                    .build();

            int slot = 0;

            for(ItemStack stack : table.generateLootForPools(random, ctx)) {
                if(slot >= tileStation.getSizeInventory()) {
                    break;
                }
                tileStation.setInventorySlotContents(slot++, stack);
            }

        }

    }

    protected void generateStructurePatternChestContents(World world, StructureBoundingBox sbb, Random random, int x, int y, int z) {

        BlockPos pos = new BlockPos(
                this.getXWithOffset(x, z),
                this.getYWithOffset(y),
                this.getZWithOffset(x, z)
        );

        if(!sbb.isVecInside(pos) || world.getBlockState(pos).getBlock() == Blocks.CHEST) {
            return;
        }

        IBlockState patternChest = TinkerTools.toolTables.getStateFromMeta(4);
        world.setBlockState(pos, patternChest);

        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TilePatternChest) {

            TilePatternChest tileChest = (TilePatternChest) tile;

            EnumFacing facing = correctPatternChestFacing(world, pos, tileChest);
            tileChest.setFacing(facing);

            LootTable table = world.getLootTableManager()
                    .getLootTableFromLocation(VillageLoot.WORKSHOP_PATTERNS);

            LootContext ctx = new LootContext.Builder((WorldServer) world)
                    .build();

            int slot = 0;

            for(ItemStack stack : table.generateLootForPools(random, ctx)) {
                if(slot >= tileChest.getSizeInventory()) {
                    break;
                }
                tileChest.setInventorySlotContents(slot++, stack);
            }

        }

    }

    /**
     * Stolen logic from {@link net.minecraft.block.BlockChest#correctFacing(World, BlockPos, IBlockState)}
     */
    private EnumFacing correctPatternChestFacing(World world, BlockPos pos, TilePatternChest chest) {

        EnumFacing facing = null;

        for(EnumFacing horizontalFacing : EnumFacing.Plane.HORIZONTAL) {

            IBlockState blockState = world.getBlockState(pos.offset(horizontalFacing));

            if(blockState.isFullBlock()) {
                if(facing != null) {
                    facing = null;
                    break;
                }
                facing = horizontalFacing;
            }

        }

        if(facing != null) {
            return facing.getOpposite();
        }

        EnumFacing currentFacing = chest.getFacing();

        if(world.getBlockState(pos.offset(currentFacing)).isFullBlock()) {
            currentFacing = currentFacing.getOpposite();
        }

        if(world.getBlockState(pos.offset(currentFacing)).isFullBlock()) {
            currentFacing = currentFacing.rotateY();
        }

        if(world.getBlockState(pos.offset(currentFacing)).isFullBlock()) {
            currentFacing = currentFacing.getOpposite();
        }

        return currentFacing;

    }

    @Override
    protected VillagerRegistry.VillagerProfession chooseForgeProfession(int count, VillagerRegistry.VillagerProfession prof) {

        VillagerRegistry.VillagerProfession oreberriesProfession = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(
                new ResourceLocation("oreberries", "tinker")
        );

        if(oreberriesProfession == null) {
            // Fallback to default vanilla Blacksmith profession
            return ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("smith"));
        }

        return oreberriesProfession;

    }

}