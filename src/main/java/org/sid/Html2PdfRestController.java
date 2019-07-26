package org.sid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.sid.entities.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log()
public class Html2PdfRestController {
    private final Html2PdfService documentGeneratorService;



    @RequestMapping(value = "/html2pdf/{idProjet}/{username}", method = RequestMethod.POST, produces = "application/json")
    public File html2pdf(@PathVariable Long idProjet, @PathVariable String username) {
        System.out.println(idProjet);

        File resource = documentGeneratorService.html2PdfGenerator(idProjet,username);
        System.out.println("222222");
        if (resource != null) {
            System.out.println("hawHouni");

            return resource;
        } else {
            System.out.println("NotNotNot");
            return resource;
        }
    }
}
