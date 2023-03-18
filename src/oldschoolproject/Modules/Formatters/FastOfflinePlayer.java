package oldschoolproject.Modules.Formatters;

import com.google.common.base.Charsets;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

public class FastOfflinePlayer implements OfflinePlayer {
	
	private final String playerName;

	public FastOfflinePlayer(String playerName) {
		this.playerName = playerName;
	}

	public boolean isOnline() {
		return false;
	}

	public String getName() {
		return this.playerName;
	}

	public UUID getUniqueId() {
		return UUID.nameUUIDFromBytes(this.playerName.getBytes(Charsets.UTF_8));
	}

	public boolean isBanned() {
		return false;
	}

	public void setBanned(boolean banned) {
		throw new UnsupportedOperationException();
	}

	public boolean isWhitelisted() {
		return false;
	}

	public void setWhitelisted(boolean value) {
		throw new UnsupportedOperationException();
	}

	public Player getPlayer() {
		throw new UnsupportedOperationException();
	}

	public long getFirstPlayed() {
		return System.currentTimeMillis();
	}

	public long getLastPlayed() {
		return System.currentTimeMillis();
	}

	public boolean hasPlayedBefore() {
		return false;
	}

	public Location getBedSpawnLocation() {
		throw new UnsupportedOperationException();
	}

	public boolean isOp() {
		return false;
	}

	public void setOp(boolean value) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Object> serialize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PlayerProfile getPlayerProfile() {

		return null;
	}

	@Override
	public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {


	}

	@Override
	public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {


	}

	@Override
	public void incrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {


	}

	@Override
	public void decrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {


	}

	@Override
	public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException {


	}

	@Override
	public int getStatistic(Statistic statistic) throws IllegalArgumentException {

		return 0;
	}

	@Override
	public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {


	}

	@Override
	public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {


	}

	@Override
	public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

		return 0;
	}

	@Override
	public void incrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {


	}

	@Override
	public void decrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {


	}

	@Override
	public void setStatistic(Statistic statistic, Material material, int newValue) throws IllegalArgumentException {


	}

	@Override
	public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {


	}

	@Override
	public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {


	}

	@Override
	public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

		return 0;
	}

	@Override
	public void incrementStatistic(Statistic statistic, EntityType entityType, int amount)
			throws IllegalArgumentException {


	}

	@Override
	public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {


	}

	@Override
	public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {


	}

	@Override
	public Location getLastDeathLocation() {

		return null;
	}
}
