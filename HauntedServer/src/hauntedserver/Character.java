public abstract class Character {

	Player bestuurder;
	private Point2d position;
	private Double movementSpeed;
	private DirectionType direction;
	private boolean moving;

	public Character() {
		// TODO - implement Character.Character
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param direction
	 */
	public void move(DirectionType direction) {
		// TODO - implement Character.move
		throw new UnsupportedOperationException();
	}

	public void detectCollision() {
		// TODO - implement Character.detectCollision
		throw new UnsupportedOperationException();
	}

	public List<Point2D> getHitBoxPoints() {
		// TODO - implement Character.getHitBoxPoints
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 * @param min
	 * @param max
	 */
	public boolean betweenInclusive(int i, int min, int max) {
		// TODO - implement Character.betweenInclusive
		throw new UnsupportedOperationException();
	}

}