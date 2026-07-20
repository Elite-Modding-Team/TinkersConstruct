package slimeknights.tconstruct.common.config;

import com.google.common.collect.Sets;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.pulsar.config.ForgeCFG;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.utils.RecipeUtil;

import java.util.Collections;
import java.util.Set;

// NOTE: Be careful when changing any old config names as they can break certain addons or mods that add integration!
public final class Config {

  public static ForgeCFG pulseConfig = new ForgeCFG("TinkerModules", "Modules");
  public static Config instance = new Config();
  public static Logger log = Util.getLogger("Config");

  private Config() {
  }

  public static boolean forceRegisterAll = false; // enables all common items, even if their module is not present
  public static boolean registerAllCommonMetals = true; // enables all common metals (copper, tin, aluminum, bronze, and steel)
  
  // Tools and general
  public static boolean spawnWithBook = true;
  public static boolean reuseStencil = true;
  public static boolean craftCastableMaterials = false;
  public static boolean chestsKeepInventory = true;
  public static boolean autosmeltlapis = false;
  public static boolean obsidianAlloy = true;
  public static boolean steelAlloy = true;
  public static boolean claycasts = true;
  public static boolean castableBricks = true;
  public static boolean leatherDryingRecipe = true;
  public static boolean gravelFlintRecipe = true;
  public static double oreToIngotRatio = 2;
  public static int despawnProjectile = 1200;
  public static boolean matchVanillaSlimeblock = false;
  public static boolean limitPiggybackpack = false;
  public static boolean clearGlassSilkTouch = true;
  public static boolean drainGaseousFluids = true;
  public static int maxSmelteryItemRenders = -1;
  public static int netherOresMiningLevel = 4;
  public static boolean deconstructTools = true;
  public static int deconstructXPRequirement = 0;
  public static int deconstructLevelRequirement = 0;
  public static int heatItemsTickrateSmeltery = 4;
  public static int heatItemsTickrateSearedFurnace = 4;
  public static int liquidTransferRate = 6;
  public static boolean vanillaToolBreaking = false;
  public static boolean oldMattockAndKama = false;
  public static boolean jeiGuidebookButton = false;
  public static boolean repairToolsOnAnvils = false;

