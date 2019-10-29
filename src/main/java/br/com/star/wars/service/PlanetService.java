package br.com.star.wars.service;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.impl.*;
import br.com.star.wars.businessrule.navegable.GetAllPageableNavigable;
import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlanetService {

    @Autowired
    PlanetDeleteBR planetDeleteBR;
    @Autowired
    PlanetGetAllBR planetGetAllBR;
    @Autowired
    PlanetGetOneBR planetGetOneBR;
    @Autowired
    PlanetSaveBR planetSaveBR;
    @Autowired
    PlanetSearchBR planetSearchBR;
    @Autowired
    PrepareEnvironmentBR prepareEnvironmentBR;

    public void prepareEnvironmentBrExecute() throws Exception {
        ExecutionContext context = prepareEnvironmentBR.execute(new ArrayList<>());
        if (context.isExecutionSuspended()) {
            throw new Exception("Configuração do ambiente falhou!");
        }
    }

    public void planetDeleteBrExecute(String id) throws Exception {
        ExecutionContext<String> context = planetDeleteBR.execute(id);
        if (context.isExecutionSuspended()) {
            throw new Exception("Não foi possível deletar o planeta: " + id);
        }
    }

    public Page planetGetAllBrExecute(Pageable pageable) throws Exception {
        ExecutionContext<GetAllPageableNavigable> context = planetGetAllBR.execute(new GetAllPageableNavigable(pageable));
        if (context.isExecutionSuspended() || !context.getExecutionResult().isPresent()) {
            throw new Exception("Não foi possível buscar os planetas!");
        }
        return ((GetAllPageableNavigable)context.getExecutionResult().get()).getPage();
    }

    public PlanetDto planetGetOneBrExecute(String id) throws Exception {
        ExecutionContext<PlanetNavigable> context = planetGetOneBR.execute(new PlanetNavigable(new PlanetDto(id)));
        if (context.isExecutionSuspended() || !context.getExecutionResult().isPresent()) {
            throw new Exception("Não foi possível buscar o planeta com id:" + id);
        }
        return ((PlanetNavigable)context.getExecutionResult().get()).getDto();
    }

    public PlanetDto planetSaveBrExecute(PlanetDto dto) throws Exception {
        ExecutionContext<PlanetDto> context = planetSaveBR.execute(dto);
        if (context.isExecutionSuspended() && !context.getExecutionResult().isPresent()) {
            throw new Exception("Não foi possível salvar o planeta:" + dto.toString());
        }
        return (PlanetDto) context.getExecutionResult().get();

    }

    public List<PlanetDto> planetSearchBrExecute(String term) throws Exception {
        ExecutionContext<SearchNavigable> context = planetSearchBR.execute(new SearchNavigable(term));
        if (context.isExecutionSuspended() || !context.getExecutionResult().isPresent()) {
            throw new Exception("Não foi possível buscar o planeta:" + term);
        }
        return ((SearchNavigable)context.getExecutionResult().get()).getDtos();
    }
}
