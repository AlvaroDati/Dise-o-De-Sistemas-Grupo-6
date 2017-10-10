# Trabajo Práctico Anual - Diseño de Sistemas - Grupo6
---
## Iniciar el proyecto en eclipse
1. git clone `https://github.com/AlvaroDati/Dise-o-De-Sistemas-Grupo-6`
2. git checkout `nombre del branch a la que queiren ir`  
3. Luego de hacer el `import` del proyecto, para "mavenizar" el proyecto  
  Hacemos: Click derecho sobre el proyecto => `Configure` => `Convert to Maven Project...`
4. Abren `cmd`, ir al directorio en donde se encuentra el proyecto y ejecutar comandos:  
  4.1 mvn clean  
  4.2 mvn eclipse:eclipse 
 
## Antlr4
1. Seguir pasos de [GITHUB de ANTLR](https://github.com/antlr4ide/antlr4ide).  
2. Para ejecutar (Run):  
  2.1 `Run` => `Run Configurations...` => `Java Aplication` => Apretan el iconito de New File => `Main`.  
    Una vez en el `Main`:  
     * `Name`: Lo nombramos Antlr4 (se puede hacer más expresivo)
     * `&Project`: Lo nombramos proyectoInversiones  
     * `&Main class`: Ponemos `org.antlr.v4.gui.TestRig` o `org.antlr.v4.runtime.misc.TestRig`          
   
    Ahora vamos a `Arguments`:  
     * `Program &arguments`: [ nombre del .g4 (sin poner el .g4)] [producción inicial] [ubicación absoluta del archivo de entrada] [árbol de parsing activado]    
      Por ejemplo: `hello r C:\\Users\text.txt -gui`  
        
    Le damos a `Apply` y ¡Listo!  
    
## Spring
1. Cambiar los directorios de antlr y prompt en el Build Path
2. Ir a cmd y ejecutar los siguientes comandos:  
  2.2. mvn eclipse:eclipse  
  2.3. mvn dependency:tree  
  2.4. mvn spring-boot:run  
3. Ir al browser que más le guste (si usan chrome son unos logis), y poner `localhost:8080` (por default spring te crea el host en el puerto 8080, se puede cambiar)
  
  
