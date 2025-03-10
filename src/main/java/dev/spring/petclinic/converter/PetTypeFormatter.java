package dev.spring.petclinic.converter;

import dev.spring.petclinic.model.PetType;
import dev.spring.petclinic.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


/*
현재 사용자가 petType을 선택하면 String으로 전달되지만, 이를 petType 객체로 저장해야 함
Formatter <T> 인터페이스를 사용하면 String <-> petType 간 변환을 자동으로 처리할 수 있음
 */
@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeRepository petTypeRepository;


    /**
     *  폼에서 넘어온 String을 PetType 객체로 변환하는 메서드
     * @param text - 사용자가 입력한 PetType 이름
     * @param locale - 현재 로케일 (사용하지 않음)
     * @return - String에 해당하는 PetType 객체
     * @throws ParseException - PetType을 찾을 수 없을 때 예외 발생
     */
    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        return petTypeRepository.findByName(text)
                .orElseThrow(() -> new ParseException("유효하지 않은 petType 입니다: " + text, 0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
