package proyectoInversiones.cargaBatch;


import java.io.IOException;
import java.util.ArrayList;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import proyectoInversiones.DescargaDrive;
import proyectoInversiones.Empresa;
import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.repositorio.RepositorioServicio;

public class PruebaScheduler implements org.quartz.Job {

 int nroDeArchivosPersistidos = 0;
 int sumaHashPersistidos = 0;
 
 private Repositorio repositorio;
 
 public void execute(JobExecutionContext context) throws JobExecutionException{
  
   
  RepositorioServicio repo = RepositorioServicio.getInstance();
  DescargaDrive lectorDrive = new DescargaDrive();
  
  
  ArrayList<Empresa> empresas = new ArrayList<Empresa>();
  try {
   empresas = lectorDrive.obtenerEmpresas();
  } catch (IOException e1) {
   e1.printStackTrace();
  }
  ArrayList<Integer> hashcodes = lectorDrive.getHashcodesDeArchivos();
  
  
  int sumaHash = 0;
  
  for (Integer hash:hashcodes){
   sumaHash = sumaHash + hash;
  }
  
  
  if ( sumaHash == sumaHashPersistidos) {

   System.out.printf("\nEl/los archivos ya fue/ron cargado/s\n");
   repositorio.cerrar();

  } else {
   System.out.printf("\nSe llama a una instancia de repositorioServicio para persitir los archivos del drive\n");
   
   try {
    repo.persistir(empresas);
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   System.out.println("Se persistieron los nuevos archivos");
   sumaHashPersistidos = sumaHash;
  
  }
 }

}
