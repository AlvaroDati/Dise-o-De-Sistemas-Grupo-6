var empresaSeleccionada;

function validar(){
	var empresa;
	empresa=document.getElementById("Empresa").value;

	if((empresa != "America Movil") && (empresa != "General Electric") && (empresa != "Berkshire Hathaway")){
		alert("ERROR: Ingrese un nombre de empresa válido; los registrados hasta el momento son America Movil, General Electric y Berkshire Hathaway");
		return false;
	}else
		alert("Datos ingresados correctamente, se mostrará a continuación la informacion de la empresa: "+empresa);
		return false;
}