package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Visit;
import dev.spring.petclinic.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

	private final VisitRepository visitRepository;

	/**
	 * 새로운 방문(Visit) 저장
	 * @param visit 저장할 Visit 객체
	 * @return 저장된 Visit 객체
	 */
	@Transactional
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	/**
	 * 특정 Pet의 방문 목록 조회
	 * @param petId 조회할 Pet의 ID
	 * @return 해당 Pet의 모든 Visit 목록
	 */
	public List<Visit> findByPetId(Long petId) {
		return visitRepository.findByPetId(petId);
	}
}

