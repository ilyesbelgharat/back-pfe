package org.sid;

import org.sid.entities.File;
import org.springframework.core.io.InputStreamResource;

import java.util.Map;

public interface Html2PdfService {
        File html2PdfGenerator(Long idProjet,String username);
}