  private static String[] craftingStationBlacklistArray = new String[] {
      "de.ellpeck.actuallyadditions.mod.tile.TileEntityItemViewer"
  };
  private static String[] orePreference = {
      "minecraft",
      "tconstruct",
      "thermalfoundation",
      "forestry",
      "immersiveengineering",
      "embers",
      "ic2"
  };
  public static Set<String> craftingStationBlacklist = Collections.emptySet();
  public static String[] oredictMeltingIgnore = {
          "dustRedstone",
          "plankWood",
          "stickWood",
          "stickTreatedWood",
          "string",
          "minecraft:chest:0"
  };
  public static String[] materialIgnore = {
  };
  public static String[] mobHeadDrops = {
          "minecraft:skeleton;true;minecraft:skull:0",
          "minecraft:stray;true;minecraft:skull:0",
          "minecraft:wither_skeleton;true;minecraft:skull:1",
          "minecraft:zombie;true;minecraft:skull:2",
          "minecraft:player;false;minecraft:skull:3",
          "minecraft:creeper;true;minecraft:skull:4",
          "minecraft:ender_dragon;true;minecraft:skull:5",
          "minecraft:snowman;false;minecraft:pumpkin",
          "minecraft:villager_golem;false;minecraft:pumpkin",
          "mod_lavacow:boneworm;false;minecraft:skull:0",
          "mod_lavacow:forsaken;true;minecraft:skull:0",
          "mod_lavacow:skeletonking;false;minecraft:skull:0",
          "mod_lavacow:soulworm;false;minecraft:skull:1",
          "mod_lavacow:scarecrow;false;mod_lavacow:scarecrowhead_common",
          "techguns:armysoldier;false;minecraft:skull:3",
          "techguns:bandit;false;minecraft:skull:3",
          "techguns:commando;false;minecraft:skull:3",
          "techguns:dictatordave;false;minecraft:skull:3",
          "techguns:psychosteve;false;minecraft:skull:3",
          "techguns:stormtrooper;false;minecraft:skull:3",
          "techguns:zombiefarmer;true;minecraft:skull:2",
          "techguns:zombieminer;true;minecraft:skull:2",
          "techguns:zombiepoliceman;true;minecraft:skull:2",
          "techguns:zombiesoldier;true;minecraft:skull:2",
          "thaumcraft:CultistCleric;false;minecraft:skull:3",
          "thaumcraft:CultistKnight;false;minecraft:skull:3",
          "thaumcraft:CultistLeader;false;minecraft:skull:3",
          "tropicraft:tropiskeleton;false;minecraft:skull:0",
          "tropicraft:tropicreeper;false;minecraft:skull:4"
  };
  public static String[] entityMelting = {
          "minecraft:blaze;true;blazing_blood;20",
          "minecraft:evocation_illager;true;emerald;6",
          "minecraft:guardian;true;stone;50",
          "minecraft:illusion_illager;true;emerald;6",
          "minecraft:skeleton;true;notmilk;20",
          "minecraft:skeleton_horse;true;notmilk;20",
          "minecraft:silverfish;true;stone;25",
          "minecraft:slime;false;greenslime;25",
          "minecraft:snowman;true;water;100",
          "minecraft:spider;true;venom;20",
          "minecraft:stray;true;notmilk;20",
          "minecraft:villager;true;emerald;6",
          "minecraft:villager_golem;true;iron;18",
          "minecraft:vindication_illager;true;emerald;6",
          "minecraft:wither_skeleton;true;notmilk;20",
          "minecraft:zombie_pigman;true;gold;10",
          "tconstruct:blueslime;false;blueslime;25",
          "tconstruct:purpleslime;false;purpleslime;25",
          "battletowers:golem;false;stone;100",
          "mocreatures:Bee;false;venom;3",
          "mocreatures:BigGolem;false;stone;50",
          "mocreatures:CaveOgre;false;stone;50",
          "mocreatures:CaveScorpion;false;venom;20",
          "mocreatures:DarkManticore;false;venom;20",
          "mocreatures:DirtScorpion;false;venom;20",
          "mocreatures:FireManticore;false;blazing_blood;20",
          "mocreatures:FireOgre;false;blazing_blood;40",
          "mocreatures:FireScorpion;false;blazing_blood;20",
          "mocreatures:FrostManticore;false;venom;20",
          "mocreatures:FrostScorpion;false;venom;20",
          "mocreatures:GreenOgre;false;emerald;12",
          "mocreatures:HellRat;false;blazing_blood;20",
          "mocreatures:JellyFish;false;blueslime;6",
          "mocreatures:KomodoDragon;false;venom;20",
          "mocreatures:MiniGolem;false;ancient_silver;12",
          "mocreatures:PlainManticore;false;venom;20",
          "mocreatures:SilverSkeleton;false;ancient_silver;12",
          "mocreatures:Snail;false;greenslime;3",
          "mocreatures:Snake;false;venom;10",
          "mocreatures:StingRay;false;venom;10",
          "mocreatures:ToxicManticore;false;venom;20",
          "mocreatures:UndeadScorpion;false;venom;20",
          "mod_lavacow:boneworm;true;notmilk;20",
          "mod_lavacow:forsaken;true;notmilk;20",
          "mod_lavacow:grave_robber;false;emerald;6",
          "mod_lavacow:imp;false;blazing_blood;20",
          "mod_lavacow:lavacow;false;lava;20",
          "mod_lavacow:lilsludge;false;blueslime;25",
          "mod_lavacow:salamander;false;blazing_blood;40",
          "mod_lavacow:skeletonking;false;notmilk;40",
          "mod_lavacow:sludgelord;false;blueslime;25",
          "mod_lavacow:vespa;false;venom;40",
          "mod_lavacow:zombiemushroom;false;venom;20",
          "natura:babyheatscarspider;false;blazing_blood;20",
          "natura:heatscarspider;false;blazing_blood;40",
          "thaumcraft:Firebat;false;blazing_blood;5",
          "thaumcraft:Pech;true;gold;10",
          "tropicraft:tropiskeleton;true;notmilk;20"
  };
  public static String[] materialPriorities = {
          "tconstruct"
  };
  public static String[] entityJEIRendererTransformation = {
          "minecraft:ender_dragon;5.0"
  };
  public static String[] fluidIgnore = {};
  public static String[] incognitoModBlacklist = {
          "night_vision_armor",
          "potion_belt_armor",
          "soul_sight_armor",
          "travel_belt_armor",
          "travel_goggles_armor",
          "travel_sack_armor",
          "travel_slowfall_armor",
          "travel_sneak_armor"
  };

