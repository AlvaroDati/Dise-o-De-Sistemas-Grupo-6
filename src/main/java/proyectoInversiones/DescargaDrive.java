package proyectoInversiones;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import proyectoInversiones.Empresa;
import proyectoInversiones.Periodo;

import com.google.api.services.drive.Drive;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DescargaDrive {
    private static final String APPLICATION_NAME = "MyProject";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/proyectoInversiones");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_METADATA,DriveScopes.DRIVE_FILE,DriveScopes.DRIVE, DriveScopes.DRIVE_APPDATA);
    private static Drive driveService;
    private static Credential credential;
    
       public List<File> archivos = new ArrayList<File>();
    
	   public static ArrayList<Empresa> todasLasEmpresas;
	    
	   public ArrayList<Empresa> getTodasLasEmpresas() {
			return todasLasEmpresas;
		}
	   
	   public void setTodasLasEmpresas(ArrayList<Empresa> todasLasEmpresas) {
			DescargaDrive.todasLasEmpresas = todasLasEmpresas;
		}
	   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

	static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        InputStream in = DescargaDrive.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }


    
    public static Drive getDriveService() throws IOException {
        
    	if(credential == null) credential = authorize();
        
        if(driveService == null){
        	driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
        }
        
        return driveService;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
  
    public ArrayList<Empresa> obtenerEmpresas() throws IOException{
    	ArrayList<Empresa> empresasDelDrive = new ArrayList<Empresa>();
    	
    	 Drive driveService = getDriveService();
         FileList result = driveService.files().list()
              .setQ("mimeType='application/json'")
              .setFields("nextPageToken, files(id, name)")
              .execute();
         
         List<File> files = result.getFiles();
         
         archivos = files;
         
         if (files == null || files.size() == 0) {
         	
             System.out.println("No se encontraron archivos .json");
         
         } else {
         
         	System.out.println("Archivos encontrados:");
         	ArrayList<Empresa> empresasDeJsons = new ArrayList<Empresa>();
         	
             for (File file : files) {
                 System.out.printf("Nombre del archivo:%s ID del archivo:(%s)\n", file.getName(), file.getId()); 
             	String fileId = file.getId(); 	
             	InputStream input = driveService.files().get(fileId).executeMediaAsInputStream();
             	ArrayList<Empresa> empresasDelJson = this.leerArchivoDrive(input);
             	empresasDeJsons.addAll(empresasDelJson);
             	empresasDelDrive = empresasDeJsons;
             	if(todasLasEmpresas == null) todasLasEmpresas = empresasDeJsons;
             	}
         }
    	
    	return empresasDelDrive;
    }
    
    
    
//    public void obtenerEmpresas() throws IOException {
//        Drive driveService = getDriveService();
//
//        FileList result = driveService.files().list()
//             .setQ("mimeType='application/json'")
//             .setFields("nextPageToken, files(id, name)")
//             .execute();
//        
//        List<File> files = result.getFiles();
//        
//        archivos = files;
//        
//        if (files == null || files.size() == 0) {
//        	
//            System.out.println("No se encontraron archivos .json");
//        
//        } else {
//        
//        	System.out.println("Archivos encontrados:");
//        	ArrayList<Empresa> empresasDeJsons = new ArrayList<Empresa>();
//        	
//            for (File file : files) {
//                System.out.printf("%s (%s)\n", file.getName(), file.getId());
//            	String fileId = file.getId(); 	
//            	InputStream input = driveService.files().get(fileId).executeMediaAsInputStream();
//            	ArrayList<Empresa> empresasDelJson = this.leerArchivoDrive(input);
//            	empresasDeJsons.addAll(empresasDelJson);
//            	}
//            todasLasEmpresas = empresasDeJsons;
//        }
//  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<Empresa> leerArchivoDrive(InputStream jsonCrudo){
		ArrayList<Empresa> empresasDrive = new ArrayList<>();
		Type tipoEmpresa = new TypeToken<ArrayList<Empresa>>() {}.getType();
		final Gson gson = new Gson();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(jsonCrudo));
		empresasDrive = gson.fromJson(reader, tipoEmpresa);
	return empresasDrive;
}    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//	public List<Periodo> getPeriodos(Empresa empresa) {
//		List<Periodo> periodos = new ArrayList<Periodo>();
//		String empresaAsoc = empresa.getNombre();
//		for (int i = 0; i < todasLasEmpresas.size(); i++) {
//			if (todasLasEmpresas.get(i).getNombre().equals(empresaAsoc)) {
//				periodos = todasLasEmpresas.get(i).getPeriodos();
//			}
//		}
//		return periodos;
//	}
	
	
	public List<Periodo> getPeriodos(Empresa empresa) throws IOException {
		List<Periodo> periodos = new ArrayList<Periodo>();
		String empresaAsoc = empresa.getNombre();
		for (int i = 0; i < todasLasEmpresas.size(); i++) {
			if (todasLasEmpresas.get(i).getNombre().equals(empresaAsoc)) {
				periodos = todasLasEmpresas.get(i).getPeriodos();
			}
		}
		return periodos;
	}

	public int getCantidadDeArchivos(){
		return archivos.size();
	}
	
	public ArrayList<Integer> getHashcodesDeArchivos (){
		ArrayList<Integer> hashcodes = new ArrayList <Integer>();
		for (File archivo:archivos){
			System.out.println("Agregando hashcode: "+archivo.hashCode());
			hashcodes.add(archivo.hashCode());
		}
		return hashcodes;
	}
}