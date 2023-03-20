package oldschoolproject.Minigames;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import oldschoolproject.Managers.Game.GameManager;
import oldschoolproject.Managers.Game.GameType;
import oldschoolproject.Modules.Loaders.Listener.BaseListener;

public class CTF extends BaseListener {
	
	/*
	 * 
	 * TODO
	 * @Custom Event
	 * The server is always listening to it
	 * The event tracks the gm's GameStage
	 * when the game's stage hits beggining it triggers the event
	 * and the event teleports all players to the corresponding game arena
	 * 
	 */
	
	/*
	 * A new game instance must only happen when a player write a new sign
	 */
	
	/*
	 * A placa é setada ao jogador digitar [ctf] na primeira linha
	 * 
	 * Um objeto "ctf" é instanciado
	 * 
	 * A localização do bloco da placa é salva na config
	 * 
	 * A placa é atualizada
	 * 
	 * Ao servidor carregar:
	 * 
	 * Ler o arquivo
	 * 
	 * Se tiver um bloco de placa na localização então instanciar o objeto "ctf" e atualizar placa
	 * 
	 * Se não, apagar localização do arquivo
	 * 
	 * 
	 * 
	 * Caso placa quebrada, apagar localização do arquivo e finalizar objeto "ctf"
	 */
	
	public GameManager gm;

	public CTF() {
		gm = new GameManager(GameType.CAPTURE_THE_FLAG, 4, 50, 600);
	}
	
	@EventHandler
	public void createGame(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[ctf]")) {
			
		}
	}
}
