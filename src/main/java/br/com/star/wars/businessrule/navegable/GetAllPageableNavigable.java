package br.com.star.wars.businessrule.navegable;

import org.springframework.data.domain.Page;
import br.com.star.wars.model.dto.PlanetDto;
import org.springframework.data.domain.Pageable;


public class GetAllPageableNavigable {
	private Page<PlanetDto> page;
	private Pageable pageable;

	public GetAllPageableNavigable(Pageable pageable) {
		this.pageable = pageable;
	}

	public GetAllPageableNavigable() {

	}

	public Page<PlanetDto> getPage() {
		return page;
	}

	public void setPage(Page<PlanetDto> page) {
		this.page = page;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}
}
