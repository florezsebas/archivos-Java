import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public List<String> generarListaDeArchivos(String directorio) {
        List<String> fileNames  = new ArrayList<>();
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directorio));
            for (Path path : directoryStream) {
                if (path.toFile().isFile()) {
                    fileNames.add(path.toString());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileNames;
    }

    public List<Path> generarListaDeDiretorios(String directorio) {
        List<Path> folders = new ArrayList<>();
        try {
            folders = Files.walk(Paths.get(directorio))
                    .map(path -> path.normalize())
                    .filter(path -> path.toFile().isDirectory())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folders;
    }

    public static void main(String[] args) throws IOException {
        Main main =  new Main();
        //List<String> listaNombresArchivos = main.generarListaDeArchivos("D:/gradle-5.3.1");
//        for (String srtItem : listaNombresArchivos) {
//            //System.out.println(srtItem);
//        }

        List<Path> listaNombresDiretorios = main.generarListaDeDiretorios("D:/gradle-5.3.1");
//        for (Path pathItem : listaNombresDiretorios) {
//            System.out.println(pathItem);
//        }

        int cantidadArchivos = 0;
        for (int i = 0; i < listaNombresDiretorios.size(); i++) {
            List<String> listaNombresArchivos = main.generarListaDeArchivos(listaNombresDiretorios.get(i).toString());
            System.out.println(listaNombresDiretorios.get(i) + ": Hay "+ listaNombresArchivos.size() + " archivos");
            cantidadArchivos += listaNombresArchivos.size();
            System.out.println("Total: " + cantidadArchivos);
        }
    }

    public void nombreArchivo (){


    }
}