  // Worldgen
  public static boolean genSlimeIslands = true;
  public static boolean genIslandsInSuperflat = false;
  public static int slimeIslandsRate = 730; // Every x-th chunk will have a slime island. so 1 = every chunk, 100 = every 100th
  public static int magmaIslandsRate = 100; // Every x-th chunk will have a slime island. so 1 = every chunk, 100 = every 100th
  public static int[] slimeIslandBlacklist = new int[]{-1, 1};
  public static boolean slimeIslandDimensionsIsBlacklist = true;
  public static boolean slimeIslandsOnlyGenerateInSurfaceWorlds = true;
  public static boolean genSlimePools = false;
  public static int slimePoolRate = 30;
  public static int magmaPoolRate = 30;
  public static int slimePoolHeightMax = 64;
  public static int[] slimePoolDimensions = new int[]{-1, 1};
  public static boolean slimePoolDimensionsIsBlacklist = true;
  public static boolean slimePoolsOnlyGenerateInSurfaceWorlds = true;
  public static boolean genVillageStructures = true;

  public static boolean genCobalt = true;
  public static int cobaltRate = 20; // max. cobalt per chunk
  public static boolean genArdite = true;
  public static int arditeRate = 20; // max. ardite per chunk
  public static boolean genCopper = true;
  public static int copperRate = 8;
  public static int copperHeightMin = 20;
  public static int copperHeightMax = 60;
  public static boolean genTin = true;
  public static int tinRate = 8;
  public static int tinHeightMin = 0;
  public static int tinHeightMax = 40;
  public static boolean genAluminum = true;
  public static int aluminumRate = 8;
  public static int aluminumHeightMin = 0;
  public static int aluminumHeightMax = 64;

  // Clientside configs
  public static boolean renderTableItems = true;
  public static boolean renderInventoryNullLayer = true;
  public static boolean extraTooltips = true;
  public static boolean listAllTables = true;
  public static boolean listAllToolMaterials = true;
  public static boolean listAllPartMaterials = true;
  public static boolean enableForgeBucketModel = true; // enables the forge bucket model by default
  public static boolean dumpTextureMap = false; // requires debug module
  public static boolean testIMC = false; // requires debug module
  public static boolean temperatureCelsius = true;
  public static boolean disableAllParticles = false;
  public static boolean fancyJEIBeheadingAnimation = true;
  public static int minFluidHeight = 3;
  public static int columnsPartBuilder = 4;
  public static int columnsStencilTable = 4;
  public static int columnsToolStation = 5;

  /* Config File */

  static Configuration configFile;

  static ConfigCategory Modules;
  static ConfigCategory Gameplay;
  static ConfigCategory Worldgen;
  static ConfigCategory Experimental;
  static ConfigCategory ClientSide;
  
  public static void load(FMLPreInitializationEvent event) {
    configFile = new Configuration(event.getSuggestedConfigurationFile(), "0.4", false);

    MinecraftForge.EVENT_BUS.register(instance);

    syncConfig();
  }

  @SubscribeEvent
  public void update(ConfigChangedEvent.OnConfigChangedEvent event) {
    if(event.getModID().equals(TConstruct.modID)) {
      syncConfig();
    }
  }


  public static boolean syncConfig() {
    Property prop;

    // Modules
    {
      Modules = pulseConfig.getCategory();
      /*
      // convert pulse config to MC compatible config for GUI config
      Modules = new ConfigCategory("modules");
      for(PulseMeta pm : TConstruct.pulseManager.getAllPulseMetadata()) {
        if(pm.isForced()) continue;
        prop = new Property(pm.getId(), pm.isDefaultEnabled() ? "true" : "false", Property.Type.BOOLEAN);
        prop.setValue(pm.isEnabled());
        prop.setRequiresMcRestart(true);
        Modules.put(pm.getId(), prop);
      }*/
    }
    // Gameplay
    {
      String cat = "gameplay";
      Gameplay = configFile.getCategory(cat);

      prop = configFile.get(cat, "spawnWithBook", spawnWithBook);
      prop.setComment("Players who enter the world for the first time get a Tinkers' Book.");
      spawnWithBook = prop.getBoolean();

      prop = configFile.get(cat, "reuseStencils", reuseStencil);
      prop.setComment("Allows to reuse stencils in the stencil table to turn them into other stencils.");
      reuseStencil = prop.getBoolean();

      prop = configFile.get(cat, "chestsKeepInventory", chestsKeepInventory);
      prop.setComment("Pattern and Part chests keep their inventory when harvested.");
      chestsKeepInventory = prop.getBoolean();

      prop = configFile.get(cat, "enableClayCasts", claycasts);
      prop.setComment("Adds single-use clay casts.");
      claycasts = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "allowBrickCasting", castableBricks);
      prop.setComment("Allows the creation of bricks from molten clay.");
      castableBricks = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "autosmeltFortuneInteraction", autosmeltlapis);
      prop.setComment("Fortune increases drops after harvesting a block with autosmelt.");
      autosmeltlapis = prop.getBoolean();

