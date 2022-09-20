package ubs.info.as.tp01.exosP1;

public class AllTheColorsOfTheRainbow {
	
	//Premier exercice de Thinking in Java - Bruce Eckel
	
	int anIntegerRepresentingColors, hue;
	
	public AllTheColorsOfTheRainbow(int anIntegerRepresentingColors, int hue) {
		this.anIntegerRepresentingColors = anIntegerRepresentingColors;
		this.hue = hue;
	}
	
	void changeTheHueOfTheColor(int newHue) {
		hue = newHue;
	}
	
	public static void main(String[] args) {
		AllTheColorsOfTheRainbow test = new AllTheColorsOfTheRainbow(22, 6);
		test.changeTheHueOfTheColor(28);
	}
}