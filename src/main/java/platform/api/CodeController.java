package platform.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.dto.CodeDTO;
import platform.entity.Code;
import platform.mapper.CodeMapper;
import platform.response.SaveCodeResponse;
import platform.service.CodeService;
import java.util.List;
import java.util.UUID;

@RestController
public class CodeController {

    @Autowired
    CodeService codeService;

    @Autowired
    CodeMapper codeMapper;

    @PostMapping("/api/code/new")
    public SaveCodeResponse postCode(@RequestBody Code code) {
        codeService.save(code);
        return new SaveCodeResponse(code.getId());
    }

    @GetMapping("/api/code/{id}")
    public CodeDTO getCodeById(@PathVariable UUID id) {
        return codeMapper.toCodeDTO(codeService.viewCodeById(id));
    }

    @GetMapping("/api/code/latest")
    public List<CodeDTO> getLatestCodes() {
        return codeService.getLatestCodes();
    }
}