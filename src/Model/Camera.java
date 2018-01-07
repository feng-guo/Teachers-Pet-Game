package Model;

public class Camera {

	private float cameraX = 0f;
	private float cameraY = 0f;
		
	public void update(float camX, float camY) {
		this.cameraX = camX;
		this.cameraY = camY;		
	}

	public float getCameraX() {
		return cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}
	
	
}
