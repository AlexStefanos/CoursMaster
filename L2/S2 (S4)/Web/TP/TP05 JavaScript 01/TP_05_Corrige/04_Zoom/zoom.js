var fontSize = 30;

function zoom_plus()
{
	fontSize += 5;
	toZoom.style.fontSize = fontSize + "px";
}

function zoom_moins()
{
	fontSize -= 5;
	
	if (fontSize < 1)
		fontSize = 1;
	
	toZoom.style.fontSize = fontSize + "px";
}
