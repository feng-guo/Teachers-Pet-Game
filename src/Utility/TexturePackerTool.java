package Utility;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {

	public static void main(String[] args) {
		
		TexturePacker.process("images/sprites/unpacked/", "images/sprites/packed/", "feng_textures");
		
	}
	
}
