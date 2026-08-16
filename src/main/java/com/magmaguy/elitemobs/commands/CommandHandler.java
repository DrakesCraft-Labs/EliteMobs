package com.magmaguy.elitemobs.commands;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.EliteMobs;
import com.magmaguy.elitemobs.dungeons.EMPackage;
import com.magmaguy.elitemobs.dungeons.MetaPackage;
import com.magmaguy.magmacore.command.CommandManager;
import com.magmaguy.magmacore.nightbreak.NightbreakDownloadContentCommand;
import com.magmaguy.magmacore.nightbreak.NightbreakDownloadEverythingCommand;
import com.magmaguy.magmacore.nightbreak.NightbreakDownloadPluginUpdateCommand;
import com.magmaguy.magmacore.nightbreak.NightbreakForceReinstallContentCommand;
import com.magmaguy.magmacore.nightbreak.NightbreakRecommendedPluginsCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommandHandler {
    private  static CommandManager emCommand;
    private  static CommandManager adventurersGuildCommand;
    private CommandHandler() {
    }

    public static void registerCommands() {
        emCommand = new CommandManager(MetadataHandler.PLUGIN, "elitemobs");

        //Admin commands
        emCommand.registerCommand(new SetupCommand());
        emCommand.registerCommand(new SetupDoneCommand());
        emCommand.registerCommand(new SetupToggleCommand());
        emCommand.registerCommand(new SpawnBossCommand());
        emCommand.registerCommand(new SpawnBossLevelCommand());
        emCommand.registerCommand(new SpawnBossAtCommand());
        emCommand.registerCommand(new SpawnBossLevelAtCommand());
        emCommand.registerCommand(new PlaceBossCommand());
        emCommand.registerCommand(new PlaceTreasureChestCommand());
        emCommand.registerCommand(new PlaceNPCCommand());
        emCommand.registerCommand(new RemoveCommand());
        emCommand.registerCommand(new EventCommand());
        emCommand.registerCommand(new StatsCommand());
        emCommand.registerCommand(new LootMenuCommand());
        emCommand.registerCommand(new LootGiveCommand());
        emCommand.registerCommand(new LootRewardCommand());
        emCommand.registerCommand(new LootRandomCommand());
        emCommand.registerCommand(new LootSimulateMultipleCommand());
        emCommand.registerCommand(new LootSimulateCommand());
        emCommand.registerCommand(new VersionCommand());
        emCommand.registerCommand(new ReloadCommand());
        emCommand.registerCommand(new KillCommand());
        emCommand.registerCommand(new KillRadiusCommand());
        emCommand.registerCommand(new KillTypeCommand());
        emCommand.registerCommand(new KillTypeRadiusCommand());
        emCommand.registerCommand(new LootDebugLimitedCommand());
        emCommand.registerCommand(new LootDebugCommand());
        emCommand.registerCommand(new MoneyAddCommand());
        emCommand.registerCommand(new MoneyAddAllCommand());
        emCommand.registerCommand(new MoneySetCommand());
        emCommand.registerCommand(new MoneyCheckPlayerCommand());
        emCommand.registerCommand(new UnbindForceCommand());
        emCommand.registerCommand(new FireballCommand());
        emCommand.registerCommand(new RespawnAllCommand());
        emCommand.registerCommand(new PackageDungeonCommand());
        emCommand.registerCommand(new DungeonResetLockoutCommand());
        emCommand.registerCommand(new LanguageCommand());
        emCommand.registerCommand(new PlaceWormholeCommand());
        emCommand.registerCommand(new LootStats());
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.economiaPropiaActiva()) {
            emCommand.registerCommand(new ShopProceduralOtherCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.economiaPropiaActiva()) {
            emCommand.registerCommand(new ShopCustomOtherCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.economiaPropiaActiva()) {
            emCommand.registerCommand(new ShopSellOtherCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestBypassCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestCompleteCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestCompleteQuestCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestResetCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestResetAllCommand());
        }
        emCommand.registerCommand(new TransitiveBlocksCancelCommand());
        emCommand.registerCommand(new TransitiveBlocksRegisterCommand());
        emCommand.registerCommand(new TransitiveBlocksEditCommand());
        emCommand.registerCommand(new TransitiveBlocksRegisterAreaCommand());
        emCommand.registerCommand(new TransitiveBlocksEditAreaCommand());
        emCommand.registerCommand(new SpawnElite());
        emCommand.registerCommand(new SpawnElitePowers());
        emCommand.registerCommand(new SpawnEliteAtCommand());
        emCommand.registerCommand(new DiscordMessageCommand());
        emCommand.registerCommand(new DiscordCommand());
        emCommand.registerCommand(new MoneyRemoveCommand());
        emCommand.registerCommand(new ProtectionBypassCommand());
        emCommand.registerCommand(new FirstTimeSetupCommand());
        emCommand.registerCommand(new DebugCommand());
        emCommand.registerCommand(new DebugInfoCommand());
        emCommand.registerCommand(new NightbreakRecommendedPluginsCommand(MetadataHandler.PLUGIN, EliteMobs.NIGHTBREAK_PLUGIN_SPEC));
        emCommand.registerCommand(new NightbreakDownloadPluginUpdateCommand(MetadataHandler.PLUGIN, EliteMobs.NIGHTBREAK_PLUGIN_SPEC));
        emCommand.registerCommand(new NightbreakDownloadEverythingCommand<>(MetadataHandler.PLUGIN,
                EliteMobs.NIGHTBREAK_PLUGIN_SPEC,
                CommandHandler::nightbreakBulkPackages,
                ReloadCommand::reload));
        emCommand.registerCommand(new NightbreakDownloadContentCommand<>(MetadataHandler.PLUGIN,
                EliteMobs.NIGHTBREAK_PLUGIN_SPEC,
                CommandHandler::nightbreakBulkPackages,
                ReloadCommand::reload,
                false,
                "elitemobs.downloadall"));
        emCommand.registerCommand(new NightbreakDownloadContentCommand<>(MetadataHandler.PLUGIN,
                EliteMobs.NIGHTBREAK_PLUGIN_SPEC,
                CommandHandler::nightbreakBulkPackages,
                ReloadCommand::reload,
                true,
                "elitemobs.updatecontent"));
        emCommand.registerCommand(new NightbreakForceReinstallContentCommand<>(MetadataHandler.PLUGIN,
                EliteMobs.NIGHTBREAK_PLUGIN_SPEC,
                CommandHandler::nightbreakBulkPackages,
                ReloadCommand::reload));
        emCommand.registerCommand(new PeaceBannerGiveCommand());
        emCommand.registerCommand(new PeaceBannerListCommand());

        //User commands
//        emCommand.registerCommand(new AdventurersGuildCommand());
        emCommand.registerCommand(new ShareItemCommand());
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.economiaPropiaActiva()) {
            emCommand.registerCommand(new ShopDynamicCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.economiaPropiaActiva()) {
            emCommand.registerCommand(new ShopCustomCommand());
        }
        emCommand.registerCommand(new RepairCommand());
        emCommand.registerCommand(new EnchantCommand());
        emCommand.registerCommand(new EliteScrollCommand());
        emCommand.registerCommand(new ScrollGetCommand());
        emCommand.registerCommand(new ScrollGiveCommand());
        emCommand.registerCommand(new ScrapCommand());
        emCommand.registerCommand(new UnbindCommand());
        emCommand.registerCommand(new MoneyCheckCommand());
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestAcceptCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestCheckCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestTrackCommand());
        }
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.misionesPropiasActivas()) {
            emCommand.registerCommand(new QuestLeaveCommand());
        }
        emCommand.registerCommand(new SkillSetCommand());
        emCommand.registerCommand(new SkillSetAllCommand());
        emCommand.registerCommand(new SkillCheckCommand());
        emCommand.registerCommand(new SkillTestCommand());
        emCommand.registerCommand(new SkillTestTypeCommand());
        emCommand.registerCommand(new SkillTestCancelCommand());
        emCommand.registerCommand(new SkillTestResultsCommand());
        emCommand.registerCommand(new LootCommand());
        emCommand.registerCommand(new QuitCommand());
        emCommand.registerCommand(new StartCommand());
        emCommand.registerCommand(new ArenaCommand());
        emCommand.registerCommand(new DismissCommand());
        emCommand.registerCommand(new AltCommand());
        emCommand.registerCommand(new SpawnTeleportCommand());
        emCommand.registerCommand(new DungeonTeleportCommand());
        emCommand.registerCommand(new DungeonTeleportDialogCommand());
        emCommand.registerCommand(new TrackBossCommand());
        emCommand.registerCommand(new PayCommand());
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.progresionPropiaActiva()) {
            emCommand.registerCommand(new AdventurersGuildArgCommand());
        }
        emCommand.registerCommand(new NPCQuestList());
        emCommand.registerCommand(new PartyCreateCommand());
        emCommand.registerCommand(new PartyInviteCommand());
        emCommand.registerCommand(new PartyMenuCommand());
        emCommand.registerCommand(new PartyAcceptCommand());
        emCommand.registerCommand(new PartyLeaveCommand());
        emCommand.registerCommand(new PartyReadyCommand());
        emCommand.registerCommand(new PartyDeclineCommand());
        emCommand.registerCommand(new PartyHideInteractionHintCommand());

        emCommand.registerCommand(new EliteMobsCommand());
        emCommand.registerCommand(new HelpCommand());

        adventurersGuildCommand =new CommandManager(MetadataHandler.PLUGIN, "adventurersguild");
        if (com.magmaguy.elitemobs.integrations.drakes.DrakesStandaloneModules.progresionPropiaActiva()) {
            adventurersGuildCommand.registerCommand(new AdventurersGuildCommand());
        }
    }

    private static List<EMPackage> nightbreakBulkPackages() {
        Set<String> metaChildren = EMPackage.getMetaPackageChildFilenames();
        List<EMPackage> packages = new ArrayList<>();
        for (EMPackage emPackage : EMPackage.getEmPackages().values()) {
            if (metaChildren.contains(emPackage.getContentPackagesConfigFields().getFilename())) continue;
            if (emPackage instanceof MetaPackage metaPackage) metaPackage.refreshState();
            packages.add(emPackage);
        }
        return packages;
    }
}
