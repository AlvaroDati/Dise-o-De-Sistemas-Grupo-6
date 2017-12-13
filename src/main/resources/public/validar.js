var empresaSeleccionada;

function validar(){
	var empresa;
	empresa=document.getElementById("Empresa").value;

	if((empresa != "America Movil") && (empresa != "General Electric") && (empresa != "Berkshire Hathaway")){
		alert("Ingrese un nombre de empresa valido; los registrados hasta el momento son America Movil, General Electric y Berkshire Hathaway");
		return false;
	}
//	var TextoEncabezado = document.getElementById("TituloEmpresa").innerHTML= "<tr>"+Empresa+"</tr>";
//	var Encabezado = document.createElement("TR");
//	Encabezado.innerHTML=TextoEncabezado;
//    document.getElementById("tablita").appendChild(Encabezado);
}


//function mostrarEmpresa(){
//	var empresa = document.getElementById("Empresa").value;
//	return empresa;
//}
//
//function mostarEmpresaSeleccionada(){
//	//var empresa = mostrarEmpresa();
//	
//	var empresa = document.getElementById("tabla").rows[0].cells;
//	empresa[0].innerHTML = document.getElementById("Empresa").value;
//	
//}