var empresaSeleccionada;

function validar(){
	var empresa;
	empresa=document.getElementById("Empresa").value;

	if((empresa != "America Movil") && (empresa != "General Electric") && (empresa != "Berkshire Hathaway")){
		alert("ERROR: Ingrese un nombre de empresa válido; los registrados hasta el momento son America Movil, General Electric y Berkshire Hathaway");
		return false;
	}//else
//		alert("Datos ingresados correctamente, se mostrará a continuación la informacion de la empresa: "+empresa);
//		return true;

}

function validarMetodologia(){
	var metodologia;
	var metodologiasCargadas = ["Metodologia1","Metodologia2","Metodologia3"];
	metodologia=document.getElementById("Metodologia").value;
	if(metodologiasCargadas.find(metodologia)){
		alert("ERROR: Ingrese una metodologia existente");
		return false;
	}else{
		alert("Datos ingresados correctamente, se aplicará ahora dicha metodología a todas las empresas:");
		return false;
	}
}