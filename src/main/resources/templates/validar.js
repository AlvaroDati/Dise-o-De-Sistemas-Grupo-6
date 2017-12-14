function validar(){
	var empresa;
	empresa=document.getElementById("Empresa").value;

	if((empresa != "America Movil") && (empresa != "General Electric") && (empresa != "Berkshire Hathaway")){
		alert("Ingrese un nombre de empresa valido; los registrados hasta el momento son America Movil, General Electric y Berkshire Hathaway");
		return false;
	}
}



