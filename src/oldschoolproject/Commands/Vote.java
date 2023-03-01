//package oldschoolproject.Commands;
//
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//
//import oldschoolproject.Managers.Game.GameManager;
//import oldschoolproject.Managers.Voting.VoteInventory;
//import oldschoolproject.Modules.Loaders.Command.BaseCommand;
//
//public class Vote extends BaseCommand {
//
//	public Vote() {
//		super("vote");
//	}
//
//	@Override
//	public void onCommand(CommandSender sender, String[] args) {
//		if (!GameManager.isPlaying()) {
//			Player p = (Player)sender;
//			VoteInventory.openInventory(p);
//		}
//	}
//
//}
