package oldschoolproject.utils.builders;

public class MessageBuilder {
	
	private String message;
	private String prefix;
	private String tag;
	
	public MessageBuilder(String tag) {
		this.tag = tag;
	}
	
	public MessageBuilder(String tag, String message) {
		this.tag = tag;
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
			this.prefix = "§4[§c" + this.tag + "§4]§r";
			return this;
		case WARNING:
			this.prefix = "§6[§e" + this.tag + "§6]§r";
			break;
		case GOOD:
			this.prefix = "§2[§a" + this.tag + "§2]§r";
			break;
		case COMMON:
			this.prefix = "§3[§b" + this.tag + "§3]§r";
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
		COMMON, WARNING, DANGER, GOOD
	}
}
