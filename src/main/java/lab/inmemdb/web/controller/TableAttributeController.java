package lab.inmemdb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.service.TableAttributeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/databases/{dbId}/tables/{tbId}/attributes")
public class TableAttributeController {

    @Autowired
    private TableAttributeService attributeService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<TableAttribute> getAllAttributesByTableId(@PathVariable Integer id) {
        return attributeService.findAllByTableId(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<TableAttribute> addAttributeToTable(@PathVariable Integer dbId, @PathVariable Integer tbId,
                                                     @RequestBody TableAttribute tableAttribute, UriComponentsBuilder ucBuilder) {
        TableAttribute newAttribute = attributeService.addAttributeToTable(tableAttribute, tbId);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(ucBuilder.path("/api/databases/{dbId}/tables/{tbId}/attributes/{attributeId}")
                .buildAndExpand(dbId, tbId, newAttribute.getId()).toUri());
        return new ResponseEntity<>(newAttribute, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public TableAttribute editTableAttribute(@PathVariable Integer id, @RequestBody TableAttribute tableAttribute) {
        TableAttribute updatedAttribute = new TableAttribute();
        updatedAttribute.setName(tableAttribute.getName());
        updatedAttribute.setId(id);
        return attributeService.updateName(updatedAttribute);
    }
}
