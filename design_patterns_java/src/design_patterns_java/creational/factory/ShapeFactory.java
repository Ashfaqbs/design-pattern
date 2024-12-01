package design_patterns_java.creational.factory;

class ShapeFactory {
	// Static method to create objects
	public static Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		switch (shapeType.toLowerCase()) {
		case "circle":
			return new Circle();
		case "rectangle":
			return new Rectangle();
		default:
			throw new IllegalArgumentException("Unknown shape type: " + shapeType);
		}
	}
}