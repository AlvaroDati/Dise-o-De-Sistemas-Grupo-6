package proyectoInversiones;

import proyectoInversiones.Empresa;
import proyectoInversiones.RepoEmpresas;
import java.util.Set;

public class Resources {

    // Singleton usage
    private static Resources instance = null;

    private RepoEmpresas repoEmpresas;
    private Factory factory = new Factory();

    public static Resources getInstance() {
        if(instance == null){
            instance = new Resources();
        }
        return instance;
    }

    private Resources(){
    }

    public Factory getFactory() {
        return this.factory;
    }


//    public boolean createRepoEmpresas(Set<Empresa> initialData) {
//        this.repoEmpresas = new RepoEmpresas(initialData);
//        return true;
//    }

}