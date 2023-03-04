package oldschoolproject.Modules.Builders;

public class MessageBuilder {
	
	private String message;
	private String prefix;
	
	public MessageBuilder(String prefix) {
		this.prefix = prefix;
	}
	
	public MessageBuilder(String prefix, String message) {
		this.prefix = prefix;
		this.message = message;
	}
	
	public MessageBuilder setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public MessageBuilder setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}
	
	public MessageBuilder setType(MessageType messageType) {
		switch (messageType) {
		case DANGER:
			this.prefix = "§4[§c" + this.prefix + "§4]§r";
			return this;
		case WARNING:
			this.prefix = "§6[§e" + this.prefix + "§6]§r";
			break;
		case COMMON:
			this.prefix = "§2[§a" + this.prefix + "§2]§r";
			break;
		}
		return this;
	}
	
	public String toString() {
		if (this.prefix != null) {
			this.message = this.prefix + " " + this.message;
		}
		return message;
	}
	
	public enum MessageType {
		COMMON, WARNING, DANGER
	}
}