      prop = configFile.get(cat, "craftCastableMaterials", craftCastableMaterials);
      prop.setComment("Allows to craft all tool parts of all materials in the part builder, including materials that normally have to be cast with a smeltery.");
      craftCastableMaterials = prop.getBoolean();

      prop = configFile.get(cat, "registerAllItems", forceRegisterAll);
      prop.setComment("Enables all items, even if the Module needed to obtain them is not active.");
      forceRegisterAll = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      
      prop = configFile.get(cat, "registerAllCommonMetals", registerAllCommonMetals);
      prop.setComment("Enables all common metals (copper, tin, aluminum, bronze, and steel) for ingots, nuggets, ores, and metal blocks. Disable if you want to rely on metals added by third party mods instead.");
      registerAllCommonMetals = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "obsidianAlloy", obsidianAlloy);
      prop.setComment("Allows the creation of obsidian in the smeltery, using a bucket of lava and water.");
      obsidianAlloy = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      
      prop = configFile.get(cat, "steelAlloy", steelAlloy);
      prop.setComment("Allows the creation of steel by pouring Blazin' Blood on iron ingots or blocks on a casting table or basin. Note that this will always be disabled if the steel material added by Tinkers' Antique is also disabled.");
      obsidianAlloy = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "addLeatherDryingRecipe", leatherDryingRecipe);
      prop.setComment("Adds a recipe that allows you to get leather from drying cooked meat.");
      leatherDryingRecipe = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "addFlintRecipe", gravelFlintRecipe);
      prop.setComment("Adds a recipe that allows you to craft 3 gravel into a flint.");
      gravelFlintRecipe = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "oreToIngotRatio", oreToIngotRatio);
      prop.setComment("Determines the ratio of ore to ingot, or in other words how many ingots you get out of an ore. This ratio applies to all ores (including poor and dense). The ratio can be any decimal, including 1.5 and the like, but can't go below 1. THIS ALSO AFFECTS MELTING TEMPERATURE!");
      prop.setMinValue(1);
      oreToIngotRatio = prop.getDouble();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "matchVanillaSlimeblock", matchVanillaSlimeblock);
      prop.setComment("If true, requires slimeballs in the vanilla slimeblock recipe to match in color, otherwise gives a pink slimeblock.");
      matchVanillaSlimeblock = prop.getBoolean();
      prop.setRequiresMcRestart(true);

      prop = configFile.get(cat, "limitPiggybackpack", limitPiggybackpack);
      prop.setComment("If true, piggybackpacks can only pick up players and mobs that can be leashed in vanilla. If false any mob can be picked up.");
      limitPiggybackpack = prop.getBoolean();

      prop = configFile.get(cat, "clearGlassSilkTouch", clearGlassSilkTouch);
      prop.setComment("If true, clear glass can only be harvested with silk touch like regular glass.");
      clearGlassSilkTouch = prop.getBoolean();

      prop = configFile.get(cat, "despawnProjectile", despawnProjectile);
      prop.setComment("How many ticks projectiles are allowed on the ground until they despawn.");
      despawnProjectile = prop.getInt();

      prop = configFile.get(cat, "craftingStationBlacklist", craftingStationBlacklistArray);
      prop.setComment("Blacklist of registry names or TE classnames for the crafting station to connect to. Mainly for compatibility.");
      craftingStationBlacklistArray = prop.getStringList();
      craftingStationBlacklist = Sets.newHashSet(craftingStationBlacklistArray);

      prop = configFile.get(cat, "orePreference", orePreference);
      prop.setComment("Preferred mod ID for oredictionary outputs. Top most mod ID will be the preferred output ID, and if none is found the first output stack is used.");
      orePreference = prop.getStringList();
      RecipeUtil.setOrePreferences(orePreference);

      prop = configFile.get(cat, "oredictMeltingIgnore", oredictMeltingIgnore);
      prop.setComment("List of items to ignore when generating melting recipes from the crafting registry. For example, ignoring sticks allows metal pickaxes to melt down.\nFormat: oreName or modid:item[:meta]. If meta is unset, uses wildcard.");
      oredictMeltingIgnore = prop.getStringList();

      prop = configFile.get(cat, "testIMC", testIMC);
      prop.setComment("REQUIRES DEBUG MODULE. Tests all IMC integrations with dummy recipes. May significantly impact gameplay, so its advised you disable this outside of dev environments.");
      testIMC = prop.getBoolean();

      prop = configFile.get(cat, "materialIgnore", materialIgnore);
      prop.setComment("List of materials to ignore, effectively preventing registration.");
      materialIgnore = prop.getStringList();

      prop = configFile.get(cat, "fluidIgnore", fluidIgnore);
      prop.setComment("List of fluids to ignore, effectively preventing registration of melting and casting recipes.");
      fluidIgnore = prop.getStringList();

      prop = configFile.get(cat, "drainGaseousFluids", drainGaseousFluids);
      prop.setComment("If gaseous fluids are being transferable via faucets.");
      drainGaseousFluids = prop.getBoolean();

      prop = configFile.get(cat, "maxSmelteryItemRenders", maxSmelteryItemRenders);
      prop.setComment("Determines the maximum number of possible items to display before not rendering any to prevent substantial lag. 0 to disable rendering items in the smeltery entirely. -1 for the default, which is always rendering items.");
      maxSmelteryItemRenders = prop.getInt();

      prop = configFile.get(cat, "netherOresMiningLevel", netherOresMiningLevel);
      prop.setComment("The mining level for ardite and cobalt ores.");
      netherOresMiningLevel = prop.getInt();

      prop = configFile.get(cat, "deconstructTools", deconstructTools);
      prop.setComment("If tools can be deconstructed in tool stations and tool forges by putting them into output slots.");
      deconstructTools = prop.getBoolean();

      prop = configFile.get(cat, "deconstructXPRequirement", deconstructXPRequirement);
      prop.setComment("The XP requirement for deconstructing tools (if provided by Tinkers' Tool Leveling).");
      deconstructXPRequirement = prop.getInt();

      prop = configFile.get(cat, "deconstructLevelRequirement", deconstructLevelRequirement);
      prop.setComment("The level requirement for deconstructing tools (if provided by Tinkers' Tool Leveling).");
      deconstructLevelRequirement = prop.getInt();

      prop = configFile.get(cat, "heatItemsTickrateSmeltery", heatItemsTickrateSmeltery);
      prop.setComment("The tickrate at which items are heated and alloys are created in the smeltery. Defaults to every 4th tick.");
      heatItemsTickrateSmeltery = prop.getInt();

      prop = configFile.get(cat, "heatItemsTickrateSearedFurnace", heatItemsTickrateSearedFurnace);
      prop.setComment("The tickrate at which items are heated in the seared furnace. Defaults to every 4th tick.");
      heatItemsTickrateSearedFurnace = prop.getInt();

      prop = configFile.get(cat, "liquidTransferRate", liquidTransferRate);
      prop.setComment("How much liquid is transferred by faucets and channels per pouring operation.");
      liquidTransferRate = prop.getInt();

      prop = configFile.get(cat, "oldMattockAndKama", oldMattockAndKama);
      prop.setComment("Restores old Mattock and Kama behavior (Mattock usable as a hoe, Kama is not)");
      oldMattockAndKama = prop.getBoolean();

      prop = configFile.get(cat, "mobHeadDrops", mobHeadDrops);
      prop.setComment("List of mob head drops in the format 'modid:entity;subtypes;modid:item[:metadata][;max_quantity]'. Example: 'minecraft:skeleton;true;minecraft:skull:0' or 'minecraft:chicken;false;minecraft:feather;2'");
      mobHeadDrops = prop.getStringList();

      prop = configFile.get(cat, "entityMelting", entityMelting);
      prop.setComment("List of entity melting entries in the format 'modid:entity;subtypes;fluid;amount'.");
      entityMelting = prop.getStringList();

      prop = configFile.get(cat, "materialPriorities", materialPriorities);
      prop.setComment("List of mod IDs for material registration with descending priority. Highest mod ID wins!");
      materialPriorities = prop.getStringList();
    }
    // Worldgen
    {
      String cat = "worldgen";
      Worldgen = configFile.getCategory(cat);

      // Slime Islands
      prop = configFile.get(cat, "generateSlimeIslands", genSlimeIslands);
      prop.setComment("If true, slime islands will generate.");
      genSlimeIslands = prop.getBoolean();

      prop = configFile.get(cat, "generateIslandsInSuperflat", genIslandsInSuperflat);
      prop.setComment("If true, slime islands generate in superflat worlds.");
      genIslandsInSuperflat = prop.getBoolean();

      prop = configFile.get(cat, "slimeIslandRate", slimeIslandsRate);
      prop.setComment("One in every X chunks will contain a slime island.");
      slimeIslandsRate = prop.getInt();

      prop = configFile.get(cat, "magmaIslandRate", magmaIslandsRate);
      prop.setComment("One in every X chunks will contain a magma island in the nether.");
      magmaIslandsRate = prop.getInt();

      configFile.renameProperty(cat, "slimeIslandBlacklist", "slimeIslandDimensions");

      prop = configFile.get(cat, "slimeIslandDimensions", slimeIslandBlacklist);
      prop.setComment("List of dimensions in which to enable or disable generation of slime islands.");
      slimeIslandBlacklist = prop.getIntList();

      prop = configFile.get(cat, "slimeIslandDimensionsIsBlacklist", slimeIslandDimensionsIsBlacklist);
      prop.setComment("Whether the list of slime island dimensions behaves as a blacklist or a whitelist.");
      slimeIslandDimensionsIsBlacklist = prop.getBoolean();

      prop = configFile.get(cat, "slimeIslandsOnlyGenerateInSurfaceWorlds", slimeIslandsOnlyGenerateInSurfaceWorlds);
      prop.setComment("If false, slime islands only generate in dimensions which are of type surface. This means they won't generate in modded cave dimensions like the Deep Dark. Note that the name of this property is inverted: It must be set to false to prevent slime islands from generating in non-surface dimensions.");
      slimeIslandsOnlyGenerateInSurfaceWorlds = prop.getBoolean();

      // Slime Pools
      prop = configFile.get(cat, "generateSlimePools", genSlimePools);
      prop.setComment("If true, slime pools will generate.");
      genSlimePools = prop.getBoolean();

      prop = configFile.get(cat, "slimePoolRate", slimePoolRate);
      prop.setComment("One in every X chunks will contain a slime pool.");
      slimePoolRate = prop.getInt();

      prop = configFile.get(cat, "slimePoolHeightMax", slimePoolHeightMax);
      prop.setComment("Maximum Y level for slime pool generation.");
      slimePoolHeightMax = prop.getInt();

      prop = configFile.get(cat, "slimePoolDimensions", slimePoolDimensions);
      prop.setComment("List of dimensions in which to enable or disable generation of slime pools.");
      slimePoolDimensions = prop.getIntList();

      prop = configFile.get(cat, "slimePoolDimensionsIsBlacklist", slimePoolDimensionsIsBlacklist);
      prop.setComment("Whether the list of slime pool dimensions behaves as a blacklist or a whitelist.");
      slimePoolDimensionsIsBlacklist = prop.getBoolean();

      prop = configFile.get(cat, "slimePoolsOnlyGenerateInSurfaceWorlds", slimePoolsOnlyGenerateInSurfaceWorlds);
      prop.setComment("If false, slime pools only generate in dimensions which are of type surface. This means they won't generate in modded cave dimensions like the Deep Dark. Note that the name of this property is inverted: It must be set to false to prevent slime pools from generating in non-surface dimensions.");
      slimePoolsOnlyGenerateInSurfaceWorlds = prop.getBoolean();

      // Villages
      prop = configFile.get(cat, "generateVillageStructures", genVillageStructures);
      prop.setComment("If true, Smelteries and Tool Workshops will generate in villages.");
      genVillageStructures = prop.getBoolean();

      // Ores
      prop = configFile.get(cat, "genCobalt", genCobalt);
      prop.setComment("If true, cobalt ore will generate in the nether.");
      genCobalt = prop.getBoolean();

      prop = configFile.get(cat, "genArdite", genArdite);
      prop.setComment("If true, ardite ore will generate in the nether.");
      genArdite = prop.getBoolean();

      prop = configFile.get(cat, "genCopper", genCopper);
      prop.setComment("If true, copper ore will generate in the overworld.");
      genCopper = prop.getBoolean();

      prop = configFile.get(cat, "genTin", genTin);
      prop.setComment("If true, tin ore will generate in the overworld.");
      genTin = prop.getBoolean();

      prop = configFile.get(cat, "genAluminum", genAluminum);
      prop.setComment("If true, aluminum ore will generate in the overworld.");
      genAluminum = prop.getBoolean();

      prop = configFile.get(cat, "cobaltRate", cobaltRate);
      prop.setComment("Approximate cobalt ore generation per chunk.");
      cobaltRate = prop.getInt();

      prop = configFile.get(cat, "arditeRate", arditeRate);
      prop.setComment("Approximate ardite ore generation per chunk.");
      arditeRate = prop.getInt();

      prop = configFile.get(cat, "copperRate", copperRate);
      prop.setComment("Approximate copper ore generation per chunk.");
      copperRate = prop.getInt();

      prop = configFile.get(cat, "copperHeightMin", copperHeightMin);
      prop.setComment("Minimum Y level for copper ore generation.");
      copperHeightMin = prop.getInt();

      prop = configFile.get(cat, "copperHeightMax", copperHeightMax);
      prop.setComment("Maximum Y level for copper ore generation.");
      copperHeightMax = prop.getInt();

      prop = configFile.get(cat, "tinRate", tinRate);
      prop.setComment("Approximate tin ore generation per chunk.");
      tinRate = prop.getInt();

      prop = configFile.get(cat, "tinHeightMin", tinHeightMin);
      prop.setComment("Minimum Y level for tin ore generation.");
      tinHeightMin = prop.getInt();

      prop = configFile.get(cat, "tinHeightMax", tinHeightMax);
      prop.setComment("Maximum Y level for tin ore generation.");
      tinHeightMax = prop.getInt();

      prop = configFile.get(cat, "aluminumRate", aluminumRate);
      prop.setComment("Approximate aluminum ore generation per chunk.");
      aluminumRate = prop.getInt();

      prop = configFile.get(cat, "aluminumHeightMin", aluminumHeightMin);
      prop.setComment("Minimum Y level for aluminum ore generation.");
      aluminumHeightMin = prop.getInt();

      prop = configFile.get(cat, "aluminumHeightMax", aluminumHeightMax);
      prop.setComment("Maximum Y level for aluminum ore generation.");
      aluminumHeightMax = prop.getInt();
    }
    // Experimental
    {
      String cat = "experimental";
      Experimental = configFile.getCategory(cat);

      prop = configFile.get(cat, "vanillaToolBreaking", vanillaToolBreaking);
      prop.setComment("[EXPERIMENTAL] If true, tools will be fully destroyed like vanilla tools when durability is depleted. You monster!");
      vanillaToolBreaking = prop.getBoolean();

      prop = configFile.get(cat, "jeiGuidebookButton", jeiGuidebookButton);
      prop.setComment("[EXPERIMENTAL] If true, a button is added to JEI material pages that opens the 'Materials and You' book at the approximate location.");
      jeiGuidebookButton = prop.getBoolean();

      prop = configFile.get(cat, "repairToolsOnAnvils", repairToolsOnAnvils);
      prop.setComment("[EXPERIMENTAL] If tools can be repaired or upgraded on anvils like vanilla equipment.");
      repairToolsOnAnvils = prop.getBoolean();
    }
    // Clientside
    {
      String cat = "clientside";
      ClientSide = configFile.getCategory(cat);

      // rename renderTableItems to renderInventoryInWorld
      configFile.renameProperty(cat, "renderTableItems", "renderInventoryInWorld");

      prop = configFile.get(cat, "renderInventoryInWorld", renderTableItems);
      prop.setComment("If true, all of Tinkers' blocks with contents (tables, basin, drying racks,...) will render their contents in the world.");
      renderTableItems = prop.getBoolean();

      prop = configFile.get(cat, "renderInventoryNullLayer", renderInventoryNullLayer);
      prop.setComment("If true, use a null render layer when building the models to render tables. Fixes an issue with chisel, but the config is provide in case it breaks something.");
      renderInventoryNullLayer = prop.getBoolean();

      prop = configFile.get(cat, "extraTooltips", extraTooltips);
      prop.setComment("If true, tools will show additional info in their tooltips.");
      extraTooltips = prop.getBoolean();

      prop = configFile.get(cat, "listAllTables", listAllTables);
      prop.setComment("If true, all variants of the different tables will be listed in creative. Set to false to only have the oak variant for all tables.");
      listAllTables = prop.getBoolean();

      configFile.renameProperty(cat, "listAllMaterials", "listAllToolMaterials");
      prop = configFile.get(cat, "listAllToolMaterials", listAllToolMaterials);
      prop.setComment("If true, all material variants of the different tools will be listed in creative. Set to false to only have the first found material for all tools (usually wood).");
      listAllToolMaterials = prop.getBoolean();

      prop = configFile.get(cat, "listAllPartMaterials", listAllToolMaterials); // property was split, so default to the value of tool materials
      prop.setComment("If true, all material variants of the different parts will be listed in creative. Set to false to only have the first found material for all parts (usually wood).");
      listAllPartMaterials = prop.getBoolean();

      prop = configFile.get(cat, "temperatureCelsius", temperatureCelsius);
      prop.setComment("If true, temperatures in the smeltery and in JEI will display in celsius. If false, they will use the internal units of Kelvin, which may be better for devs.");
      temperatureCelsius = prop.getBoolean();
      Util.setTemperaturePref(temperatureCelsius);

      prop = configFile.get(cat, "minFluidHeight", minFluidHeight);
      prop.setComment("Minimum fluid height to display in the smeltery, great for users that need an easier time to visually identify fluids in the smeltery interface. This can make the smeltery appear more full than it actually is, only touch this if you know what you're doing.");
      prop.setMinValue(3);
      prop.setMaxValue(8);
      minFluidHeight = prop.getInt();

      prop = configFile.get(cat, "enableForgeBucketModel", enableForgeBucketModel);
      prop.setComment("If true, tools will enable the forge bucket model on startup and then turn itself off. This is only there so that a fresh install gets the buckets turned on by default.");
      enableForgeBucketModel = prop.getBoolean();
      if(enableForgeBucketModel) {
        prop.set(false);
        ForgeModContainer.replaceVanillaBucketModel = true;
        Property forgeProp = ForgeModContainer.getConfig().getCategory(Configuration.CATEGORY_CLIENT).get("replaceVanillaBucketModel");
        if(forgeProp != null) {
          forgeProp.set(true);
          ForgeModContainer.getConfig().save();
        }
      }

      prop = configFile.get(cat, "dumpTextureMap", dumpTextureMap);
      prop.setComment("REQUIRES DEBUG MODULE. Will do nothing if debug module is disabled. If true the texture map will be dumped into the run directory, just like old forge did.");
      dumpTextureMap = prop.getBoolean();

      prop = configFile.get(cat, "columnsPartBuilder", columnsPartBuilder);
      prop.setComment("The column count of buttons in part builder GUIs.");
      columnsPartBuilder = prop.getInt();

      prop = configFile.get(cat, "columnsStencilTable", columnsStencilTable);
      prop.setComment("The column count of buttons in stencil table GUIs.");
      columnsStencilTable = prop.getInt();

      prop = configFile.get(cat, "columnsToolStation", columnsToolStation);
      prop.setComment("The column count of buttons in tool station GUIs.");
      columnsToolStation = prop.getInt();

      prop = configFile.get(cat, "disableAllParticles", disableAllParticles);
      prop.setComment("If true, disables all mod-specific particles to display.");
      disableAllParticles = prop.getBoolean();
      
      prop = configFile.get(cat, "fancyJEIBeheadingAnimation", fancyJEIBeheadingAnimation);
      prop.setComment("If true, the JEI tab for beheading will use a fancy animation.");
      fancyJEIBeheadingAnimation = prop.getBoolean();

      prop = configFile.get(cat, "entityJEIRendererScaleFactor", entityJEIRendererTransformation);
      prop.setComment("List of entity IDs that needs to be scaled when rendered in a GUI in the format 'modid:entity;scale'");
      entityJEIRendererTransformation = prop.getStringList();

      prop = configFile.get(cat, "incognitoModBlacklist", incognitoModBlacklist);
      prop.setComment("Modifiers that are still displayed despite an Incognito modifier being applied");
      incognitoModBlacklist = prop.getStringList();
    }

    // save changes if any
    boolean changed = false;
    if(configFile.hasChanged()) {
      configFile.save();
      changed = true;
    }
    if(pulseConfig.getConfig().hasChanged()) {
      pulseConfig.flush();
      changed = true;
    }
    return changed;
  }
}
