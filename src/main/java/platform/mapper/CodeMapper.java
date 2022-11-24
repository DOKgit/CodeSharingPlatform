package platform.mapper;

import org.springframework.stereotype.Component;
import platform.dto.CodeDTO;
import platform.entity.Code;

@Component
public class CodeMapper {

    public CodeDTO toCodeDTO(Code code) {
        CodeDTO newCode = new CodeDTO();
        newCode.setCode(code.getCode());
        newCode.setDate(code.getDate());
        newCode.setTime(code.getTime());
        newCode.setViews(code.getViews());
        return newCode;
    }
}
