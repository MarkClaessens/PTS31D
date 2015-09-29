package haunted;

public class Player {

	Game game;
	private String name;
	private Boolean ready;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getReady() {
		return this.ready;
	}

	public void setReady(Boolean ready) {
		this.ready = ready;
	}

	public void changeSettings() {
		// TODO - implement Player.changeSettings
		throw new UnsupportedOperationException();
	}

	public void loadSettings() {
		// TODO - implement Player.loadSettings
		throw new UnsupportedOperationException();
	}

}