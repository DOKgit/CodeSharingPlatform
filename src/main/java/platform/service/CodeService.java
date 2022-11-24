package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.dto.CodeDTO;
import platform.entity.Code;
import platform.mapper.CodeMapper;
import platform.repository.CodeRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {

    private final CodeRepository codeRepository;
    @Autowired
    CodeMapper codeMapper;


    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code viewCodeById(UUID id) {
        if (codeRepository.findCodeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code is not found!");
        }
        Code code = codeRepository.findCodeById(id);

        if (!code.isRestricted()) {
            code.setTime(0);
            codeRepository.save(code);
            return code;
        }

        if (code.isTimeRestricted()) {
            if (code.getTime() < 1) {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code is not found!");
            }
        }

        if (code.isViewsRestricted()) {
            if (code.getViews() == 0) {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code is not found!");
            }

            if (!code.isTimeRestricted()) {
                code.setTime(0);
            }
            code.setViews(code.getViews() - 1);
            codeRepository.save(code);
        }

        return code;

    }

    public void save(Code code) {

        if (code.getTime() > 0) {
            code.setRestricted(true);
            code.setTimeRestricted(true);
        }

        if (code.getViews() > 0) {
            code.setRestricted(true);
            code.setViewsRestricted(true);
        }
        codeRepository.save(code);
    }

    public List<CodeDTO> getLatestCodes() {
        List<Code> listCodes = codeRepository.findAll();
        Collections.reverse(listCodes);
        return listCodes.stream().filter(n -> !n.isRestricted()).limit(10).map(c -> {
            c.setTime(0);
            return codeMapper.toCodeDTO(c);
        }).toList();
    }
}
