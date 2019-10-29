package br.com.star.wars.controllers;

import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.repository.PlanetRepository;
import br.com.star.wars.service.PlanetService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planets")
@Api(value = "Planet Api", description = "Star Wars - Planet Api", basePath = "/api/planets")
public class PlanetController {

    @Autowired
    PlanetService planetService;

    @Autowired
    PlanetRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody PlanetDto dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(planetService.planetSaveBrExecute(dto));
    }

    @GetMapping
    public ResponseEntity getAllPageable(@RequestParam("page") int pageIndex,
                                         @RequestParam("size") int pageSize) throws Exception {

        return ResponseEntity.ok(planetService.planetGetAllBrExecute(PageRequest.of(pageIndex, pageSize)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(planetService.planetGetOneBrExecute(id));
    }


    @GetMapping(value = "/search")
    public ResponseEntity searchByName(@RequestParam("term") String term) throws Exception {
        return ResponseEntity.ok(planetService.planetSearchBrExecute(term));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws Exception {
        planetService.planetDeleteBrExecute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/status")
    public ResponseEntity getStatus() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/prepare-environment")
    public ResponseEntity prepareEnvironment() throws Exception {
        planetService.prepareEnvironmentBrExecute();
        return ResponseEntity.status(HttpStatus.CREATED).body("Environment Ready");
    }


}
