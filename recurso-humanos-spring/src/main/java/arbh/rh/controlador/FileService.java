package arbh.rh.controlador;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class FileService {
    // Directorio donde se guardarán las imágenes
    private static final String DIRECTORY_PATH = "D:/react-pictures/";

    public String guardarArchivo(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = originalFilename != null ? System.currentTimeMillis() + "_" + originalFilename : "default.png";
        Path destinationPath = Paths.get(DIRECTORY_PATH + filename);

        // Crea el directorio si no existe
        if (!Files.exists(destinationPath.getParent())) {
            Files.createDirectories(destinationPath.getParent());
        }

        // Guarda el archivo en el sistema de archivos
        file.transferTo(destinationPath);

        // Retorna el nombre del archivo guardado
        return filename;
    }
}
